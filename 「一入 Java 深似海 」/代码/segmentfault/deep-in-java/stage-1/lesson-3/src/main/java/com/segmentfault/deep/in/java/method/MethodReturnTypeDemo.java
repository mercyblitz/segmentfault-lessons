package com.segmentfault.deep.in.java.method;

import java.util.*;
import java.util.stream.Stream;

public class MethodReturnTypeDemo {

    // 面向对象 -> 多态、封装、继承

    // 方法返回值（多态、封装）
    // 原则一：返回类型需要抽象（强类型），除 Object
    // 抽象返回类型的意义，调用方（接受方）容易处理
    // 越具体，越难通用。

    // 需求返回一个有序的，去重的字符串
    public TreeSet<String> getValues() { // 错误范例
        return new TreeSet<>();
    }

    // 动词 + 形容 + 名词
    public SortedSet<String> getSortedValues() { // 正确范例
        return new TreeSet<>();
    }

    // 如果返回的类型是集合的话，Collection 优于List 或 Set
    // 如果不考虑写操作，Iterable 优于 Collection

    // 原则二：尽可能地返回 Java 集合框架内的接口，尽量避免数组
    // 1. Collection 比较 [] 而言，拥有更多的操作方法，比如 add
    // 2. Collection 接口返回时，可以限制只读，而 [] 不行。
    public static List<Integer> getErrorNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        return numbers;
    }

    // 数组尽管确保长度不变的，无法保证只读
    private static Integer[] getNumbersArray() {
        return new Integer[]{1, 2, 3, 4, 5};
    }

    // 原则三：确保集合返回接口只读
    private static List<Integer> getNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        return Collections.unmodifiableList(numbers);
    }

    // 原则四：如果需要非只读集合返回的话，那么确保返回快照
    private static List<Integer> getNumbersSnapshot() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        return new ArrayList<>(numbers);
    }

    // 技巧：如果需要返回快照，尽可能地选择 ArrayList

    public static void main(String[] args) {
        List<Integer> numbers = getErrorNumbers();
        System.out.println(numbers);
        // 抛出 UnsupportedOperationException
        // numbers.add(6);
        // Arrays.asList 并非只读，尽管在 add 方法操作时，抛出异常
        numbers.set(4, 9);
        System.out.println(numbers);

        numbers = getNumbers();
//        numbers.set(4, 9);
        System.out.println(numbers);

        Integer[] values = getNumbersArray();
        println(values);
        values[2] = 0;
        println(values);
    }

    private static void println(Integer[] values) {
        for (Integer v : values) {
            System.out.print(v + " , ");
        }
        System.out.println();
    }


}
