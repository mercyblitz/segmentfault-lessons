# Spring Cloud Hystrix 源码分析





## Spring Cloud Hystrix 源码解读



### `@EnableCircuitBreaker`

职责：

* 激活 Circuit Breaker



#### 初始化顺序

* `@EnableCircuitBreaker `
  * `EnableCircuitBreakerImportSelector`
    * `HystrixCircuitBreakerConfiguration`



### HystrixCircuitBreakerConfiguration



#### 初始化组件

* `HystrixCommandAspect`
* `HystrixShutdownHook`
* `HystrixStreamEndpoint` ： Servlet 
* `HystrixMetricsPollerConfiguration`



## Netflix Hystrix 源码解读

### `HystrixCommandAspect`

#### 依赖组件

* `MetaHolderFactory`
* `HystrixCommandFactory`: 生成`HystrixInvokable`
* `HystrixInvokable`
  * `CommandCollapser`
  * `GenericObservableCommand`
  * `GenericCommand`



###  Future 实现 服务熔断

```java
package com.segumentfault.springcloudlesson9.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 通过 {@link Future} 实现 服务熔断
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 */
public class FutureCircuitBreakerDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 初始化线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        RandomCommand command = new RandomCommand();

        Future<String> future = executorService.submit(command::run);

        String result = null;
        // 100 毫秒超时时间
        try {
            result = future.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            // fallback 方法调用
            result = command.fallback();
        }

        System.out.println(result);

        executorService.shutdown();

    }

    /**
     * 随机对象
     */
    private static final Random random = new Random();

    /**
     * 随机事件执行命令
     */
    public static class RandomCommand implements Command<String> {


        @Override
        public String run() throws InterruptedException {

            long executeTime = random.nextInt(200);

            // 通过休眠来模拟执行时间
            System.out.println("Execute Time : " + executeTime + " ms");

            Thread.sleep(executeTime);

            return "Hello,World";
        }

        @Override
        public String fallback() {
            return "Fallback";
        }
    }


    public interface Command<T> {

        /**
         * 正常执行，并且返回结果
         *
         * @return
         */
        T run() throws Exception;

        /**
         * 错误时，返回容错结果
         *
         * @return
         */
        T fallback();

    }
}
```





## RxJava 基础



### 单数据：Single



```java
 Single.just("Hello,World") // 仅能发布单个数据
        .subscribeOn(Schedulers.io()) // 在 I/O 线程执行
        .subscribe(RxJavaDemo::println) // 订阅并且消费数据
;
```



### 多数据：Observable



```java
List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

Observable.from(values) //发布多个数据
        .subscribeOn(Schedulers.computation()) // 在 I/O 线程执行
        .subscribe(RxJavaDemo::println) // 订阅并且消费数据
;

// 等待线程执行完毕
Thread.sleep(100);
```



### 使用标准 Reactive 模式



```java
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
```





## Java 9 Flow API

```java
package concurrent.java9;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * {@link SubmissionPublisher}
 *
 * @author mercyblitz
 **/
public class SubmissionPublisherDemo {

    public static void main(String[] args) throws InterruptedException {

        try (SubmissionPublisher<Integer> publisher =
                     new SubmissionPublisher<>()) {

            //Publisher(100) => A -> B -> C => Done
            publisher.subscribe(new IntegerSubscriber("A"));
            publisher.subscribe(new IntegerSubscriber("B"));
            publisher.subscribe(new IntegerSubscriber("C"));

            // 提交数据到各个订阅器
            publisher.submit(100);

        }


        Thread.currentThread().join(1000L);

    }

    private static class IntegerSubscriber implements
            Flow.Subscriber<Integer> {

        private final String name;

        private Flow.Subscription subscription;

        private IntegerSubscriber(String name) {
            this.name = name;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.printf(
                    "Thread[%s] Current Subscriber[%s] " +
                            "subscribes subscription[%s]\n",
                    Thread.currentThread().getName(),
                    name,
                    subscription);
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(Integer item) {
            System.out.printf(
                    "Thread[%s] Current Subscriber[%s] " +
                            "receives item[%d]\n",
                    Thread.currentThread().getName(),
                    name,
                    item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete() {
            System.out.printf(
                    "Thread[%s] Current Subscriber[%s] " +
                            "is completed!\n",
                    Thread.currentThread().getName(),
                    name);
        }

    }

}
```

