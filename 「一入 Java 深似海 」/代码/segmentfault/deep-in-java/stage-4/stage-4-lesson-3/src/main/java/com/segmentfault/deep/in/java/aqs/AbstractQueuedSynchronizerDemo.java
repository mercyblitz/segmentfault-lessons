package com.segmentfault.deep.in.java.aqs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AbstractQueuedSynchronizerDemo {

    // ReentrantLock is AbstractQueuedSynchronizer
    private static Lock lock = new ReentrantLock();

    // Condition is a part of ReentrantLock or AbstractQueuedSynchronizer
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 一个线程获得锁，另外一个线程入队

        // main -> thread-2.interrupt()
        // sub-threads
        List<Thread> threads = new ArrayList<>();

        executorService.submit(() -> {
            threads.add(Thread.currentThread());
            action();

        }); // thread-1
        executorService.submit(AbstractQueuedSynchronizerDemo::action); // thread-1
        executorService.submit(AbstractQueuedSynchronizerDemo::action); // thread-2
        executorService.submit(AbstractQueuedSynchronizerDemo::action); // thread-3


        // 非公平锁
        // thread-1 unlock -> release -> unpark thread-2 -> thread-2 try acquire
        // thread-4 or thread-5 lock -> try acquire

        // PS : unpark = LockSupport.unpark

        // 公平锁
        // thread-1 unlock -> release -> unpark thread-2 -> thread-2 try acquire
        // thread-2 lock -> ..
        // thread-3 wait
        // thread-4 wait
        // thread-5 wait

        // 等待 200 秒
        executorService.awaitTermination(200, TimeUnit.SECONDS);
        // 关闭线程池
        executorService.shutdown();

    }

    private static void action() {
        System.out.printf("当前线程[%s] 正在等待您的输入\n", Thread.currentThread().getName());
        // I/O 中断线程
        try {


            // 利用 ReentrantLock 作为 AQS 实现，理解内部数据结构
            lock.lock();
            System.in.read();
            System.out.printf("当前线程[%s] 执行结束...\n", Thread.currentThread().getName());
        } catch (IOException e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }

}
