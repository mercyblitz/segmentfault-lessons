package com.segmentfault.deep.in.java;

/**
 * 类对象示例代码
 */
public class ClassObjectDemo {

    public static void main(String[] args) {

        // ClassLoader 在加载类过程（验证）中，去重操作
        // 验证：运行时校验 .class 文件（已经编译结果）
        // .class 文件版版本 Java 5 泛型，JVM 版本过低版本不兼容
        // 处理：参考《深入 Java 虚拟机》第二版
        // 双亲委派（类加载以及类存储）
        Class<?> objectClass = Object.class;

        // ClassLoader

        // 原生类型也有类对象
        Class<?> intClass = int.class;

        isPrimitive(objectClass);
        isPrimitive(intClass);

        // Object.class 和 int.class 均被 Bootstrap ClassLoader
        // Bootstrap ClassLoader 在 Java 9 之前，就是 rt.jar
        // 除 Bootstrap ClassLoader 之外，System ClassLoader, Application ClassLoader
    }

    private static void isPrimitive(Class klass) {
        System.out.printf("类[%s] 是否属于原生类型：%s\n", klass.getName(), klass.isPrimitive());
    }
}
