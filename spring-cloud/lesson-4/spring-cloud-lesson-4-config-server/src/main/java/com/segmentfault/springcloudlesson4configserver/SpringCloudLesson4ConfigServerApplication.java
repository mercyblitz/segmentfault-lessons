package com.segmentfault.springcloudlesson4configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class SpringCloudLesson4ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson4ConfigServerApplication.class, args);
	}
}
