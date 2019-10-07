package com.segmentfault.deep.in.java.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronousQueueDemo {

    public static void main(String[] args) throws InterruptedException {


        // SynchronousQueue 互斥使用场景
        // SynchronousQueue put() 完成之后，必须被其他线程 take()
        // capacity == 0 , 又允许插入(put) 一个元素
        // offer 方法无效，add 方法抛出异常
        BlockingQueue<Integer> sQueue = new SynchronousQueue<>(true);
        // 申请 2 个大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (AtomicInteger i = new AtomicInteger(1); i.get() < 100; i.incrementAndGet()) {
            executorService.execute(() -> { // 写线程
                try {
                    // 必须要有 put，不能用 offer
                    // BlockingQueue 尽可能用 put，避免使用 offer，最好不要用 add
                    // sQueue.offer(1); // 如果 SynchronousQueue 被其他线程调用 take() 方法的话，会发生死锁
                    sQueue.put(i.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            executorService.execute(() -> { // 读线程
                try {
                    System.out.println(sQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.awaitTermination(10, TimeUnit.MICROSECONDS);

        executorService.shutdown();
    }

    private static void synchronousQueuevsArrayBlockingQueue() throws InterruptedException {
        // size == 0 特殊 ArrayBlockingQueue
        BlockingQueue<Integer> sQueue = new SynchronousQueue<Integer>();
        sQueue.put(1);

        System.out.println(sQueue.size());

        BlockingQueue<Integer> aQueue = new ArrayBlockingQueue<>(1);

        aQueue.add(1);

        System.out.println(aQueue.size());

    }

    // offer 方法来自于 Queue 接口，因此，子接口无法超越 Queue 方法签名

    public boolean equals(Object object)
//            throws Exception // 错误（编译时）：超越父类 Object equals(Object) 方法签名
            throws RuntimeException // 非 checked 异常时没有以上限制
    {

        return false;
    }
}



