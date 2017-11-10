# Spring Cloud 服务发现/注册



## Spring Cloud  Netflix Eureka



### Eureka 服务器



#### 引入 Maven 依赖



```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-eureka-server</artifactId>
</dependency>
```



#### 激活 Eureka 服务器



```java
package com.segumentfault.springcloudlesson4eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudLesson4EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudLesson4EurekaServerApplication.class, args);
	}
}
```



#### 调整 Eureka 服务器配置

`application.properties`

```properties
## Spring Cloud Eureka 服务器应用名称
spring.application.name = spring-cloud-eureka-server

## Spring Cloud Eureka 服务器服务端口
server.port = 9090

## 管理端口安全失效
management.security.enabled = false
```



#### 检验 Eureka Server

异常堆栈信息：

```
[nfoReplicator-0] c.n.discovery.InstanceInfoReplicator     : There was a problem with the instance info replicator

com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
```



http://localhost:9090/

运行效果：

># Instances currently registered with Eureka



问题原因：Eureka Server 既是注册服务器，也是客户端，默认情况，也需要配置注册中心地址。

> "description": "Spring Cloud Eureka Discovery Client"



#### 解决问题的方法

```properties
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





### Eureka 客户端



#### 引入 Maven 依赖

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```



#### 激活 Eureka 客户端



```java
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
```



#### 配置 Eureka 客户端

```properties
## Spring Cloud Eureka 客户端应用名称
spring.application.name = spring-cloud-eureka-client

## Spring Cloud Eureka 客户端服务端口
server.port = 8080

## 管理端口安全失效
management.security.enabled = false
```



#### 检验 Eureka 客户端

发现与 Eureka 服务器控制台出现相同异常：

```
[nfoReplicator-0] c.n.discovery.InstanceInfoReplicator     : There was a problem with the instance info replicator

com.netflix.discovery.shared.transport.TransportException: Cannot execute request on any known server
```



#### 需要再次调整 Eureka 客户端配置



```properties
## Spring Cloud Eureka 客户端应用名称
spring.application.name = spring-cloud-eureka-client

## Spring Cloud Eureka 客户端服务端口
server.port = 8080

## 管理端口安全失效
management.security.enabled = false

## Spring Cloud Eureka 客户端 注册到 Eureka 服务器
eureka.client.serviceUrl.defaultZone = http://localhost:9090/eureka
```





### Spring Cloud Config 与 Eureka 整合



#### 调整 `spring-cloud-config-server` 作为 Eureka 客户端



#### 引入 Maven 依赖

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
```



#### 激活 Eureka 客户端

```java
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
```



#### 调整 `spring-cloud-config-server` 配置



```properties
## 配置服务器应用名称
spring.application.name = spring-cloud-config-server

## 配置服务器端口
server.port = 7070

## 关闭管理端actuator 的安全
## /env /health 端口完全开放
management.security.enabled = false

## 配置服务器文件系统git 仓库
## ${user.dir} 减少平台文件系统的不一致
# spring.cloud.config.server.git.uri = ${user.dir}/src/main/resources/configs

## 配置服务器远程 Git 仓库（GitHub）
spring.cloud.config.server.git.uri = https://github.com/mercyblitz/tmp

## 强制拉去 Git 内容
spring.cloud.config.server.git.force-pull = true

## spring-cloud-config-server 注册到 Eureka 服务器
eureka.client.serviceUrl.defaultZone = http://localhost:9090/eureka
```



#### 调整 `spring-cloud-eureka-client` 称为 Config 客户端

##### 引入 `spring-cloud-starter-config` Maven 依赖

```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

##### 创建 `bootstrap.properties`

##### 配置 Config 客户端配置

`bootstrap.properties`：

```properties
## 配置客户端应用关联的应用
## spring.cloud.config.name 是可选的
## 如果没有配置，采用 ${spring.application.name}
spring.cloud.config.name = segmentfault
## 关联 profile
spring.cloud.config.profile = prod
## 关联 label
spring.cloud.config.label = master
## 激活 Config 服务器发现
spring.cloud.config.discovery.enabled = true
## 配置 Config 服务器的应用名称（Service ID）
spring.cloud.config.discovery.serviceId = spring-cloud-config-server
```



##### 检验效果

启动发现，spring-cloud-config-server 服务无法找到，原因如下：

```
注意：当前应用需要提前获取应用信息，那么将 Eureka 的配置信息提前至 bootstrap.properties 文件
原因：bootstrap 上下文是 Spring Boot 上下文的 父 上下文，那么它最先加载，因此需要最优先加载 Eureka 注册信息
```



##### 调整后配置

`bootstrap.properties`：

```properties
## 注意：当前应用需要提前获取应用信息，那么将 Eureka 的配置信息提前至 bootstrap.properties 文件
## 原因：bootstrap 上下文是 Spring Boot 上下文的 父 上下文，那么它最先加载，因此需要最优先加载 Eureka 注册信息
## Spring Cloud Eureka 客户端 注册到 Eureka 服务器
eureka.client.serviceUrl.defaultZone = http://localhost:9090/eureka

## 配置客户端应用关联的应用
## spring.cloud.config.name 是可选的
## 如果没有配置，采用 ${spring.application.name}
spring.cloud.config.name = segmentfault
## 关联 profile
spring.cloud.config.profile = prod
## 关联 label
spring.cloud.config.label = master
## 激活 Config 服务器发现
spring.cloud.config.discovery.enabled = true
## 配置 Config 服务器的应用名称（Service ID）
spring.cloud.config.discovery.serviceId = spring-cloud-config-server
```



##### 再次检验效果

访问 http://localhost:8080/env：

```json
"configService:https://github.com/mercyblitz/tmp/segmentfault-prod.properties": {
    "sf.user.id": "001",
    "sf.user.name": "xiaomage",
    "name": "segumentfault.com"
  },
  "configService:https://github.com/mercyblitz/tmp/segmentfault.properties": {
    "name": "segumentfault.com"
  },
```

以上内容来自于`spring-cloud-config-server`:

http://localhost:7070/segmentfault-prod.properties

```properties
name: segumentfault.com
sf.user.id: 001
sf.user.name: xiaomage
```



