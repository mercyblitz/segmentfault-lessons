package com.segmentfault.deep.in.java.generic;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassCastDemo {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add("A");

        Type type = int.class;

        Class intType = int.class;

        List<Object> list2 = list;

        List<String> strList = Collections.emptyList();
        List<String> strList2 = Arrays.asList("1", "2", "3");
        // Diamond 语法
        List<String> strList3 = new ArrayList<>();
        // Java 8 var
        // var strList = new ArrayList<>();

    }

    /**
     * a String List
     * b Integer List
     * String -> Integer
     * for-each
     *
     * @param a
     * @param b
     */
    private static void exchange(List a, List b) {
        a.addAll(b);
        Integer v = (Integer) a.get(0);

    }
}
