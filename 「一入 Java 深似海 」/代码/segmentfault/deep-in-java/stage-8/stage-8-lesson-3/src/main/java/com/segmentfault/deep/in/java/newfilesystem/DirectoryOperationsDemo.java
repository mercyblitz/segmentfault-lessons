package com.segmentfault.deep.in.java.newfilesystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DirectoryOperationsDemo {

    public static void main(String[] args) {
        String classPath = System.getProperty("java.class.path");
        Stream.of(classPath.split(File.pathSeparator))
                .map(Paths::get) // String -> Path
                .filter(Files::isDirectory) // 过滤目录
                .filter(Files::isReadable)
                .filter(Files::isWritable)
                .map(Path::toString)        // Path -> String
                .map(dirPath -> Paths.get(dirPath, "parent-dir", "sub-dir"))  // Path -> new Path
                .forEach(newDir -> {
                    try {
                        Path newDirectory = Files.createDirectories(newDir);
                        System.out.printf("新的目录[%s] 已被创建!\n", newDirectory);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

    }
}
