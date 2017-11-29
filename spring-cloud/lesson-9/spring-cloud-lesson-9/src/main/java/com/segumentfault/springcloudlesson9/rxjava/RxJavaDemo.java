package com.segumentfault.springcloudlesson9.rxjava;

import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * RxJava 1.x 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 */
public class RxJavaDemo {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("主线程：" + Thread.currentThread().getName());

        demoStandardReactive();

    }

    private static void demoStandardReactive() throws InterruptedException {

        List<Integer> values = Arrays.asList(1, 2, 3);

        Observable.from(values) //发布多个数据
                .subscribeOn(Schedulers.newThread()) // 在 newThread 线程执行
                .subscribe(value -> {

                    if (value > 2)
                        throw new IllegalStateException("数据不应许大于 2");

                    //消费数据
                    println("消费数据：" + value);

                }, e -> {
                    // 当异常情况，中断执行
                    println("发生异常 , " + e.getMessage());
                }, () -> {
                    // 当整体流程完成时
                    println("流程执行完成");
                })

        ;

        // 等待线程执行完毕
        Thread.sleep(100);
    }

    private static void demoObservable() throws InterruptedException {

        List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Observable.from(values) //发布多个数据
                .subscribeOn(Schedulers.computation()) // 在 computation 线程执行
                .subscribe(RxJavaDemo::println) // 订阅并且消费数据
        ;

        // 等待线程执行完毕
        Thread.sleep(100);

    }

    private static void demoSingle() {

        Single.just("Hello,World") // 仅能发布单个数据
                .subscribeOn(Schedulers.io()) // 在 I/O 线程执行
                .subscribe(RxJavaDemo::println) // 订阅并且消费数据
        ;
    }

    private static void println(Object value) {

        System.out.printf("[线程: %s] %s\n", Thread.currentThread().getName(), value);

    }
}
