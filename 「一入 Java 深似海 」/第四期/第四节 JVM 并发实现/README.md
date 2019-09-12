# JVM 并发实现



## Java 线程 - java.lang.Thread



### 预备知识

- C++ Basic
- JNI - Java Native Interface
- POSIX Thread



### java.lang.Thread API 定义

#### 基本操作

##### 创建 - `new Thread()`

Thread 对象 new 实际普通的 Java 对象创建，不涉及 JVM 或者 OS 线程创建和启动

JNI 类型：`jobject`

##### 启动 - `Thread#start()`

 `Thread#start()` 引导线程启动，不一定马上启动（非阻塞），终究会 `Thread#run()` 方法（回调）

 `Thread#start()`  方法定义：

```java
public synchronized void start() { // 线程安全
    ...
    group.add(this); // 将当前线程添加到所在 ThreadGroup
    ...
    try {
        start0(); // Native 方法
    }
   	...
}

private native void start0(); // JNI Java 调用 JVM 方法
```



Java 类名称： `java.lang.Thread `

```java
public
class Thread implements Runnable {
    /* Make sure registerNatives is the first thing <clinit> does. */
    private static native void registerNatives();
    static {
        registerNatives();
    }
	...
}
```

JNI 方法名称（C 函数）：

```c++
JNIEXPORT void JNICALL
Java_java_lang_Thread_registerNatives(JNIEnv *env, jclass cls)
{
    (*env)->RegisterNatives(env, cls, methods, ARRAY_LENGTH(methods));
}
```



java.lang.Thread native 方法的映射表：

```c++
static JNINativeMethod methods[] = {
    {"start0",           "()V",        (void *)&JVM_StartThread},
    {"stop0",            "(" OBJ ")V", (void *)&JVM_StopThread},
    {"isAlive",          "()Z",        (void *)&JVM_IsThreadAlive}
    ...
};
```



方法签名：`java.lang.Thread#start0()`

Native 方法：`JVM_StartThread`

```c++
/*
 * java.lang.Thread
 */
JNIEXPORT void JNICALL
JVM_StartThread(JNIEnv *env, jobject thread);
```

>  方法声明位置：`/jdk/src/share/javavm/export/jvm.h`

实现入口：

```c++
JVM_ENTRY(void, JVM_StartThread(JNIEnv* env, jobject jthread))
...
{
    jlong size =
             java_lang_Thread::stackSize(JNIHandles::resolve_non_null(jthread));
	  ...
      size_t sz = size > 0 ? (size_t) size : 0;
      native_thread = new JavaThread(&thread_entry, sz);
    ...
}
```

`native_thread` 创建了 `JavaThread` 类型对象，第一个构造参数 `&thread_entry`，即函数指针（引用函数 `thread_entry`）：

```c++
static void thread_entry(JavaThread* thread, TRAPS) {
  HandleMark hm(THREAD);
  Handle obj(THREAD, thread->threadObj()); // 1
  JavaValue result(T_VOID);
  JavaCalls::call_virtual(&result,         // 2
                          obj,             // 3
                          KlassHandle(THREAD, SystemDictionary::Thread_klass()),         // 4
                          vmSymbols::run_method_name(), // 5
                          vmSymbols::void_method_signature(), // 6 
                          THREAD);
}
```

1. `thread->threadObject()` 返回了 Java 代码创建 `java.lang.Thread` 对象
2. 调用 JVM C++ 对象虚拟方法（多态）
3. obj  `java.lang.Thread` 对象包装
4. java.lang.Thread 类对象
5. `java.lang.Thread#run()` 方法名称
6. `void` 方法签名

综上所述，`thread_entry` 方法执行目的是要执行 `java.lang.Thread` 对象的`run()` 方法



分析 `JavaThread::JavaThread` 构造器：

```c++
JavaThread::JavaThread(ThreadFunction entry_point, size_t stack_sz)
  ...
{
  ...
  set_entry_point(entry_point); // 设置 thread_entry 函数到当前对象
  ...
  os::create_thread(this, thr_type, stack_sz);
  ...
}
```

第一个构造参数 `entry_point` 是 `ThreadFunction`， `entry_point`  实际指向函数 - `thread_entry`，`thread_entry` 函数会被 `JavaThread` 对象作为成员变量保存，接下来执行 `os::create_thread(this, thr_type, stack_sz);` 方法，以 Linux 实现为例：

