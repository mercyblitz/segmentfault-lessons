# [Spring Cloud 分布式应用跟踪](https://segmentfault.com/l/1500000011386721)





SpanID : 阶段性 ID，比如一次 RPC 有可能多 Span



TraceID：一次 RPC 只有一个 TraceID





## 整合 Spring Cloud Sleuth



### 增加依赖

```xml
        <!-- 整合 Spring Cloud Sleuth -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth</artifactId>
        </dependency>
```





## Zipkin 整合



### 新增 Zipkin 服务器



#### 增加 Maven 依赖



```xml
        <!-- Zipkin Server 整合 -->
        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-server</artifactId>
        </dependency>

        <!-- Zipkin Server UI 整合 -->
        <dependency>
            <groupId>io.zipkin.java</groupId>
            <artifactId>zipkin-autoconfigure-ui</artifactId>
        </dependency>
```



#### 激活 Zipkin 服务器



HTTP 方式采集

```java
package com.segumentfault.spring.cloud.lesson15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

/**
 * Zipkin 服务器应用
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
```



#### 配置 Zipkin 服务器

```properties
## 应用元信息
## Zipkin 服务器应用名称
spring.application.name = zipkin-server

## Zipkin 服务器服务端口
server.port = 20000

## 管理端口安全失效
management.security.enabled = false
```



### 整合 ZipKin 客户端



#### 改造 `user-service-client`



HTTP 方式上报



##### 增加 Maven 依赖

```xml
        <!-- 整合 Spring Cloud Zipkin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```





##### 配置：连接 zipkin 服务器

```properties
## Zipkin 配置
### 配置 Zipkin 服务器
zipkin.server.host = localhost
zipkin.server.port = 20000
spring.zipkin.base-url = http://${zipkin.server.host}:${zipkin.server.port}
```



#### 改造 `user-service-provider`



##### 增加 Maven 依赖

```xml
        <!-- 整合 Spring Cloud Zipkin -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```





##### 配置：连接 zipkin 服务器

```properties
## Zipkin 配置
### 配置 Zipkin 服务器
zipkin.server.host = localhost
zipkin.server.port = 20000
spring.zipkin.base-url = http://${zipkin.server.host}:${zipkin.server.port}
```



### 改造Zipkin 服务器：使用 Stream 方式订阅



#### 增加 Maven 依赖

```xml
        <!-- zipkin 整合 Spring Cloud Sleuth Stream  -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-sleuth-zipkin-stream</artifactId>
        </dependency>

        <!-- 整合 Spring Cloud Stream Binder Rabbit MQ -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
        </dependency>
```



#### 替换激活注解：`@EnableZipkinStreamServer`



Stream 方式采集

```java
package com.segumentfault.spring.cloud.lesson15.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * Zipkin 服务器应用
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@SpringBootApplication
//@EnableZipkinServer
@EnableZipkinStreamServer
public class ZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
```





### 改造 `user-service-client`



#### 增加 Maven 依赖

Stream 方式上报

```xml
	    <!-- 整合 Spring Cloud Sleuth -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth</artifactId>
        </dependency> 
	    <!-- 整合 Spring Cloud Sleuth Stream -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-sleuth-stream</artifactId>
        </dependency>
	    <!-- 整合 Spring Cloud Stream Binder Rabbit MQ -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
        </dependency>
```



> 注意应移除 `spring-cloud-starter-zipkin` 依赖



### 改造 `user-service-provider`



#### 增加 Maven 依赖

Stream 方式上报

```xml
	    <!-- 整合 Spring Cloud Sleuth -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-sleuth</artifactId>
        </dependency> 
	    <!-- 整合 Spring Cloud Sleuth Stream -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-sleuth-stream</artifactId>
        </dependency>
	    <!-- 整合 Spring Cloud Stream Binder Rabbit MQ -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-stream-binder-rabbit</artifactId>
        </dependency>
```



> 注意应移除 `spring-cloud-starter-zipkin` 依赖