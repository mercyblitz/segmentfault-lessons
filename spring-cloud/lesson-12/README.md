# [Spring Cloud 消息驱动整合](https://segmentfault.com/l/1500000011386642)





## 整合 Kafka



### 改造 user-service-client 消息发送源（Kafka 原生 API）



#### User 模型实现序列化接口



```java
package com.segumentfault.spring.cloud.lesson12.domain;

import java.io.Serializable;

/**
 * 用户模型
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
public class User implements Serializable {

    private static final long serialVersionUID = -5688097732613347904L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名称
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```



#### 增加 kafka 依赖

```xml
        <!-- 整合 Kafka -->
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
        </dependency>
```



#### 利用 KafkaTemplate 实现消息发送



```java
package com.segumentfault.spring.cloud.lesson12.user.service.client.web.controller;

import com.segumentfault.spring.cloud.lesson12.api.UserService;
import com.segumentfault.spring.cloud.lesson12.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * {@link UserService} 客户端 {@link RestController}
 * <p>
 * 注意：官方建议 客户端和服务端不要同时实现 Feign 接口
 * 这里的代码只是一个说明，实际情况最好使用组合的方式，而不是继承
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RestController
public class UserServiceClientController implements UserService {

    @Autowired
    private UserService userService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public UserServiceClientController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/user/save/message")
    public boolean saveUserByMessage(@RequestBody User user) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("sf-users", 0, user);
        return future.isDone();
    }

    // 通过方法继承，URL 映射 ："/user/save"
    @Override
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // 通过方法继承，URL 映射 ："/user/find/all"
    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

}

```



#### 实现 Kafka 序列化器：Java 序列化协议



```java
package com.segumentfault.spring.cloud.lesson12.user.service.client.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * Java 序列化协议
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public class ObjectSerializer implements Serializer<Object> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Object object) {

        System.out.println("topic : " + topic + " , object : " + object);

        byte[] dataArray = null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(object);

            dataArray = outputStream.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return dataArray;
    }

    @Override
    public void close() {

    }
}
```





## Spring Cloud Stream 整合



### 改造 user-service-provider 消息接收器（Sink）



#### 引入 spring-cloud-stream-binder-kafka

```xml
        <!-- 依赖 Spring Cloud Stream Binder Kafka -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-stream-binder-kafka</artifactId>
        </dependency>
```



#### 用户消息 Stream 接口定义



```java
package com.segumentfault.spring.cloud.lesson12.user.stream;

import com.segumentfault.spring.cloud.lesson12.domain.User;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * {@link User 用户} 消息 Stream 接口定义
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public interface UserMessage {

    String INPUT = "user-message";

    @Input(INPUT)
        // 管道名称
    SubscribableChannel input();

}
```



#### 激活用户消息 Stream 接口



```java
package com.segumentfault.spring.cloud.lesson12.user.service;

import com.segumentfault.spring.cloud.lesson12.user.stream.UserMessage;
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
```



#### 配置 Kafka 以及 Stream Destination



```properties
## Spring Cloud Stream Binding 配置
### destination 指定 Kafka Topic
### userMessage 为输入管道名称
spring.cloud.stream.bindings.user-message.destination = sf-users

## Kafka 生产者配置

spring.kafka.BOOTSTRAP-SERVERS=localhost:9092
spring.kafka.consumer.group-id=sf-group
spring.kafka.consumer.clientId=user-service-provider
```



#### 添加 User 消息监听器



##### SubscribableChannel 实现

```java
package com.segumentfault.spring.cloud.lesson12.user.service.provider.service;

import com.segumentfault.spring.cloud.lesson12.api.UserService;
import com.segumentfault.spring.cloud.lesson12.domain.User;
import com.segumentfault.spring.cloud.lesson12.user.stream.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * 用户 消息服务
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@Service
public class UserMessageService {

    @Autowired
    private UserMessage userMessage;

    @Autowired
    @Qualifier("inMemoryUserService")
    private UserService userService;

    private void saveUser(byte[] data) {
        // message body 是字节流 byte[]
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            User user = (User) objectInputStream.readObject(); // 反序列化成 User 对象
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
  
     @PostConstruct
    public void init() {

        SubscribableChannel subscribableChannel = userMessage.input();
        subscribableChannel.subscribe(message -> {
            System.out.println("Subscribe by SubscribableChannel");
            // message body 是字节流 byte[]
            byte[] body = (byte[]) message.getPayload();
            saveUser(body);

        });
    }

}
```



##### @ServiceActivator 实现

```java
    @ServiceActivator(inputChannel = INPUT)
    public void listen(byte[] data) {
        System.out.println("Subscribe by @ServiceActivator");
        saveUser(data);
    }
```



##### @StreamListener 实现



```java
    @StreamListener(INPUT)
    public void onMessage(byte[] data) {
        System.out.println("Subscribe by @StreamListener");
        saveUser(data);
    }
```



### 改造 user-service-client 消息发送源（ Stream Binder : Rabbit MQ）



#### 增加 spring-cloud-stream-binder-rabbitmq 依赖

```xml
        <!-- 整合 Spring Cloud Stream Binder Rabbit MQ -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
        </dependency>
```



#### 配置发送源管道





#### 添加用户消息接口



```java
package com.segumentfault.spring.cloud.lesson12.user.service.client.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 用户消息(输出)
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public interface UserMessage {

    @Output("user-message-out")
    MessageChannel output();

}
```



#### 激活用户消息接口



```java
package com.segumentfault.spring.cloud.lesson12.user.service.client;

import com.netflix.loadbalancer.IRule;
import com.segumentfault.spring.cloud.lesson12.api.UserService;
import com.segumentfault.spring.cloud.lesson12.user.service.client.rule.MyRule;
import com.segumentfault.spring.cloud.lesson12.user.service.client.stream.UserMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@SpringBootApplication
@RibbonClient("user-service-provider") // 指定目标应用名称
@EnableCircuitBreaker // 使用服务短路
@EnableFeignClients(clients = UserService.class) // 申明 UserService 接口作为 Feign Client 调用
@EnableDiscoveryClient // 激活服务发现客户端
@EnableBinding(UserMessage.class)
public class UserServiceClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceClientApplication.class, args);
    }

    /**
     * 将 {@link MyRule} 暴露成 {@link Bean}
     *
     * @return {@link MyRule}
     */
    @Bean
    public IRule myRule() {
        return new MyRule();
    }

    /**
     * 申明 具有负载均衡能力 {@link RestTemplate}
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
```



#### 实现消息发送到 RabbitMQ

```java
    @PostMapping("/user/save/message/rabbit")
    public boolean saveUserByRabbitMessage(@RequestBody User user) throws JsonProcessingException {
        MessageChannel messageChannel = userMessage.output();
        // User 序列化成 JSON
        String payload = objectMapper.writeValueAsString(user);
        GenericMessage<String> message = new GenericMessage<String>(payload);
        // 发送消息
        return messageChannel.send(message);
    }
```



启动 Rabbit MQ



### 改造 user-service-provider 消息接收器（ Stream Binder : Rabbit MQ）



#### 替换依赖

```xml
        <!--&lt;!&ndash; 依赖 Spring Cloud Stream Binder Kafka &ndash;&gt;-->
        <!--<dependency>-->
            <!--<groupId>org.springframework.cloud</groupId>-->
            <!--<artifactId>spring-cloud-stream-binder-kafka</artifactId>-->
        <!--</dependency>-->

        <!-- 整合 Spring Cloud Stream Binder Rabbit MQ -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
        </dependency>
```

