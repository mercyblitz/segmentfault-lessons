package com.segmentfault.deep.in.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    public static void main(String[] args) {
        // Java 1.8 之后提供
        StampedLock lock = new StampedLock();
        long stamp = lock.tryOptimisticRead();
        Lock readLock =  lock.asReadLock();
        try {
            readLock.lock();

            lock.validate(stamp);
        } finally {
            readLock.unlock();
        }
    }
}
