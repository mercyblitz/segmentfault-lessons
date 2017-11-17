package com.segumentfault.springcloudlesson5eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
public class SpringCloudLesson5EurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson5EurekaClientApplication.class, args);
	}
}
