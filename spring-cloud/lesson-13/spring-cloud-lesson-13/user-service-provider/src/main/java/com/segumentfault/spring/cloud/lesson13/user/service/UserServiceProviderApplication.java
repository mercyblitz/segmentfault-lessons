package com.segumentfault.spring.cloud.lesson13.user.service;

import com.segumentfault.spring.cloud.lesson13.user.stream.UserMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient // 激活服务发现客户端
@EnableBinding(UserMessage.class) // 激活 Stream Binding 到 UserMessage
public class UserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderApplication.class, args);
    }
}
