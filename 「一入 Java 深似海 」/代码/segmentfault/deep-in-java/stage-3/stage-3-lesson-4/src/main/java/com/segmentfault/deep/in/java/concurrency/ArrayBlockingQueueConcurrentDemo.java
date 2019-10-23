package com.segmentfault.deep.in.java.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBlockingQueueConcurrentDemo {

    public static void main(String[] args) throws InterruptedException {

        // 最大允许添加 2 个元素
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2, true);
        // 申请 2 个大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (AtomicInteger i = new AtomicInteger(1); i.get() < 100; i.incrementAndGet()) {
            executorService.submit(() -> { // 写线程（1）
                try {
                    queue.put(i.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            executorService.submit(() -> { // 读线程（1）
                try {
                    System.out.println(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

//        executorService.submit(() -> { // 写线程（2）
//            queue.put(2);
//        });


        executorService.awaitTermination(10, TimeUnit.SECONDS);
        // 关闭线程池
        executorService.shutdown();

    }
}
