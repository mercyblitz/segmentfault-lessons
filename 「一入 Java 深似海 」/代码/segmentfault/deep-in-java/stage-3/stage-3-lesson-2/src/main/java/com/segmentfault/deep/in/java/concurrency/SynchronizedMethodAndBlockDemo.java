package com.segmentfault.deep.in.java.concurrency;

public class SynchronizedMethodAndBlockDemo {

    public static void main(String[] args) {
        // synchronized 保证互斥
        // 当某个线程 T1 获得锁（1），重新又见到 synchronized（+1）
        // 以此类推
        echo("Hello,World"); //　echo 到底重进入了多少次？3
        // echo -> PrintStream.println -> newLine()
        doEcho("Hello,World"); //　doEcho 到底重进入了多少次？4
        // doEcho -> echo -> PrintStream.println -> newLine()
    }

    private static void doEcho(String message) {
        Object object = new Object();
        synchronized (object) {
            // wait 和 notify
            echo(message);
        }
    }

    /**
     * synchronized 修饰方法（实例或类）
     *
     * @param message
     */
    private synchronized static void echo(String message) {
        System.out.println(message);
    }

}
