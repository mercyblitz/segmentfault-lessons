package com.segmentfault.deep.in.java.concurrency;

public class ConcurrentHashMapDemo {

    public static void main(String[] args) {

        // ConcurrentHashMap 读多写少场景
        // 1.6 读（部分锁） -> 1.7 无锁（Segment） -> 1.8 无锁（红黑树——更好地解决 Hash 冲突）
        // ConcurrentSkipListMap 读多写多场景（如果内存足够时）

        //  ConcurrentHashMap
        // table 2^n ->
        // n = 1 -> size = 2
        // n = 2 -> size = 2^2 = 4
        // n = 3 -> size = 2^3 = 8 TREEIFY_THRESHOLD
        // log2(8) = 3
        // [0] -> [1] -> [2]
        // [0] -> [1]
        //     -> [2]
        // n = 4 -> size = 2^4 = 16
    }
}
