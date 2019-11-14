package com.segementfalut.deep.in.java.io;

import java.util.Formatter;

public class FormattingDemo {

    public static void main(String[] args) {
        System.out.printf("Hello,%s\n", "World");
        System.out.print(String.format("Hello,%s\n", "World"));
        System.out.print(new Formatter().format("Hello,%s\n", "World"));
    }
}
