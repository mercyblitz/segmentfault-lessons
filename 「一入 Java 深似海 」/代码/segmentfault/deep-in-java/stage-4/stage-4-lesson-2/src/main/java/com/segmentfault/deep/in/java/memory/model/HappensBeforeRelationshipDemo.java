package com.segmentfault.deep.in.java.memory.model;

/**
 * happens-before relationship
 */
public class HappensBeforeRelationshipDemo {

    // 性能开销：Lock > CAS > volatile
    // 术语：    Mutex > CAS > Memory Barrier
    // 底层分析：N多指令 > 多个指令 > 单个或若干指令

    public static void main(String[] args) {

    }

    /**
     * If x and y are actions of the same thread and x comes before y in program order,
     * then hb(x, y).
     */
    private static void inSameThread() {
        // action1
        // action2
    }

    /**
     * There is a happens-before edge from the end of a constructor of an object to the
     * start of a finalizer (§12.6) for that object
     */
    private static void constructorHappensBeforeFinalizer() {
        // 构造早于销毁（终结）之前
        // 构造对象是在用户线程（main、子线程）中执行
        // Finalizer 操作是 JVM 线程（GC 线程）中执行
        // 对象存放在 Heap 里面，Heap 对于线程是共享的
        // 假设 Object 刚创建，Finalizer 线程看到该对象，马上回收
    }

    /**
     * lock 对象是一个锁对象，也可视作 monitor
     */
    private static final Object monitor = new Object();

    /**
     * The wait methods of class Object (§17.2.1) have lock and unlock actions
     * associated with them; their happens-before relationships are defined by these
     * associated actions.
     *
     * @throws InterruptedException
     */
    private static void synchronizedAndWait() throws InterruptedException {

        // JMM 描述：
        // monitor (lock) happens-before monitor.wait()
        // monitor.wait() happens-before monitor (unlock)

        // 实际情况：
        // monitor (lock) synchronizes-with monitor.wait()
        // monitor.wait() synchronizes-with monitor (unlock)

        // if x synchronizes-with y , then x happens-before y
        synchronized (monitor) {
            monitor.wait(); //
            // 当 wait() 方法所属对象没有被 synchronized 关键字修饰，
            // 将抛出 IllegalMonitorStateException
        }
    }

    private static void threadStartAndJoin() throws InterruptedException {

        Thread t = new Thread(() -> {
            // action 动作
        });

        t.start(); // start() 方法 happens-before actions 之前

        // main 线程调用线程 t 的join() 方法
        // 在 join() 方法返回之前，t 所有的 actions 已执行结束
        t.join();

    }
}
