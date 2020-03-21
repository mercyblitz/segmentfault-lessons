package com.segementfalut.deep.in.java.io;

import java.io.DataOutputStream;
import java.io.IOException;

public class DataStreamsDemo {

    public static void main(String[] args) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(System.out);
        outputStream.writeByte(65);
        outputStream.writeChar(97);
        outputStream.writeUTF("Hello,World");
        outputStream.flush();
    }
}
