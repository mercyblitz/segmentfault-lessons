package com.segmentfault.deep.in.java;

import java.io.Serializable;

public class InterfaceDemo {

    public static void main(String[] args) {

        // 强类型约束
        println(1); // 1 -> int => Integer <- Number
        // String 既是 CharSequence，也是 Serializable
        println((CharSequence) "Hello");
    }

    private static void println(Serializable id) {
        System.out.println(id);
    }

    private static void println(CharSequence cs) {
        System.out.println(cs.toString());
    }

    public interface A {
        int VALUE = 1;
    }
}
