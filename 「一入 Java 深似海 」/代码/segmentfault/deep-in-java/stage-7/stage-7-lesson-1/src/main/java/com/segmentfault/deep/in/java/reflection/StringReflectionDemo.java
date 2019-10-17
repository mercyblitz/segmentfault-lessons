package com.segmentfault.deep.in.java.reflection;

import java.lang.reflect.Field;

public class StringReflectionDemo {

    /**
     * Java 5 开始反射可以修改对象属性，即使它被 final 修饰
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String testContent = "Hello,World";
        String otherValue = "小马哥";
        // 修改前的内容输出
        System.out.println("反射修改前的 testContent = " + testContent);
        //  private final char value[];
        // 获取名为 "value" 的 private 字段
        Field valueField = String.class.getDeclaredField("value");
        // 关闭访问性检查
        valueField.setAccessible(true);
        // 替换 testContent String 对象中 value[];
        valueField.set(testContent, otherValue.toCharArray());
        // 修改后的内容输出
        System.out.println("反射修改后的 testContent = " + testContent);
    }
}
