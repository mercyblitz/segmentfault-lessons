package com.segmentfault.deep.in.java.beans.introspection;

import com.segmentfault.deep.in.java.beans.properties.Person;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

public class PersonIntrospectionDemo {

    public static void main(String[] args) throws IntrospectionException {
        // 排除 java.lang.Object.class 类定义的干扰
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        // 获取 BeanDescriptor
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println("Person 类的 BeanDescriptor ：" + beanDescriptor);
        System.out.println("============================================================================");
        // 获取所有属性描述符 PropertyDescriptor
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    System.out.println("Person 类的 PropertyDescriptor ：" + propertyDescriptor);
                });

        System.out.println("============================================================================");
        // 获取所有方法描述 MethodDescriptor
        Stream.of(beanInfo.getMethodDescriptors())
                .forEach(methodDescriptor -> {
                    System.out.println("Person 类的 MethodDescriptor ：" + methodDescriptor);
                });

        System.out.println("============================================================================");

        // 获取所有事件集合描述符 EventSetDescriptor
        Stream.of(beanInfo.getEventSetDescriptors())
                .forEach(eventSetDescriptor -> {
                    System.out.println("Person 类的 EventSetDescriptor ：" + eventSetDescriptor);
                });
    }
}
