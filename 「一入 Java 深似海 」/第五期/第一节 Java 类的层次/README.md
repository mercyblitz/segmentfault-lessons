# 「一入 Java 深似海 」系列 第五期 第一节 《Java 类的层次》



## JVM 小常识

###  native 源码路径

通常 XXX.java 出现 native 方法时，对应的 JVM 源码实现则为 XXX.c 文件，比如：

- java.lang.Class -> share/native/java/lang/Class.c
- java.lang.ClassLoader -> share/native/java/lang/ClassLoader.c



###  native 方法注册机制

Java Class 文件中的 `registerNatives（）` 方法，以 `java.lang.Class` 为例：

```java
    private static native void registerNatives();
    static {
        registerNatives();
    }
```



对应的 JVM 实现：

```c
JNIEXPORT void JNICALL
Java_java_lang_Class_registerNatives(JNIEnv *env, jclass cls)
{
    methods[1].fnPtr = (void *)(*env)->GetSuperclass;
    (*env)->RegisterNatives(env, cls, methods,
                            sizeof(methods)/sizeof(JNINativeMethod));
}
```



其中 `JNINativeMethod` 是数据结构，`methods` 是函数指针（地址）：

```c
static JNINativeMethod methods[] = {
    {"getName0",         "()" STR,          (void *)&JVM_GetClassName},
    {"getSuperclass",    "()" CLS,          NULL},
    {"getInterfaces0",   "()[" CLS,         (void *)&JVM_GetClassInterfaces},
    {"getClassLoader0",  "()" JCL,          (void *)&JVM_GetClassLoader},
    {"isInterface",      "()Z",             (void *)&JVM_IsInterface},
    {"getSigners",       "()[" OBJ,         (void *)&JVM_GetClassSigners},
    {"setSigners",       "([" OBJ ")V",     (void *)&JVM_SetClassSigners},
    {"isArray",          "()Z",             (void *)&JVM_IsArrayClass},
    {"isPrimitive",      "()Z",             (void *)&JVM_IsPrimitiveClass},
    {"getComponentType", "()" CLS,          (void *)&JVM_GetComponentType},
    {"getModifiers",     "()I",             (void *)&JVM_GetClassModifiers},
    {"getDeclaredFields0","(Z)[" FLD,       (void *)&JVM_GetClassDeclaredFields},
    {"getDeclaredMethods0","(Z)[" MHD,      (void *)&JVM_GetClassDeclaredMethods},
    {"getDeclaredConstructors0","(Z)[" CTR, (void *)&JVM_GetClassDeclaredConstructors},
    {"getProtectionDomain0", "()" PD,       (void *)&JVM_GetProtectionDomain},
    {"getDeclaredClasses0",  "()[" CLS,      (void *)&JVM_GetDeclaredClasses},
    {"getDeclaringClass0",   "()" CLS,      (void *)&JVM_GetDeclaringClass},
    {"getGenericSignature0", "()" STR,      (void *)&JVM_GetClassSignature},
    {"getRawAnnotations",      "()" BA,        (void *)&JVM_GetClassAnnotations},
    {"getConstantPool",     "()" CPL,       (void *)&JVM_GetClassConstantPool},
    {"desiredAssertionStatus0","("CLS")Z",(void *)&JVM_DesiredAssertionStatus},
    {"getEnclosingMethod0", "()[" OBJ,      (void *)&JVM_GetEnclosingMethodInfo},
    {"getRawTypeAnnotations", "()" BA,      (void *)&JVM_GetClassTypeAnnotations},
};
```

例如：`getName0` 方法是来自于 Java native 方法名， `JVM_GetClassName` 是  JVM C 实现函数：

```c
JVM_ENTRY(jstring, JVM_GetClassName(JNIEnv *env, jclass cls))
  assert (cls != NULL, "illegal class");
  JVMWrapper("JVM_GetClassName");
  JvmtiVMObjectAllocEventCollector oam;
  ResourceMark rm(THREAD);
  const char* name;
  if (java_lang_Class::is_primitive(JNIHandles::resolve(cls))) {
    name = type2name(java_lang_Class::primitive_type(JNIHandles::resolve(cls)));
  } else {
    // Consider caching interned string in Klass
    Klass* k = java_lang_Class::as_Klass(JNIHandles::resolve(cls));
    assert(k->is_klass(), "just checking");
    name = k->external_name();
  }
  oop result = StringTable::intern((char*) name, CHECK_NULL);
  return (jstring) JNIHandles::make_local(env, result);
JVM_END
```



### JVM Header 与 Source 文件的映射

header 文件是`.hpp` 存储，source 文件是对应的 `.cpp` 文件。



### 重要方法 JNIHandles::resolve

将 JNI jxxx 对象转化成 oop 对象



### 重要文件



#### allocation.hpp 以及 allocation.cpp 文件

- ResourceObj

For objects allocated in the resource area (see resourceArea.hpp).

- CHeapObj

 For objects allocated in the C-heap (managed by: free & malloc).

