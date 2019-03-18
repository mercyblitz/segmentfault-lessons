package com.segmentfault.deep.in.java;

import java.lang.reflect.Field;

public class StringDemo {

    public static void main(String[] args) throws Exception {

        // 常量化是原生类型支持，赋值方式
        int a = 1; // 常量
        String value = "Hello"; // 常量（语法特性） = 对象类型常量化
        // C 写法 char* var = malloc(length(char) * 5);
        // var ="Hello";

        // 面向对象规则：一切对象需要 new
        // 合法的写法（会被视作异类）
        String value2 = new String("Hello");

        System.out.println("value :　"+value);

        System.out.println("value2 :　"+value);

        // 从 Java 1.5 开始对象属性可以通过反射修改
        char[] chars = "World".toCharArray();

        // 获取 String 类中的 value 字段
        Field valueFiled = String.class.getDeclaredField("value");
        // 设置 private 字段可以被修改
        valueFiled.setAccessible(true);
        // 把 chars 设置到 value 字段的内容
        valueFiled.set(value2, chars);

        System.out.println("value :　"+value);

        System.out.println("value2 :　"+value2);

    }

    public static class ExtendableString {

        private final char value[];

        /**
         * Cache the hash code for the string
         */
        private int hash; // Default to 0

        public ExtendableString(String value) {
            this.value = value.toCharArray();
        }

        public final int hashCode() {
            int h = hash;
            if (h == 0 && value.length > 0) {
                char val[] = value;

                for (int i = 0; i < value.length; i++) {
                    h = 31 * h + val[i];
                }
                hash = h;
            }
            return h;
        }

        public final boolean equals(Object object) {
            return true;
        }
    }
}
