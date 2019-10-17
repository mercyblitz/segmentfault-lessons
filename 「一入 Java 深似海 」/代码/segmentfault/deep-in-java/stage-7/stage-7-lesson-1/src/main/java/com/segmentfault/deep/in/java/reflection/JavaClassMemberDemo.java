package com.segmentfault.deep.in.java.reflection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class JavaClassMemberDemo {

    private int value;

    public static void main(String[] args) {
        // Java 类成员对象来自于 java.lang.Class
        // 成员访问性
        // 所有成员 getDeclaredXXX() ->
        // getDeclaredMethods()
        // getDeclaredFields()
        // getDeclaredConstructors

        // getDeclaredFields() - 所有声明的字段（仅限当前类） 与 getFields() - public 字段（仅限当前类）区别
        List<Field> allDeclaredFields = getAllDeclaredFields(ExtendedData.class);
        Field[] declaredFields = ExtendedData.class.getDeclaredFields();
        Field[] fields = ExtendedData.class.getFields();
        System.out.println("ExtendedData 类所有层次的声明字段 : " + allDeclaredFields.stream().map(Field::getName).collect(Collectors.joining(",")));
        System.out.println("ExtendedData 类所有的声明字段 : " + Stream.of(declaredFields).map(Field::getName).collect(Collectors.joining(",")));
        System.out.println("ExtendedData 类所有的 public 字段 : " + Stream.of(fields).map(Field::getName).collect(Collectors.joining(",")));
    }

    static List<Field> getAllDeclaredFields(Class<?> type) {
        if (Object.class.equals(type)) {
            return Collections.emptyList();
        }
        List<Field> allDeclaredFields = new LinkedList<>();
        Field[] declaredFields = type.getDeclaredFields();
        allDeclaredFields.addAll(asList(declaredFields));
        Class<?> superClass = type.getSuperclass();

        while (true) {
            if (superClass == null || Object.class.equals(superClass)) {
                break;
            }
            allDeclaredFields.addAll(getAllDeclaredFields(superClass));
            superClass = superClass.getSuperclass();
        }

        return allDeclaredFields;
    }

    public JavaClassMemberDemo() {

    }

    class Data {

        private int value;

        public int getValue() {
            return value;
        }

        protected void setValue(int value) {
            this.value = value;
        }
    }

    class ExtendedData extends Data {

        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }

}


