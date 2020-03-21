package com.segementfalut.deep.in.java.compiler;

import javax.annotation.processing.Processor;
import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class Compiler {

    // 指定 Java Source 文件的根路径 -sourcepath
    private final File sourceDirectory;

    private final File targetDirectory;

    private final JavaCompiler javaCompiler;

    private final StandardJavaFileManager fileManager;

    private Iterable<? extends Processor> processors;

    public Compiler(File sourceDirectory, File targetDirectory) {
        this.sourceDirectory = sourceDirectory;
        this.targetDirectory = targetDirectory;
        this.javaCompiler = ToolProvider.getSystemJavaCompiler();
        this.fileManager = javaCompiler.getStandardFileManager(null, null, null);
    }

    public void setProcessors(Processor... processors) {
        this.processors = asList(processors);
    }

    /**
     * @param classNames 类所在的目录相对于 {@link #sourceDirectory}
     * @throws IOException
     */
    public void compile(String... classNames) throws IOException {
        // 指定 Java 新生成的 Class 输出目录（并非编译时依赖的 ClassPath） javac -d
        this.fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Collections.singleton(targetDirectory));

        List<File> sourceFiles = Stream.of(classNames)
                .map(name -> name.replace('.', File.separatorChar).concat(".java")) // 目标类的源文件（相对路径）
                .map(name -> sourceDirectory.getAbsolutePath() + File.separatorChar + name) // 目标类的源文件（Maven 路径）
                .map(File::new)                                                             // 将路径变为文件
                .collect(toList());

        Iterable<? extends JavaFileObject> compilationUnits =
                fileManager.getJavaFileObjectsFromFiles(sourceFiles);

        JavaCompiler.CompilationTask compilationTask = javaCompiler.getTask(new OutputStreamWriter(System.out), fileManager, null, null, null, compilationUnits);
        // 设置 Processor
        compilationTask.setProcessors(processors);
        // 执行编译
        compilationTask.call();
    }

    public File getSourceDirectory() {
        return sourceDirectory;
    }

    public File getTargetDirectory() {
        return targetDirectory;
    }
}
