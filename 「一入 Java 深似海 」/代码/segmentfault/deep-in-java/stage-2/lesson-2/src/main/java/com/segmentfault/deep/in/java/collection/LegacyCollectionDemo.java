package com.segmentfault.deep.in.java.collection;

import java.util.*;

public class LegacyCollectionDemo {

    public static void main(String[] args) {

        // 几乎所有遗留集合实现是线程安全
        vectorVsList();
        vectorVsStack();
        hashtableVsHashMap();
        enumerationVsEnum();
        bitSet();
    }

    private static void bitSet() {
        // BitSet 用于位运算集合操作，可以搭配 NIO ByteBuffer
    }

    private static void enumerationVsEnum() {
        // Enumeration 主要用于迭代早期实现，由于 java.util.Iterator 从 Java 1.2 引入。
        String value = "1,2,3";

        StringTokenizer tokenizer = new StringTokenizer(value, ",");

        Enumeration enumeration = tokenizer;

        while (enumeration.hasMoreElements()) { // 等价 Iterator.hasNext()
            String element = String.valueOf(enumeration.nextElement()); // 等价 Iterator.next();
            System.out.println(element);
        }

        // Iterable 从 Java 5 引入，用于 for-each 语句语法提升
    }

    private static void hashtableVsHashMap() {
        // Hashtable 与  HashMap
        // Hashtable
        //  实现 Dictionary 和 Map 接口
        //  线程安全
        //  key 与 value 不允许 null

        // HashMap 实现 Map 接口
        //  线程非安全（写操作
        // key 与 value 允许 null 的 value

        // 特殊：ConcurrentHashMap
        //  key 与 value 不允许 null
        // 如果 value 为空的话，ConcurrentHashMap 在查询数据时，会产生歧义
        // 到底是值为 null，还是线程不可见

        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put(null, null);
    }

    private static void vectorVsStack() {
        // Vector 是 FIFO
        // Stack 是 LIFO，是 Vector 的子类
    }

    private static void vectorVsList() {
        // Vector 数组实现，对比 ArrayList，实现了 List
        // Vector 所有操作线程安全的，使用关键字“synchronized”修饰
        Vector<String> vector = new Vector<>();
        List<String> list = new ArrayList<>();
        // 如果 Vector 在方法内部使用的话， synchronized 修饰后的方法基本上没有线程同步的消耗
        vector.add("A");
        list.add("A");
    }
}
