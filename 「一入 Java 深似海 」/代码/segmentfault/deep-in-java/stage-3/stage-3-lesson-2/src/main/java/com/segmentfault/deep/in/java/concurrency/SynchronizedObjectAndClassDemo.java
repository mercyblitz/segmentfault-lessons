package com.segmentfault.deep.in.java.concurrency;

public class SynchronizedObjectAndClassDemo {

    public static void main(String[] args) {

        Object object = new Object();

        // 对象锁
        synchronized (object) { // Monitor -> Object
            // 对象存在在 Heap（堆）
            // ...
        }

        // 类锁
        synchronized (Object.class) {
            // 类对象存在于 Metadata区域（Java 8+）
            // 或者 Perm 区域（<= Java 7）方法区
            // ...
        }

        // Class 对象就是 Object 实例
        Object objectClass = Object.class;

    }
}
