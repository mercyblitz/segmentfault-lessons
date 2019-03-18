package com.segmentfault.java.lesson1;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GenericTypeDemo {

    /**
     * @param <S> 来源类型
     * @param <T> 转换类型
     */
    public interface Converter<S, T extends Serializable> {

        T convert(S source);
    }

    public interface StringConverter<T extends Serializable> extends Converter<String,T> {

    }


    public static void main(String[] args) {

        Converter<Integer, String> stringConverter =
                new Converter<Integer, String>() {
                    @Override
                    public String convert(Integer source) {
                        return String.valueOf(source);
                    }
                };

        new Converter<String, Integer>() {

            @Override
            public Integer convert(String source) {
                return Integer.valueOf(source);
            }
        };

        List<String> strings;

        List<Integer> integers;

        List<String> data = Collections.emptyList();

        List data2 = Collections.emptyList();

        data = data2;
    }

    /**
     * 方法签名   #doConvert(List)
     *
     * @param value
     */
    public static void doConvert(List<String> value) {

    }

    // 方法签名冲突, 方法签名   #doConvert(List)
//    public static void doConvert(List value) {
//
//    }

    public static void doConvert(List<Integer> value, int data) {

    }
}
