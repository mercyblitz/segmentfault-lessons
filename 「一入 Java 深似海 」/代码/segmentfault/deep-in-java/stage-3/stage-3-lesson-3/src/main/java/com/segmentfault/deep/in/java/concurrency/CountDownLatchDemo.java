package com.segmentfault.deep.in.java.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        // 4 -> 3 -> 2 -> 1
        CountDownLatch latch = new CountDownLatch(4); // 4 个计数
        ExecutorService executorService = Executors.newFixedThreadPool(3); // 线程数 3

        for (int i = 0; i < 3; i++) { // 启动3个线程
            executorService.submit(() -> {
                try {
                    echoThread();
                    latch.countDown(); // -1
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(5 * 100);

        // 当 count 数量等于 0，才会释放
        // 4-3 == 1 !=0
        System.out.println("CountDownLatch 剩余数量：" + latch.getCount());
        latch.await(); // await 类似 wait() -> join

        // 关闭线程池
        executorService.shutdown();

    }

    private static void echoThread() {
        System.out.printf("当前线程[%s]正在执行...\n", Thread.currentThread().getName());
    }
}
