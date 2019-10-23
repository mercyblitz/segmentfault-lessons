package com.segmentfault.deep.in.java.memory.model;

public class JavaMemoryModelDemo {

    /**
     * 广义同步：狭义锁（互斥）、volatile 以及原子操作（Unsafe）
     * Java9+ VarHandle
     */

    /**
     * 狭义锁（互斥）：
     *  OS 原语（Windows）：
     *      HANDLE mutex = CreateMutex(NULL, FALSE, NULL);
     *      CRITICAL_SECTION critSec;
     *  POSIX Thread 等高级 API：
     *      pthread_mutex_t 数据结构
     */

    /**
     * volatile
     * 确保：
     *      变量的可见性
     *      引用的原子性：https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html
     * 实现：
     *      大部分利用 C++ volatile 编译时限制重排（内存屏障）
     *          Memory Barriers：https://www.infoq.com/articles/memory_barriers_jvm_concurrency
     *      部分通过汇编实现
     *      源码快速路径：orderAccess.hpp
     *
     */

    /**
     * 原子操作（Atomic）
     * 确保：
     *      变量的原子操作（自增长、exchange、CAS 等操作）
     * 实现：
     *      利用汇编指令
     *      源码快速路径：atomic.hpp
     */
}
