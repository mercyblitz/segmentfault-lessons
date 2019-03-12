package com.segmentfault.deep.in.java.collection.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CheckedTypeCollectionDemo {

    public static void main(String[] args) {
        // List 元素类型是 java.lang.Integer
        List<Integer> values = new ArrayList<>(Arrays.asList(1, 2, 3));
//        values.add("1"); // 编译错误
        // 泛型是编译时检查，运行时擦写

        // 引用 List<Integer> 类型的对象 values
        List referencedValues = values;

        System.out.println(referencedValues == values);

        referencedValues.add("A"); // 添加 "A" 进入 List<Integer> values

        // 运行时的数据 List<Integer>  == List<Object> == List
        // values.add("1") // 运行时允许，因为成员类型是 Object

        for (Object value : values) {
            System.out.println(value);
        }

        // values
        // [0] = 1, [1] = 2, [2] = 3, [3] = "A"
        // 创建时尚未检查内部的数据是否类型相同，操作时做检查，Wrapper 模式（装饰器模式）的运用
        // Collections.checked* 接口是弥补泛型集合在运行时中的擦写中的不足
        // 强约束：编译时利用 Java 泛型、运行时利用  Collections.checked* 接口
        List<Integer> checkedTypeValues = Collections.checkedList(values, Integer.class);
//        checkedTypeValues.add("1"); // 编译错误
        // 运行时检查

        // 又将 checkedTypeValues 引用给 referencedValues
        referencedValues = checkedTypeValues;

        System.out.println(referencedValues == values);

        System.out.println(referencedValues == checkedTypeValues);

        // 添加 "B" 进入 referencedValues
        referencedValues.add("B");


    }
}
