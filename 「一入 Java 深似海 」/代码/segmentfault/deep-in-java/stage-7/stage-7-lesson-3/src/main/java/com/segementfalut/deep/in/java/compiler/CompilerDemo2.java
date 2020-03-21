package com.segementfalut.deep.in.java.compiler;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class CompilerDemo2 {

    public static void main(String[] args) throws IOException {
        File sourceDirectory = new File(System.getProperty("user.dir"), "/src/main/java/");
        File targetDirectory = getClassOutputDirectory();
        // 基于 Compiler
        Compiler compiler = new Compiler(sourceDirectory, targetDirectory);
        compiler.compile(CompilerDemo2.class.getName());
    }

    private static File getClassOutputDirectory() {
        File currentClassPath = new File(findClassPath());
        File targetDirectory = currentClassPath.getParentFile();
        File classOutputDirectory = new File(targetDirectory, "new-classes");
        // 生成类输出目录
        classOutputDirectory.mkdir();
        return classOutputDirectory;
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
