package com.segmentfault.springbootlesson14plus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.segmentfault.servlet")
public class SpringBootLesson14PlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLesson14PlusApplication.class, args);
	}

}
