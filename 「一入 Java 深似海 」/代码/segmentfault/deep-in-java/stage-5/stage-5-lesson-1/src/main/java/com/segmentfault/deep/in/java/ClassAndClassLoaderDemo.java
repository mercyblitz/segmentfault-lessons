package com.segmentfault.deep.in.java;

/**
 * Class 与 ClassLoader 之间的关系
 */
public class ClassAndClassLoaderDemo {

    public static void main(String[] args) {

        // 获取加载 Object.class 的 ClassLoader
        // Object 是被 Bootstrap ClassLoader 加载，其 Java 表现形式为 null
        getClassLoader(Object.class);
        // 获取加载原生类型 int 的 ClassLoader
        // int.class 是被 Bootstrap ClassLoader 加载，其 Java 表现形式为 null
        getClassLoader(int.class);

        // 当前 ClassAndClassLoaderDemo？
        getClassLoader(ClassAndClassLoaderDemo.class);
        // 加载 ClassAndClassLoaderDemo.class 的 ClassLoader
        // 是否与系统 ClassLoader 相同
        System.out.println(ClassAndClassLoaderDemo.class.getClassLoader()
                == ClassLoader.getSystemClassLoader());

    }

    private static void getClassLoader(Class klass) {
        System.out.printf("当前类[%s] 被 %s ClassLoader 加载\n", klass,
                klass.getClassLoader());
    }
}
