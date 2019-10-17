package com.segmentfault.deep.in.java.reflection;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.AbstractList;

/**
 * Java 类对象
 */
public class ClassObjectDemo {

    public static void main(String[] args) {
        // Class 对象
        // 具体类对象
        // 没有这类方法 isConcrete 是否为具体类
        System.out.println(!Modifier.isAbstract(Object.class.getModifiers()));
        // 抽象类对象
        System.out.println(Modifier.isAbstract(AbstractList.class.getModifiers()));
        // 接口类对象
        System.out.println(Serializable.class.isInterface());
        // 枚举类对象
        System.out.println(Color.class.isEnum());
        // 注解类对象
        System.out.println(ConstructorProperties.class.isAnnotation());
        // 原生类对象
        System.out.println(int.class.isPrimitive());
        System.out.println(double.class.isPrimitive());
        // 数组类对象
        System.out.println(int[].class.isArray());
        System.out.println(Object[].class.isArray());
        // 特殊类型
        System.out.println(void.class);
    }
}
