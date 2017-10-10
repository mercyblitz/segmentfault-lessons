package com.segmentfault.springbootlesson12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.segmentfault")
public class SpringBootLesson12Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLesson12Application.class, args);
    }
}
