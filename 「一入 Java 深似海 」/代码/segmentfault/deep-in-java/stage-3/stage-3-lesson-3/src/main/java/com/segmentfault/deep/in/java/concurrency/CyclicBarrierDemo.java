package com.segmentfault.deep.in.java.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cb = new CyclicBarrier(4);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(() -> {
                try {
                    echoThread();
                    cb.await(); // await() 让计数 -1
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(5 * 100);

        // 当 count 数量等于 0，才会释放
        // 4 - 3 == 1 !=0
        // 再次 await() -1 => 4 - 3 - 1 = 0
        System.out.println(cb.getNumberWaiting());
        cb.await();
        System.out.println(cb.getNumberWaiting());

//        // reset() 操作是恢复计数 0 -> 4
        cb.reset();
        System.out.println(cb.getNumberWaiting());
//
        // 关闭线程池
        executorService.shutdown();

    }


    private static void echoThread() {
        System.out.printf("当前线程[%s]正在执行...\n", Thread.currentThread().getName());
    }

}
