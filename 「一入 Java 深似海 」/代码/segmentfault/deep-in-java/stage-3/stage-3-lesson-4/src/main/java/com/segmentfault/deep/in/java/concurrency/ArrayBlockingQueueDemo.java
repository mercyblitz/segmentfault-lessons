package com.segmentfault.deep.in.java.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        // ArrayBlockingQueue 是有限队列
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue(3);

        // Queue 添加操作中 offer 安全性高于 add 方法
//        demoOfferMethod(queue);
//        demoAddMethod(queue);

        demoPutMethod(queue);
        // BlockingQueue 要使用 put 方法多于 offer 方法

        System.out.println(queue);
    }

    private static void demoPutMethod(BlockingQueue<Integer> queue) throws InterruptedException {
        queue.put(1);
        queue.put(2);
        queue.put(3);
        // 如果超过了 capacity，？
        queue.put(4);
    }

    private static void demoAddMethod(BlockingQueue<Integer> queue) {
        queue.add(1);
        queue.add(2);
        queue.add(3);
        // 如果超过了 capacity，throw new IllegalStateException("Queue full")
//        queue.add(4);
    }

    private static void demoOfferMethod(BlockingQueue<Integer> queue) {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        // 如果超过了 capacity，后面的offer 会被忽略
        queue.offer(4);
    }
}
