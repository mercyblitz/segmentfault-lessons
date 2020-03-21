package com.segementfalut.deep.in.java.io;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class InputStreamToReaderDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {
        InputStream inputStream = System.in;
        // 转化为 Reader
        // InputStreamReader 属于适配器模式
        // 适配器模式< IN 类型，OUT 类型 >
        // 适配对象属于 OUT 类型，依赖注入 IN 类型，IN 类型和 OUT 类型没有直接层次关系
        InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
    }
}
