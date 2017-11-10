package com.segumentfault.springcloudlesson4eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudLesson4EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson4EurekaServerApplication.class, args);
	}
}
