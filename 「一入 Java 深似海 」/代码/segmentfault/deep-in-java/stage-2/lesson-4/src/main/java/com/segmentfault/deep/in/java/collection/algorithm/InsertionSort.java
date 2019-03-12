package com.segmentfault.deep.in.java.collection.algorithm;

import java.util.Arrays;

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
            int j = i;
            while (j > 0 && t.compareTo(values[j - 1]) < 0) {
                //往后移动让出插入空间
                values[j] = values[j - 1];
                j--;
            }
            //插入values[i]到对应位置
            values[j] = t;
            System.out.printf("第%d轮：%s\n", i, Arrays.toString(values));
        }
    }

    public static void main(String[] args) {
        System.out.println("一般情况");
        Integer[] values = Sort.of(3, 2, 1, 5, 4);
        Sort<Integer> sort = new InsertionSort<>();
        sort.sort(values);
        System.out.println(Arrays.toString(values));

        System.out.println("完全逆序");
        values = Sort.of(5, 4, 3, 2, 1);
        sort = new InsertionSort<>();
        sort.sort(values);
        System.out.println(Arrays.toString(values));
    }

}
