package com.segmentfault.deep.in.java;

public class IntegerDemo {

    public static void main(String[] args) {

        //final 修饰的字段符合java内存模型
        Integer value = 99;
        Integer value2 = new Integer(99);
        Integer value3 = Integer.valueOf(99);
        //Integer.equals比较的是值
        System.out.println("value.equals(value2):" + value.equals(value2));//true
        System.out.println("value.equals(value3):" + value.equals(value3));//true

        System.out.println("value == value2:" + (value == value2));//false
        System.out.println("value == value3:" + (value == value3));//true

        //问：如何不改代码，上面的结果都返回false？
        //答：通过调整java.lang.Integer.IntegerCache.high参数 JAVA_OPTS="-Djava.lang.Integer.IntegerCache.high=50" 还是 -XX:AutoBoxCacheMax=<size> ？

    }
}
