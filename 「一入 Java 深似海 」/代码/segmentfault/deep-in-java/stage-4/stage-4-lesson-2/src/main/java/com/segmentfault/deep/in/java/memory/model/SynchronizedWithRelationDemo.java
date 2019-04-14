package com.segmentfault.deep.in.java.memory.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * synchronized-with Relation
 */
public class SynchronizedWithRelationDemo {

    private static final Object lock = new Object();

    public static void main(String[] args) {

    }

    private static volatile int sharedData;

    /**
     * An unlock action on monitor m synchronizes-with all subsequent lock actions on
     * m (where "subsequent" is defined according to the synchronization order)
     *
     * @param data
     */
    private static void synchronizedChangeData(int data) {
        // T1 和 T2 两个线程
        // T1 先获得锁
        // T1 lock -> run() -> unlock
        // T2 被停顿（park）
        // T3 进入（停顿）
        // T1 unlock
        // T2 或 T3 获得锁机会
        // T3 获得锁（T2 被停顿）
        // T3 lock -> unlock
        // 结果
        // T1 unlock -> T3 lock
        synchronized (lock) {
            sharedData = data;
        } // unlock the monitor(lock)
    }


    /**
     * A write to a volatile variable v (§8.3.1.4) synchronizes-with all subsequent
     * reads of v by any thread (where "subsequent" is defined according to the
     * synchronization order)
     * <p>
     * 假设
     * T(w) ：写线程
     * T(r) : 读线程
     * 且 T(w) 1 -> sharedData volatile 写 -> 0 => 1
     * T(r) 1...n -> sharedData volatile 读 -> 0 => 1
     */
    private static int getSharedData() {
        // volatile 读
        // sharedData(1)
        return sharedData;
    }

    private static void volatileChangeDate(int data) {
        // volatile 写
        // sharedData(0) -> 1
        sharedData = data;

        // volatile 读
        int tmpData = sharedData;
    }

    /**
     * An action that starts a thread synchronizes-with the first action in the thread it
     * starts.
     */
    private static void threadStart() {
        Thread thread = new Thread(() -> {
        });

        thread.start(); // Thread.start() 方法必然在 Thread.run() 方法之前执行
    }

    private static class Person {

        private final String name;

        private final int age;

        private final Collection<String> tags;

//        public Person() {
//            // name = null
//            // age = 0
//        }

        /**
         * 线程在读取 Person 对象属性（name 或 age）时，线程不会读到字段在初始化的中间状态
         *
         * @param name
         * @param age
         * @param tags
         */
        public Person(String name, int age, Collection<String> tags) {
            this.name = name; // String 是不变对象（引用）
            this.age = age;   // age 是原生类型（复制）
            this.tags = tags; // Collection 是可变对象（引用）
        }

    }


    private static void initializeProperties() {

        /**
         * Person 对象初始化完成后，才能被其他线程访问对象属性
         */
        List<String> tags = Arrays.asList("A", "B", "C");
        /**
         *  Java 方法参数特点
         *  对于对象类型，引用
         *  引用：普通对象、数组、集合（Collection、Map）
         *  对于原生类型，复制
         */
        Person person = new Person("小马哥", 33, tags);

        /**
         * 修改第三个元素 "C" -> "E"
         */
        tags.set(2, "E");

        Thread thread = new Thread(() -> {
            person.toString();
        });
    }


    private volatile boolean interrupted = false;

    private static void threadInterrupt() {

        Thread t2 = new Thread(() -> {
            if (Thread.interrupted()) { // volatile 读 t2 interrupt true and is cleared.
                // 会被执行
            }
        });

        Thread t1 = new Thread(() -> {
            // T1 调用 T2 interrupt() 方法
            t2.interrupt(); // volatile 写
            // t2 interrupt 状态 false -> true
        });


        Thread t3 = new Thread(() -> {
            if (t2.isInterrupted()) {  // volatile 读 t2 interrupt true
                // 会被执行
            }
        });

        // volatile 写 -> volatile 读
        // t1 -> interrupt t2 -> t3,t4,t4 read isInterrupted() == true

    }
}
