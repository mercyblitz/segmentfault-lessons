package com.segmentfault.deep.in.java.functional;

import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {

        Function<String, Long> stringToLong = Long::valueOf;

        System.out.println(stringToLong.apply("1"));

        Function<Long, String> longToString = String::valueOf;

        System.out.println(longToString.apply(1L));

        // 1L -> "1" -> 1L
        Long value = stringToLong.compose(String::valueOf).apply(1L);
        // 稍微解释一下
        //stringToLong = Long::valueOf
        // function<T,R> 接口 的作用就是转换,
        /*
         *@param <T> the type of the input to the function
         *@param <R> the type of the result of the function
         *
         */
    }
}
