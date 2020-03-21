package com.segementfalut.deep.in.java.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectStreamsDemo {

    public static void main(String[] args) throws Throwable {
        List<String> strings = new ArrayList<>(Arrays.asList("A", "B", "C"));
        // new File(System.getProperty("user.dir"),"strings.ser")
        File file = new File("strings.ser");

        // 对象序列化
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(strings);
            outputStream.flush();
        }

        // 对象反序列化
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            List<String> copyStrings = (List) inputStream.readObject();
            System.out.println(copyStrings);
        }
    }
}
