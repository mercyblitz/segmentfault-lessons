package com.segmentfault.springbootlesson9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class SpringBootLesson9Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLesson9Application.class, args);
	}
}
