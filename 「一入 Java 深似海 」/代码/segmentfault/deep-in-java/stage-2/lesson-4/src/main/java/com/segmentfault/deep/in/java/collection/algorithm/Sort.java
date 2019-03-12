package com.segmentfault.deep.in.java.collection.algorithm;

/**
 * 排序接口
 * <p>
 * 原地(在地)排序 - In-Place
 */
public interface Sort<T extends Comparable<T>> {

    void sort(T[] values);


    static <T> T[] of(T... values) {
        return values;
    }
}
