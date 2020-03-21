package com.segmentfault.deep.in.java.filesystem;

import java.io.FileDescriptor;
import java.lang.reflect.Field;

public class FileDescriptorDemo {

    public static void main(String[] args) throws Exception {
        displayFileDescriptor(FileDescriptor.in);
        displayFileDescriptor(FileDescriptor.out);
        displayFileDescriptor(FileDescriptor.err);
    }

    private static void displayFileDescriptor(FileDescriptor fileDescriptor) throws Exception {
        Integer fd = getFieldValue(fileDescriptor, "fd");
        Long handle = getFieldValue(fileDescriptor, "handle");
        Boolean closed = getFieldValue(fileDescriptor, "closed");
        System.out.printf("FileDescriptor[ fd : %d , handle %d , closed : %s ]\n", fd, handle, closed);
    }

    private static <T> T getFieldValue(FileDescriptor fileDescriptor, String fieldName) throws Exception {
        Field field = FileDescriptor.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(fileDescriptor);
    }
}
