package com.segementfalut.deep.in.java.annotation.processing;

import com.segementfalut.deep.in.java.compiler.Compiler;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static com.segementfalut.deep.in.java.compiler.CompilerDemo.getClassOutputDirectory;

/**
 * 编译 + RepositoryAnnotationProcessor 处理
 */
@Component
public class RepositoryAnnotationProcessorDemo {

    public static void main(String[] args) throws IOException {
        File sourceDirectory = new File(System.getProperty("user.dir"), "/src/main/java/");
        File targetDirectory = getClassOutputDirectory();
        // 基于 Compiler
        Compiler compiler = new Compiler(sourceDirectory, targetDirectory);
        compiler.setProcessors(new RepositoryAnnotationProcessor());
        compiler.compile(
                "com.segementfalut.deep.in.java.reflection.User",
                "com.segementfalut.deep.in.java.reflection.Repository",
                "com.segementfalut.deep.in.java.reflection.CrudRepository",
                "com.segementfalut.deep.in.java.reflection.UserRepository"
        );
    }
}