```java
bool os::create_thread(Thread* thread, ThreadType thr_type, size_t stack_size) {
  assert(thread->osthread() == NULL, "caller responsible");

  // Allocate the OSThread object
  OSThread* osthread = new OSThread(NULL, NULL);
  if (osthread == NULL) {
    return false;
  }

  // set the correct thread state
  osthread->set_thread_type(thr_type);

  // Initial state is ALLOCATED but not INITIALIZED
  osthread->set_state(ALLOCATED);

  thread->set_osthread(osthread);

  // init thread attributes
  pthread_attr_t attr;
  pthread_attr_init(&attr);
  pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_DETACHED);
   ...
    pthread_t tid;
    int ret = pthread_create(&tid, &attr, (void* (*)(void*)) java_start, thread);

    pthread_attr_destroy(&attr);

  ...
  return true;
}
```

`java.lang.Thread#start0()` -> JVM JNI -> POSIX Thread  `pthread_create`

`java_start` 函数就是实际 JVM 执行操作：

```c++
static void *java_start(Thread *thread) {
  ...
  // call one more level start routine
  thread->run();

  return 0;
}
```

参数 `thread` 实际为 `JavaThread` 对象，`thread->run()` 调用实际是 `JavaThread::run()` 方法的执行：

```c++
void JavaThread::run() {
  ...
  thread_main_inner();
  // Note, thread is no longer valid at this point!
}

void JavaThread::thread_main_inner() {
  ...
  if (!this->has_pending_exception() &&
      !java_lang_Thread::is_stillborn(this->threadObj())) {
    {
      ResourceMark rm(this);
      this->set_native_thread_name(this->get_thread_name());
    }
    HandleMark hm(this);
    this->entry_point()(this, this);
  }

  DTRACE_THREAD_PROBE(stop, this);

  this->exit(false);
  delete this;
}
```

`this->entry_point`  是 `JavaThread` 成员变量，指向 `thread_entry` 函数：

```sequence
Thread.start0() -> JVM_StartThread : JVM Native 调用
JVM_StartThread -> new JavaThread() : 创建一个 JavaThread() 对象
new JavaThread() -> os_create_thread() : 创建线程（OS 相关的）
os_create_thread() -> pthread_create() : 创建 POSIX Thread
pthread_create() -> java_start() : 线程启动并回调 java_start()
java_start() -> JavaThread_run() : 调用 JavaThread run() 方法
JavaThread_run() -> thread_main_inner() : 内部调用
thread_main_inner() -> entry_point() : entry_point 指向 thread_entry()
thread_entry() -> java.lang.Thread.run() : 回调 Java Thread run() 方法
```

当 `java.lang.Thread#start()` 方法调用完成，JVM 所创建的`JavaThread`对象就移除： `delete this`



##### 完成 - `Thread#join()`

`java.lang.Thread#join()` 底层使用 `java.lang.Object#wait(long)` 方法实现：

```java
   public final synchronized void join(long millis)
    throws InterruptedException {
		...
        if (millis == 0) {
            while (isAlive()) {
                wait(0);
            }
        } 
        ...
    }
```

 `java.lang.Object#wait(long)` 方法：

```java
public class Object {
    
    private static native void registerNatives();
    static {
        registerNatives();
    }
    ...
    public final native void wait(long timeoutMillis) throws InterruptedException;
    ...
}
```

Object Native 实现：jdk/src/share/native/java/lang/Object.c

```c++
static JNINativeMethod methods[] = {
    {"hashCode",    "()I",                    (void *)&JVM_IHashCode},
    {"wait",        "(J)V",                   (void *)&JVM_MonitorWait},
    {"notify",      "()V",                    (void *)&JVM_MonitorNotify},
    {"notifyAll",   "()V",                    (void *)&JVM_MonitorNotifyAll},
    {"clone",       "()Ljava/lang/Object;",   (void *)&JVM_Clone},
};
```

`wait(long)` 方法映射  `JVM_MonitorWait`:

```c++
JVM_ENTRY(void, JVM_MonitorWait(JNIEnv* env, jobject handle, jlong ms))
  JVMWrapper("JVM_MonitorWait");
  Handle obj(THREAD, JNIHandles::resolve_non_null(handle));
  JavaThreadInObjectWaitState jtiows(thread, ms != 0);
  if (JvmtiExport::should_post_monitor_wait()) {
    JvmtiExport::post_monitor_wait((JavaThread *)THREAD, (oop)obj(), ms);
  }
  ObjectSynchronizer::wait(obj, ms, CHECK); // 1
JVM_END
```

1. ObjectSynchronizer::wait(obj, ms, CHECK):

```c++

```



wait() 方法执行前提，当前线程获得锁（synchronized）

T1-> 获得锁（synchronized ） -> wait()  -> 释放锁

T2（等待获得锁）

T3



`Object#wait()`



