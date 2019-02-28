package com.segmentfault.deep.in.java.collection.algorithm;

import java.util.stream.Stream;

public class QuickSort<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] values) {
        int n = values.length;
        int low = 0;
        int high = n - 1;

        // [3, 1, 2, 5, 4]
        // pivot = 4
        // => [(3, 1, 2), (4), (5)]
        // pIndex = 3
        // [0...2] = (3, 1, 2)
        // [3] = 4
        // [4] = 5

        // [0...2] = (3, 1, 2)
        // pivot = 2
        // => [(1), (2) , (3)]
        // pIndex = 1
        // [0] = 1
        // [1] = 2(pivot)
        // [2] = 3

        // [0] = 1, [1] = 2, [2] = 3, [3] = 4, [4] = 5

        sort(values, low, high);
    }

    private void sort(T[] values, int low, int high) {
        if (low < high) {
            // 9 -> pIndex = 5
            int pIndex = partition(values, low, high);
            // [0..4]
            sort(values, low, pIndex - 1);
            sort(values, pIndex + 1, high);
        }
    }

    /**
     * 获取分区索引
     *
     * @param values 数组对象
     * @param low    低位索引
     * @param high   高位索引
     * @return 分区索引
     */
    int partition(T[] values, int low, int high) {
        // 获取 pivot = values[high]

        // [3, 1, 2, 5, 4]
        // pivot = 4
        //              -1
        // [0] = 3 < 4 (0)
        // [1] = 1 < 4 (1)
        // [2] = 2 < 4 (2)
        // [3] = 5 > 4 (3)
        // => [(3, 1, 2), (4), (5)]
        // pIndex = 3

        T pivot = values[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (values[j].compareTo(pivot) < 1) { // <=
                i++; // -1 -> 0
                T temp = values[i]; // 低位数据
                values[i] = values[j]; // 低位数据获取高位数据
                values[j] = temp;
            }
        }

        T temp = values[i + 1];
        values[i + 1] = values[high];
        values[high] = temp;

        return i + 1; // 游标+1
    }

    public static void main(String[] args) {
        Integer[] values = Sort.of(3, 1, 2, 5, 4);
        Sort<Integer> sort = new QuickSort<>(); // Java 7 Diamond 语法
        sort.sort(values);
        Stream.of(values).forEach(System.out::println);
    }
}
