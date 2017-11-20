package com.segumentfault.springcloudlesson6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// 多个 Ribbon 定义
@RibbonClients({
        @RibbonClient(name = "spring-cloud-service-provider")
})
@EnableDiscoveryClient // 激活服务发现客户端
public class SpringCloudLesson6Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson6Application.class, args);
	}

    //声明 RestTemplate
    @LoadBalanced // RestTemplate 的行为变化
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
