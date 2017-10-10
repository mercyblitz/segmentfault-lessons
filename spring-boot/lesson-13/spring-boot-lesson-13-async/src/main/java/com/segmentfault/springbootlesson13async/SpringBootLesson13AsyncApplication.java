package com.segmentfault.springbootlesson13async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.segmentfault.springbootlesson13async.servlet")
public class SpringBootLesson13AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLesson13AsyncApplication.class, args);
    }
}
