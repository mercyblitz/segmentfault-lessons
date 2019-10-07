package com.segmentfault.deep.in.java.concurrency;

public class AcquireAndReleaseDemo {

    public static void main(String[] args) {
        //　Lock　机制
        // 获得 Acquire
            // Thread 进入 synchronized -> 获得锁
        // 释放 Release
            // 1. 当 Thread（hold lock），调用 Object#wait() 时候
            // 2. Thread park -> LockSupport.park(Object)
            // 3. Condition#await()
            // 4. 运行期异常，Thread 消亡
            // 5. Java 9 自旋 Thread.onSpinWait();
            // 6. Thread.yield();

        // 所谓的公平（Fair）和非公平（Nonfair、unfair）
        // 公平（Fair）线程 FIFO
        // 非公平（Nonfair）线程随线程调度
        // 最佳实践：在创建线程时，除非必要，不要调整线程优先级（Priority）
    }
}
