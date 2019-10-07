package com.segmentfault.deep.in.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {

        ReadWriteLock lock = new ReentrantReadWriteLock();

        Lock readLock = lock.readLock();

        Lock writeLock = lock.writeLock();

    }
}
