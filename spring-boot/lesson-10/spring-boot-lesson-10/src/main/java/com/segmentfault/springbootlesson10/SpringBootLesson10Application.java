package com.segmentfault.springbootlesson10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class SpringBootLesson10Application {

    //lock-free
//    private volatile int data;
//
    private synchronized void doSome() {

        Lock outterLock = new ReentrantLock();
        Lock innterLock = new ReentrantLock();

        try {
            outterLock.tryLock();
            innterLock.tryLock();
        } finally {
            innterLock.unlock();
            outterLock.unlock();
        }

    }


    public static void main(String[] args) {
        SpringApplication.run(SpringBootLesson10Application.class, args);

//        Integer var = new Integer(1);
//        Integer var2 = new Integer(1);
//        System.out.println(var.hashCode());
//        System.out.println(var2.hashCode());
//
//        System.out.println(System.identityHashCode(var));
//        System.out.println(System.identityHashCode(var2));

    }
}
