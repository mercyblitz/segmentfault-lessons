package com.segmentfault.deep.in.java.reflection;

import java.beans.ConstructorProperties;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class JavaTypesDemo {

    @ConstructorProperties("v")
    public JavaTypesDemo(String v){

    }

    public static void main(String[] args) {
        List<Integer> values = asList(1, 2, 3);
        System.out.println(values.getClass().toGenericString());

        System.out.println(StringList.class.getGenericSuperclass().toString());

        Color.values(); // 虚拟机动态生成的方法
    }
}

class StringList extends ArrayList<String> {

}

//class Numbers extends java.lang.Enum<Numbers> {
//
//    protected Numbers(String name, int ordinal) {
//        super(name, ordinal);
//    }
//}

//interface One extends java.lang.annotation.Annotation {
//
//}

