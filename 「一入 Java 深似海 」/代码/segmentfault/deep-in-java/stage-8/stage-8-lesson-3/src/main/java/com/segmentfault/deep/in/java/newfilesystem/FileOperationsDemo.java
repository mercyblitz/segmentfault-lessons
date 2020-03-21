package com.segmentfault.deep.in.java.newfilesystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.segmentfault.deep.in.java.newfilesystem.PathDemo.USER_DIR_LOCATION;

public class FileOperationsDemo {

    public static void main(String[] args) throws Exception {
        displayFileExists();
        displayFileAccessibility();
        displayFileEquals();
    }

    private static void displayFileEquals() throws IOException {
        Path path = Paths.get(USER_DIR_LOCATION);
        Path path2 = Paths.get(USER_DIR_LOCATION);
        System.out.println(Files.isSameFile(path, path2));
    }

    private static void displayFileAccessibility() {
        Path path = Paths.get(USER_DIR_LOCATION);
        System.out.printf("${user.dir} : %s , readable = %s , writable = %s , executable : %s \n",
                path,
                Files.isReadable(path),
                Files.isWritable(path),
                Files.isExecutable(path)
        );
    }

    private static void displayFileExists() {
        Path path = Paths.get(USER_DIR_LOCATION);
        System.out.printf("${user.dir}: %s exists  = %s\n", path, Files.exists(path));
    }
}
