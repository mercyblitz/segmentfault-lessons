package com.segmentfault.deep.in.java.collection.algorithm;

import java.util.stream.Stream;

public class BubbleSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] values) {

        // Comparable.compareTo
        // <  return -1
        // =  return 0
        // >  return 1
        int size = values.length;

        // Given array : [3,1,2,5,4]
        // for 1          |
        // for 2            |
        for (int i = 0; i < size; i++) {
            // 第 i 号元素
            T t = values[i]; // 产生临时变量
            for (int j = i + 1; j < size; j++) {
                // 第 i 号元素与 i + 1 对比
                if (t.compareTo(values[j]) == 1) { // 低位 > 高位
                    // 交换元素 [i + 1] = [i]
                    values[i] = values[j];
                    values[j] = t;
                    // [0] = 3 , [1] = 2
                    // [1] = [1](2) + [0](3) = 5
                    // [0] = [1](5) - [0](3) = 2
                    // [1] = [1](5) - [0](2) = 3
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] values = Sort.of(3, 1, 2, 5, 4);
        Sort<Integer> sort = new BubbleSort<>(); // Java 7 Diamond 语法
        sort.sort(values);
        Stream.of(values).forEach(System.out::println);
    }

}
