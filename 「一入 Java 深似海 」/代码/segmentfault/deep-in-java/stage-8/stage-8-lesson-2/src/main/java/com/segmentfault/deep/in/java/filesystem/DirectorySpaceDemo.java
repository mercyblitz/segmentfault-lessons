package com.segmentfault.deep.in.java.filesystem;

import java.io.File;
import java.io.FileFilter;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.sun.tools.doclint.Entity.sum;

public class DirectorySpaceDemo {

    // 文件 -> File.length()
    // 目录 -> listFiles

    private final File rootDirectory;

    private Predicate<File> filter;

    public DirectorySpaceDemo(File rootDirectory, FileFilter... filters) {
        this.rootDirectory = rootDirectory;
        this.filter = new FilePredicate(filters);
    }

    private interface FilePredicateAdapter extends Predicate<File>, FileFilter {

        @Override
        default boolean accept(File pathname) {
            return test(pathname);
        }

    }

    private class FilePredicate implements Predicate<File> {

        private final FileFilter[] filters;

        public FilePredicate(FileFilter[] filters) {
            this.filters = filters;
        }

        @Override
        public boolean test(File file) {
            for (FileFilter filter : filters) {
                if (!filter.accept(file)) {
                    return false;
                }
            }
            return true;
        }
    }

    public long getSpace() {
        FileFilter fileFilter = null;
        if (rootDirectory.isFile()) {
            return rootDirectory.length();
        } else if (rootDirectory.isDirectory()) {
            File[] subFiles = rootDirectory.listFiles();
            long space = 0L;
            // 累加当前目录的文件
            space += Stream.of(subFiles)
                    .filter(File::isFile)
                    .filter(filter)
                    .map(File::length)
                    .reduce(Long::sum)
                    .orElse(0L);

            // 递归当前子目录
            space += Stream.of(subFiles)
                    .filter(File::isDirectory)
                    .filter(filter)
                    .map(DirectorySpaceDemo::new)
                    .map(DirectorySpaceDemo::getSpace)
                    .reduce(Long::sum)
                    .orElse(0L);

            return space;
        }
        return -1L;
    }

    public static void main(String[] args) {
        System.out.println(new DirectorySpaceDemo(new File(System.getProperty("user.home")), file -> file.getName().endsWith(".log")).getSpace() / 1024);
    }
}