Object -> Object Monitor -> WaitSet

```java
Object monitor  = new Object();
synchronized(monitor) {
    if(true){
        monitor.wait(); // monitor WaitSet {T1 -> T2 -> T3}
    }
}

synchronized(monitor) {
    if(true){
        monitor.notify(); // T4 monitor WaitSet pop() T1 
    }
}
```



WaitSet 属于 ObjectMonitor 成员：

```c++
class ObjectMonitor {
    ...
 protected:
  ObjectWaiter * volatile _WaitSet; // LL of threads wait()ing on the monitor        
}
```

_WaitSet 是 `ObjectWaiter` 类型对象，属于双向队列节点：

```c++
class ObjectWaiter : public StackObj {
 public:
  ...
  ObjectWaiter * volatile _next; // 下一个节点（Waiter）
  ObjectWaiter * volatile _prev; // 上一个节点（Waiter）
  Thread*       _thread;
  jlong         _notifier_tid;
  ParkEvent *   _event;
  volatile int  _notified ;
  volatile TStates TState ;
  Sorted        _Sorted ;           // List placement disposition
  bool          _active ;           // Contention monitoring is enabled
 public:
  ObjectWaiter(Thread* thread);

  void wait_reenter_begin(ObjectMonitor *mon);
  void wait_reenter_end(ObjectMonitor *mon);
};
```

JVM `ObjectWaiter` 类似于 Java AQS `AbstractQueuedSynchronizer.Node`



 `ObjectWaiter`  属于 CLH （双向）队列节点（自旋锁）

```c++
void Thread::SpinAcquire (volatile int * adr, const char * LockName) {
  ...
  for (;;) {
     while (*adr != 0) {
        ++ctr ;
        if ((ctr & 0xFFF) == 0 || !os::is_MP()) {
           if (Yields > 5) {
             // Consider using a simple NakedSleep() instead.
             // Then SpinAcquire could be called by non-JVM threads
             Thread::current()->_ParkEvent->park(1) ;
           } else {
             os::NakedYield() ;
             ++Yields ;
           }
        } else {
           SpinPause() ;
        }
     }
     if (Atomic::cmpxchg (1, adr, 0) == 0) return ;
  }
  ...
}
```



`AbstractQueuedSynchronizer.Node` 属于 CLH 变种（双向）队列节点（阻塞）：

```java
    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        return Thread.interrupted();
    }
```



JVM `ParkEvent->park` 以及 JDK `LockSupport.park(this)` 底层同为 `pthread_cond_wait` 函数。

相反，

JVM `ParkEvent->unpark` 以及 JDK `LockSupport.unpark(this)` 底层同为 `pthread_cond_singal` 函数。



WaitSet

N1(T1)



自旋锁（Spin Lock）

CLH 队列 

- 原生 - JVM ParkEvent（自旋）
- 变种 - JDK AQS Node（阻塞）
  - LockSupport#park()



当前 Thread 

Part Event （CLH 队列）



`jdk.internal.misc.Unsafe#park`:

```java
public final class Unsafe {

    private static native void registerNatives();
    static {
        registerNatives();
    }
    
    public native void park(boolean isAbsolute, long time)
}

```

`hotspot/src/share/vm/prims/unsafe.cpp`:

```c++
UNSAFE_ENTRY(void, Unsafe_Park(JNIEnv *env, jobject unsafe, jboolean isAbsolute, jlong time))
  UnsafeWrapper("Unsafe_Park");
  EventThreadPark event;
...
  JavaThreadParkedState jtps(thread, time != 0);
  thread->parker()->park(isAbsolute != 0, time);
...
UNSAFE_END
```

实际执行的代码：`thread->parker()->park`， 当前线程关联的 `Parker` 对象（由 parker() 方法返回）



结论：

- `java.lang.Object#wait(long)` 
  - `JVM_MonitorWait`
    - `ObjectSynchronizer::wait`
      - `Self->_ParkEvent->park ()`
        - `os::PlatformEvent::park()`
          - OS  `pthread_cond_wait`
- `Unsafe#park(boolean,long)`
  - JVM Native 
    - JVM `Parker::park`
      - OS `pthread_cond_wait`
- `Unsafe#unpark()`
  - JVM Native 
    - JVM `Parker::unpark`
      - OS `pthread_cond_singal`



T1 park -> 释放锁

T2 获得锁 -> park

T3 获得锁



T main -> T1.unpark









- 中止 - `Thread#interrupt()`
- 休眠 - `Thread#sleep(long)`
- 让出 - `Thread#yield()`
- 停止 - ~~Thread#stop()~~





### JNI 接口定义









