package com.segmentfault.deep.in.java.concurrency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizationDemo {

    // pthread_mutex_t lock;
    static Lock lock = new ReentrantLock();

    static volatile int counter = 0;

    public static void main(String[] args) throws Exception {

        // pthread_cond_t condition1;
        Condition condition1 = lock.newCondition();

        // 前提：Lock#lock()
        // await() 和 signal() 或 signalAll()

        // 前提：synchronized(object) ->
        // Object wait() 和 notify() 或 notifyAll();

        synchronized (Object.class) {
//            Object.class.wait();
        }

        Thread t1 = new Thread(SynchronizationDemo::addCounter);
        Thread t2 = new Thread(SynchronizationDemo::addCounter);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    private static void addCounter() {
        lock.lock(); // pthread_mutex_lock()
        // lock.tryLock() // pthread_mutex_trylock()
        System.out.println(getThreadPrefix() + "Before Counter : " + counter);
        counter++;
        System.out.println(getThreadPrefix() + "After Counter : " + counter);
        lock.unlock(); // pthread_mutex_unlock()
    }

    private static String getThreadPrefix() {
        return "Thread[" + Thread.currentThread().getId() + "] : ";
    }
}
