package com.segumentfault.springcloudlesson4eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient
public class SpringCloudLesson4EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson4EurekaClientApplication.class, args);
	}
}
