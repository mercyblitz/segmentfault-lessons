# Spring Cloud 负载均衡







## Netflix Ribbon



### 引入Maven 依赖

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-ribbon</artifactId>
</dependency>
```



### 激活 Ribbon 客户端

```java
package com.segumentfault.springcloudlesson6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class SpringCloudLesson6Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson6Application.class, args);
	}

	//声明 RestTemplate
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
```



### 配置 Ribbon 客户端

application.properties

```properties
### 配置ribbon 服务地提供方
spring-cloud-service-provider.ribbon.listOfServers = \
  http://${serivce-provider.host}:${serivce-provider.port}
```



### 调整 RestTemplate

```java
//声明 RestTemplate
@LoadBalanced // RestTemplate 的行为变化
@Bean
public RestTemplate restTemplate(){
    return new RestTemplate();
}
```





## Neflix Ribbon 整合 Eureka



### 激活服务发现的客户端

```java
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

```



### 创建并且启动 Eureka Server

以`spring-cloud-lesson6-eureka-server` 为例



#### 激活 Eureka Server

```java
package com.segumentfault.springcloudlesson6eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudLesson6EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson6EurekaServerApplication.class, args);
	}
}
```



#### 配置 Eureka 服务器

```properties
## Eureka Serer
spring.application.name = spring-cloud-eureka-server

## 服务端口
server.port = 10000

## Spring Cloud Eureka 服务器作为注册中心
## 通常情况下，不需要再注册到其他注册中心去
## 同时，它也不需要获取客户端信息
### 取消向注册中心注册
eureka.client.register-with-eureka = false
### 取消向注册中心获取注册信息（服务、实例信息）
eureka.client.fetch-registry = false
## 解决 Peer / 集群 连接问题
eureka.instance.hostname = localhost
eureka.client.serviceUrl.defaultZone = http://${eureka.instance.hostname}:${server.port}/eureka
```



#### 启动 Eureka Server



### 调整 Ribbon 客户端连接 Eureka Server

`applicaiont.properties`

```properties
## 服务提供方
spring.application.name = spring-cloud-ribbon-client

### 服务端口
server.port = 8080

### 管理安全失效
management.security.enabled = false

### 暂时性关闭 Eureka 注册
## 当使用 Eureka 服务发现时，请注释掉一下配置
# eureka.client.enabled = false

## 连接 Eureka Sever
eureka.client.serviceUrl.defaultZone = http://localhost:10000/eureka/

### 服务提供方主机
serivce-provider.host = localhost
### 服务提供方端口
serivce-provider.port = 9090

serivce-provider.name = spring-cloud-service-provider

### 配置ribbon 服务地提供方
## 当使用 Eureka 服务发现时，请注释掉一下配置
# spring-cloud-service-provider.ribbon.listOfServers = \
  http://${serivce-provider.host}:${serivce-provider.port}
```



#### 调整服务提供方并且连接 Eureka Server

```properties
## 服务提供方
spring.application.name = spring-cloud-service-provider

### 服务端口
server.port = 9090

### 管理安全失效
management.security.enabled = false

### 暂时性关闭 Eureka 注册
## 当使用 Eureka 服务发现时，请注释掉一下配置
# eureka.client.enabled = false

## 连接 Eureka Sever
eureka.client.serviceUrl.defaultZone = http://localhost:10000/eureka/
```



再启动两台服务提供方实例

--server.port=9091

--server.port=9092











实际请求客户端

* LoadBalancerClient
  * RibbonLoadBalancerClient

负载均衡上下文

* LoadBalancerContext
  * RibbonLoadBalancerContext

负载均衡器

* ILoadBalancer
  * BaseLoadBalancer
  * DynamicServerListLoadBalancer
  * ZoneAwareLoadBalancer
  * NoOpLoadBalancer

负载均衡规则

核心规则接口

* IRule
  * 随机规则：RandomRule
  * 最可用规则：BestAvailableRule
  * 轮训规则：RoundRobinRule
  * 重试实现：RetryRule
  * 客户端配置：ClientConfigEnabledRoundRobinRule
  * 可用性过滤规则：AvailabilityFilteringRule
  * RT权重规则：WeightedResponseTimeRule
  * 规避区域规则：ZoneAvoidanceRule

PING 策略

核心策略接口

* IPingStrategy

PING 接口

* IPing
  * NoOpPing
  * DummyPing
  * PingConstant
  * PingUrl

Discovery Client 实现

* NIWSDiscoveryPing