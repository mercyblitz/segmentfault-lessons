package com.segmentfault.deep.in.java.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericTypeErasureDemo {

    public static void main(String[] args) {

        List<String> values = new ArrayList<>();
        // Cast : String value =  (String) values.get(0);
        String value = values.get(0);

        A<String> a = new A<>();

        a.equals(a);

        C c = new C();

        c.compareTo(c);
    }

    public static class A<T> {

    }

    public static class C implements Comparable<C> {

        @Override
        public int compareTo(C o) {
            return 0;
        }
    }
}
