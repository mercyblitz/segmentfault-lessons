package com.segmentfault.deep.in.java.collection.algorithm;

import java.util.stream.Stream;

public class InsertionSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] values) {
        // Comparable.compareTo
        // <  return -1
        // =  return 0
        // >  return 1
        int size = values.length;
        for (int i = 1; i < size; i++) {
            // 高位数 t
            // [3, 1, 2, 5, 4]
            // [j = 0] = 3, [i = 1] = 1 , t = [i = 1] = 1
            // [i = 1] = [j = 0] , [j = 0] = t = 1
            T t = values[i]; // 产生临时变量
            for (int j = i - 1; j >= 0; j--) {
                if (t.compareTo(values[j]) < 1) { // 高位 < 低位
                    values[i] = values[j]; // 高位获取低位的值
                    values[j] = t; // 低位得到高位的值
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] values = Sort.of(3, 1, 2, 5, 4);
        Sort<Integer> sort = new InsertionSort<>(); // Java 7 Diamond 语法
        sort.sort(values);
        Stream.of(values).forEach(System.out::println);
    }

}
