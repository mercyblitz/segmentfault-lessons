package com.segmentfault.deep.in.java;

/**
 * ClassLoader 与 ClassPath 之间的关系示例代码
 */
public class ClassLoaderAndClassPathDemo {


    public static void main(String[] args) {
        // 通常，在 JVM 进程中添加 -verbose:class 参数来显示加载的 Class
        // 所在的位置（source），如：
        // [0.397s][info][class,load] com.segmentfault.deep.in.java.ClassLoaderAndClassPathDemo source: file:/E:/workspace/github/mercyblitz/segmentfault-lessons/%e3%80%8c%e4%b8%80%e5%85%a5%20Java%20%e6%b7%b1%e4%bc%bc%e6%b5%b7%20%e3%80%8d/%e4%bb%a3%e7%a0%81/segmentfault/deep-in-java/stage-5/stage-5-lesson-1/target/classes/
        // Bootstrap ClassLoader 加载的 Class 将会抛出 java.lang.NullPointerException
//        getClassLocation(Object.class);
//        getClassLocation(int.class);
        // 类资源与 URL 有关联，是否意味着 ClassLoader 与 URL 存在关联
        getClassLocation(ClassLoaderAndClassPathDemo.class);
        // Spring Boot spring-boot-loader
        // 文件目录：Expose -> File Handler
        // 文件：JAR、WAR、EAR Jar Handler
        // URL 抽象 Java 资源管理


    }

    private static void getClassLocation(Class<?> klass) {
        System.out.printf("类[%s] 资源所在的位置：%s\n", klass,
                klass.getProtectionDomain().getCodeSource().getLocation());
    }
}
