package com.segmentfault.deep.in.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class UnmodifiableInterfaceDemo {

    public static void main(String[] args) {

        Collection<Integer> values = of(1, 2, 3, 4, 5);

        values.add(6);

        System.out.println(values);

        values = unmodifiable(1, 2, 3, 4, 5);

        // 不允许修改
        // 线程安全（读或者写都是数据一致）
        // 只读必然是线程安全
        values.add(6);

    }

    public static Collection<Integer> of(Integer... values) {
        return new ArrayList<Integer>(Arrays.asList(values));
    }

    public static Collection<Integer> unmodifiable(Integer... values) {
        return Collections.unmodifiableList(Arrays.asList(values));
    }
}
