package com.segementfalut.deep.in.java.io;

import java.io.StringReader;
import java.util.Scanner;

public class ScanningDemo {

    public static void main(String[] args) {
        StringReader reader = new StringReader("1 2 3 4 5");
        // 默认分隔符是 \n
        Scanner scanner = new Scanner(reader);
        // 替换分隔符
        scanner.useDelimiter("\\s");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
