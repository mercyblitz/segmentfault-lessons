package com.segmentfault.deep.in.java.collection.algorithm;

import java.util.Arrays;

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
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                // 第 j 号元素与 j + 1 对比
                if (values[j].compareTo(values[j + 1]) == 1) { // 低位 > 高位
                    // 交换元素
                    T t = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = t;
                    // [0] = 3 , [1] = 2
                    // [1] = [1](2) + [0](3) = 5
                    // [0] = [1](5) - [0](3) = 2
                    // [1] = [1](5) - [0](2) = 3
                }
            }
            System.out.printf("第%d轮：%s\n", i + 1, Arrays.toString(values));
        }
    }

    public static void main(String[] args) {
        System.out.println("一般情况");
        Integer[] values = Sort.of(3, 1, 2, 5, 4);
        Sort<Integer> sort = new BubbleSort<>(); // Java 7 Diamond 语法
        sort.sort(values);
        System.out.printf("排序结果：%s\n", Arrays.toString(values));

        System.out.println("完全逆序");
        values = Sort.of(5, 4, 3, 2, 1);
        sort = new BubbleSort<>();
        sort.sort(values);
        System.out.printf("排序结果：%s\n", Arrays.toString(values));
    }

}
