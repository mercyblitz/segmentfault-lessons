package com.segumentfault.springcloudlesson6serviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudLesson6ServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson6ServiceProviderApplication.class, args);
	}
}
