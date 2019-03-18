package com.segmentfault.deep.in.java.thread;

public class ThreadWaitAndNotifyDemo {

    public static void main(String[] args) throws InterruptedException {
        // Thread 实现 Runnable
        // 如果没有传递 Runnable 对象实现，空执行
        // wait() 语义：在同步（互斥）场景下

        // Lock 场景： T1、T2，互斥访问资源 R
        // T1 获取 L(T1) -> T2 获取 L(T2)
        // T1.wait() T2.wait() 都要被阻塞（停顿）
        Thread t1 = new Thread(ThreadWaitAndNotifyDemo::sayHelloWorld); // 方法引用
        t1.setName("T1");
        t1.start(); // 启动线程

        Thread t2 = new Thread(ThreadWaitAndNotifyDemo::sayHelloWorld);
        t2.setName("T2");
        t2.start();

        // Object.wait() 与 Thread.join() 看起来效果类似
        // 实际上 Thread.join() 方法就是调用了 Thread 对象 wait(int) 方法

        // 调用 wait() 方法的对象，再调用 notify() 方法必须是同一对象
        // 因此以下调用时错误的示范
        Object monitor = ThreadWaitAndNotifyDemo.class;
        synchronized (monitor) {
//            t1.notify();
//            t2.notify();
        }

        // 正确的写法：
        synchronized (monitor) {
            // 为什么 monitor.notify() 不能保证完全释放
            // monitor.notify();
            monitor.notifyAll();
        }

        // 第三方可以（条件）控制 T1 或者 T2 释放

        // Java 5+ : Condition 条件，await() 等待，signal() 通知
    }

    public static void sayHelloWorld() {

        Thread currentThread = Thread.currentThread();

        Object monitor = ThreadWaitAndNotifyDemo.class;

        synchronized (monitor) {
            try {
                System.out.printf("线程[%s] 进入等待状态...\n", currentThread.getName());
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("线程[%s] 恢复执行...\n", currentThread.getName());
            System.out.printf("线程[%s] : Hello,World!\n", currentThread.getName());
        }
    }

}
