package com.segmentfault.deep.in.java.newfilesystem;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.segmentfault.deep.in.java.newfilesystem.PathDemo.USER_DIR_LOCATION;

public class FindCommandDemo {

    public static void main(String[] args) throws IOException {
        String pattern = "F[a-zA-Z]*.java";
        FindVisitor findVisitor = new FindVisitor(pattern);
        Files.walkFileTree(Paths.get(USER_DIR_LOCATION), findVisitor);
        System.out.printf("Found count : %s\n", findVisitor.getFoundCount());
    }

}
