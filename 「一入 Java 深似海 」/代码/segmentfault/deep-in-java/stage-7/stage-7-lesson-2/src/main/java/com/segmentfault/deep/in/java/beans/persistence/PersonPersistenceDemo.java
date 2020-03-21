package com.segmentfault.deep.in.java.beans.persistence;

import com.segmentfault.deep.in.java.beans.properties.Person;

import java.io.*;
import java.util.Date;
import java.util.stream.Stream;

public class PersonPersistenceDemo {

    public static void main(String[] args) throws Exception {

        Person person = new Person();
        person.setId(1L);
        person.setName("小马哥");
        person.setAge(34);
        person.setUpdateTime(new Date());

        String classPath = findClassPath();
        OutputStream outputStream = new FileOutputStream(new File(classPath,"person.tmp"));
        // Java 标注序列化
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(person);

    }

    private static String findClassPath() {
        String classPath = System.getProperty("java.class.path");
        return Stream.of(classPath.split(File.pathSeparator))
                .map(File::new)
                .filter(File::isDirectory)
                .filter(File::canRead)
                .filter(File::canWrite)
                .map(File::getAbsolutePath)
                .findFirst()
                .orElse(null);
    }
}
