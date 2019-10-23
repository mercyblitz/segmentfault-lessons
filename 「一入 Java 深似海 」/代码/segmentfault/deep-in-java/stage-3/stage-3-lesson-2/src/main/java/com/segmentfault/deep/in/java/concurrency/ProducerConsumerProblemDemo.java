package com.segmentfault.deep.in.java.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProducerConsumerProblemDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Container container = new Container();

        Future producerFuture = executorService.submit(() -> { // 生产者线程
            container.produce();
        });

        Future consumerFuture = executorService.submit(() -> { // 消费者线程
            container.consume();
        });


        Thread.sleep(1000L);

        executorService.shutdown();
    }

    public static class Container {

        private List<Integer> data = new LinkedList<>();

        private static final int MAX_SIZE = 5;

        private Random random = new Random();

        public void produce() {
            while (true) { // 永久执行
                synchronized (this) {
                    try {
                        // 当数据超过上限 MAX_SIZE，停止生产
                        while (data.size() >= MAX_SIZE) {
                            wait();
                        }
                        int value = random.nextInt(100);
                        System.out.printf("线程[%s] 正在生产数据 : %d\n", Thread.currentThread().getName(), value);
                        data.add(value);

                        // 唤起消费线程
                        notify();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void consume() {
            while (true) { // 永久执行
                synchronized (this) {
                    try {
                        // 当数据没有时，停止消费
                        while (data.isEmpty()) {
                            wait();
                        }
                        int value = data.remove(0);
                        System.out.printf("线程[%s] 正在消费数据 : %d\n", Thread.currentThread().getName(), value);
                        // 唤起消费线程
                        notify();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