- StackObj

For objects allocated on the stack.

- ValueObj

For embedded objects.

- AllStatic

For classes used as name spaces.

- MetaspaceObj

For classes in Metaspace (class data)




## Java 类对象



### 基本概念

类对象 =  Class 对象 = `.class`



### 基本分类

对象类型：`Object.class` 以及派生类

原生类型：`int`、`long` 等

类定义 = 原生+对象字段作为存储状态 + 方法来控制状态行为





### 基础知识



####　类的版本

类的版本属于 Java 虚拟机规范：JVM 以及 Class

>  Java 语言规范：JDK 和 Java 运行时语法、行为控制



#### 编译器

- javac - Java 程序编写的可执行程序（since 6 开始，通过 Java 实现，6 之前，native 实现）（Maven 编译器插件）

- ecj - eclipse 编译器，JSP 编译器（JSP 翻译 JSP .java 文件，编译成 JSP .class 文件），Eclipse IDE（注意 Java 泛型差异）

  > JSP - JspServlet 运行时参数 development = true -> false

> javac 与 eclipse 编译器不一定完全兼容



####  Class 与 ClassLoader 之间的关系

Java Class 的元（meta）信息定义在 .class 文件中，类版本号、魔数、类结构、字节码等等



ClassLoader 是按照字节码规则加载 Class 资源（.class 文件 ，网络传输），形成 Java 运行时 Class 对象



1. ClassLoader#loadClass 是用于加载 Class 对象，那么 Class 对象是被那个 ClassLoader 加载的？

Class 对象是通过 ClassLoader 的 loadClass 方法加载并获取，相反，ClassLoader 可以通过 Class 的 getClassLoader() 获取。



2. Class 对象可能来自于文件（如 .class 文件），也可能是来自于网络，那么 ClassLoader 是如何将这些资源变为 Class 对象

.class 文件或者网络 I/O 都是字节码流（Stream），ClassLoader 能够把二进制流解析为 Class 对象，通过复用 ClassLoader#defineClass 方法实现（重）定义 Class 类，比如 Groovy 动态加载类。



3. Class 对象与 ClassLoader#loadClass 方法之间的关联？

ClassLoader#loadClass 时，通过 ClassPath 关联类资源（文件、网络），来获取字节码流，再被定义 Class 对象。



ClassLoader 类加载顺序：

- 尝试已加载的类（ ClassLoader 层次性，要不当前 ClassLoader，要不其 Parent）

- 尝试双亲委派加载类（直到 Bootstrap ClassLoader 加载不到）
- 执行 findClass(String) 方法（ClassLoader 是一个抛出 ClassNotFoundException 实现，子类可覆盖该方法）
  - jdk.internal.loader.BuiltinClassLoader#findClass
    - ClassPath = URLClassPath



#### 系统 ClassLaoder 实现



#####  Java 12 中的实现

- jdk.internal.loader.ClassLoaders.AppClassLoader
  - jdk.internal.loader.BuiltinClassLoader
    - java.security.SecureClassLoader
      - java.lang.ClassLoader



#### ClassLoader 双亲委派



`ClassLoader` 提供 parent 字段引用双亲 ClassLoader，通常调用 loadClass(String) 方法：

```java
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }
```

双亲委派类加载实际是通过 `parent` 字段递归，找到根 ClassLoader。实际上，根 ClassLoader = Bootstrap ClassLoader

loadClass(String) 方法 不仅可被 Java 程序调用，比如：Spring BeanFactory 类加载时，也可能被 JVM 调用，比如：new XXX()时，XXX 将作为类被加载。

#### JVM 调用 loadClass 方法的时机

- new XXX() 时
- 调用 XXX.yyy 静态字段
- 通过反射 API - `Class.forName` 或者 ` ClassLoader.loadClass` 



#### 原生类型 Class 对象



```java
// 原生类型也有类对象
Class<?> intClas = int.class;
```

#### Bootstrap ClassLoader

等价于：
```java
    Class<?> findBootstrapClassOrNull(String name) {
        if (!checkName(name)) return null;

        return findBootstrapClass(name);
    }

    // return null if not found
    private native Class<?> findBootstrapClass(String name);
```



#### ClassLoader

Bootstrap ClassLoader 是无法获取，最接近 Java 程序的 ClassLoader 是 System ClassLoader



##### 系统 ClassLoader

通过 `java.lang.ClassLoader#getSystemClassLoader` 方法获取。

##### 应用 ClassLoader

可以通过当前 Thread 上下文 ClassLoader 获取（不完全一定）：`java.lang.Thread#getContextClassLoader`





#### 相关书籍

《深入 Java 虚拟机》- 1999 年



## Java 类存储

Java 中的 Class 类 对应的 JVM 结构 Klass 类，由于Class 类存储从 Java 8 + 放置 Metadata 区域，不在是 Perm：

```c++
class Klass : public Metadata {
    
}
```



>  <https://www.tuicool.com/articles/jIfiUb>



## Java 类装载和卸载





