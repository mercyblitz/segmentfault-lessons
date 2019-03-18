package com.segmentfault.deep.in.java.concurrency;

public class ThreadStarvationDemo {

    public static void main(String[] args) {
        new ThreadStarvationDemo();
    }

    public void finalize() { // FinalReference
        System.out.printf("Thread[%s] executes current object's finalization.\n", Thread.currentThread().getName());
    }
}
