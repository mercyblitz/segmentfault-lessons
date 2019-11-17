package com.segmentfault.deep.in.java.newfilesystem;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FindVisitor extends SimpleFileVisitor<Path> {

    private final PathMatcher pathMatcher;

    private int foundCount;

    public FindVisitor(String pattern) {
        this.pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
        matchFile(file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
        matchFile(dir);
        return FileVisitResult.CONTINUE;
    }

    private void matchFile(Path file) {
        Path name = file.getFileName();
        if (name != null && pathMatcher.matches(name)) {
            foundCount++;
            System.out.printf("Found file : %s\n", file);
        }
    }

    public int getFoundCount() {
        return foundCount;
    }
}
