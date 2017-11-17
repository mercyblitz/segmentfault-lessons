package com.segumentfault.springcloudlesson5consulclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudLesson5ConsulClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson5ConsulClientApplication.class, args);
	}
}
