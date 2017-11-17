package com.segumentfault.springcloudlesson5eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudLesson5EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson5EurekaServerApplication.class, args);
	}
}
