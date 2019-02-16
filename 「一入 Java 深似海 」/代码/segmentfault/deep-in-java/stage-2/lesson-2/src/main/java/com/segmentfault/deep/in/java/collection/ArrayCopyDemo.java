package com.segmentfault.deep.in.java.collection;

import java.util.List;

public class ArrayCopyDemo {

    public static void main(String[] args) {

        String[] strings1 = new String[]{"Hello", "World"};

        arraycopy(strings1, strings1);

        // value[1] = 1
        // value['a'] = a

        int value = Integer.MAX_VALUE;

        System.out.println(value + 1);
        System.out.println(value + 1 == Integer.MIN_VALUE);
        System.out.println(value + 2 == Integer.MIN_VALUE + 1);
        // int 在 Java 只有 4 字节（32位）

        // OS 32位（4字节） 以及 64位（8字节）
        // long 和 double 是非线程安全的，两个4字节，高和低位
        // Java 中默认是没有正整数（无符号整数）
        // C unsigned int(size_t)

        List<String> values = List.of();

        // values.size() == 0
        // values.size() < 1

    }

    public static void arraycopy(Object[] src, Object[] destination) {
    }

}
