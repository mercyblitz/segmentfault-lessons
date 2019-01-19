package com.segmentfault.deep.in.java.method;

import java.util.Collection;

public class MethodArgumentsDemo implements Comparable<MethodArgumentsDemo> {

    public static void main(String[] args) {
        // arguments  通常形容方法参数
        // parameters 通常是指外部媒介传递过来，请求参数
    }

    private int value;

    // 方法参数名称设计
    public boolean equals(Object that) {
        return this.value == ((MethodArgumentsDemo) that).value;
    }

//    public boolean equals(Object another) {
//        return this.value == ((MethodArgumentsDemo) another).value;
//    }

    @Override
    public int compareTo(MethodArgumentsDemo another) {
        return 0;
    }

    // 单项传输 - 参数类型对等方式
    public void copy(Object source, Object target) {

    }

    // 单项传输 - 参数类型非对等
    public void add(Collection<Object> collection, Object element) {

    }

    // 多项传输 - 参数类型非对等
    /**
     * @param collection collection
     * @param elements   zero or more elements (0..n)
     */
    public void add(Collection<Object> collection, Object... elements) {

    }

    // 多项传输 - 参数类型非对等
    /**
     * @param collection collection
     * @param one        one element
     * @param others     more elements (0..n)
     */
    public void add(Collection<Object> collection, Object one, Object... others) {

    }

    // 多项传输 - 参数类型对等
    public void addAll(Collection<Object> destination, Collection<Object> source) {
        destination.addAll(source);
    }

    // Effective Java 建议不要超过四个参数
    // Java 8 Lambda 告诉用户，最多使用三个
    // Runnable(Action) 零个
    // Consumer 一个
    // Function BiConsumer 两个
    // BiFunction 三个

}
