package com.segmentfault.deep.in.java.newfilesystem;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Java 7 开始流行
 * 1. Fluent API（Builder API，Chain API）
 * 2. 工具 API（Objects
 */
public class PathDemo {

    public static final String USER_DIR_LOCATION = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception {

//        displayPathInfo();
//        displayPathNormalize();

        displayPathConversation();
    }

    private static void displayPathConversation() {
        Path pathFromLocation = Paths.get(USER_DIR_LOCATION);
        File file = new File(USER_DIR_LOCATION);
        Path pathFromFile = file.toPath();
        Path pathFromURI = Paths.get(pathFromFile.toUri());
        System.out.println("pathFromURL : " + pathFromURI);
        System.out.println("pathFromLocation : " + pathFromLocation);
        System.out.println("pathFromFile : " + pathFromFile);
        System.out.println("pathFromURL == pathFromLocation ? " + pathFromURI.equals(pathFromLocation));
        System.out.println("pathFromFile == pathFromLocation ? " + pathFromFile.equals(pathFromLocation));
    }

    private static void displayPathNormalize() {
        Path path = Paths.get("D:\\workspace\\coures\\..\\");
        // D:\workspace\coures\java-new-filesystem
        // D:\workspace\coures\..\
        System.out.println(path.normalize());
    }

    private static void displayPathInfo() {
        Path path = Paths.get(USER_DIR_LOCATION);
        System.out.printf("toString : %s\n", path);
        System.out.printf("getFileName : %s\n", path.getFileName());

        int nameCount = path.getNameCount();
        for (int i = 0; i < nameCount; i++) {
            System.out.printf("getName(%d) : %s\n", i, path.getName(i));
        }
        for (Path p : path) {
            System.out.printf("For-Each path : %s\n", p);
        }
        System.out.printf("getParent : %s\n", path.getParent());
        System.out.printf("getRoot : %s\n", path.getRoot());

        FileSystems.getDefault().getRootDirectories().forEach(System.out::println);
    }
}
