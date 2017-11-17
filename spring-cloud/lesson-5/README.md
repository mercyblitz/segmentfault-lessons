# Spring Cloud 高可用服务治理



## Spring Cloud Eureka



### Eureka 客户端



#### 配置多Eureka 注册中心



```properties
## 应用名称
spring.application.name = spring-cloud-eureka-client

## 客户端 端口随即可用
server.port = 0

## 配置连接 Eureka 服务器
## 配置多个 Eureka 注册中心，以"," 分割
eureka.client.serviceUrl.defaultZone = \
  http://localhost:9090/eureka,\
  http://localhost:9091/eureka
```



#### 激活 ：`@EnableEurekaClient` 或者 `@EnableDiscoveryClient`

```java
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
```





### Eureka 服务器



#### 配置高可用 Eureka 服务器



##### 设置公用 Eureka 服务器配置

`application.properties`:

```properties
## 定义 应用名称
spring.applicaiton.name = spring-cloud-eureka-server

## 管理端安全失效
management.security.enabled = false

## 公用 Eureka 配置
### 向注册中心注册
eureka.client.register-with-eureka = true
### 向获取注册信息（服务、实例信息）
eureka.client.fetch-registry = true
```



##### 配置 Peer 1 Eureka 服务器

`application-peer1.properties`:(单机情况相当于 profile = "peer1")

```properties

# peer 1 完整配置

## 配置 服务器端口
## peer 1 端口 9090
server.port = 9090

## peer 2 主机：localhost , 端口 9091
peer2.server.host = localhost
peer2.server.port = 9091

# Eureka 注册信息
eureka.client.serviceUrl.defaultZone = http://${peer2.server.host}:${peer2.server.port}/eureka
```



##### 启动 Peer 1 Eureka 服务器

通过启动参数 `—spring.profiles.active=peer1` ,相当于读取了 `application-peer1.properties `和 `application.properties`



##### 配置 Peer 2 Eureka 服务器

`application-peer2.properties`:(单机情况相当于 profile = "peer2")

```properties
# peer 2 完整配置

## 配置 服务器端口
## peer 2 端口 9091
server.port = 9091

## peer 1 主机：localhost , 端口 9090
peer1.server.host = localhost
peer1.server.port = 9090

# Eureka 注册信息
eureka.client.serviceUrl.defaultZone = http://${peer1.server.host}:${peer1.server.port}/eureka
```



##### 启动 Peer 2 Eureka 服务器

通过启动参数 `—spring.profiles.active=peer2` ,相当于读取了 `application-peer2.properties `和 `application.properties`



### Spring Cloud Consul



#### 引入依赖

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```



#### 激活服务发现客户端

```java
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
```



#### 利用服务发现API 操作

##### 配置应用信息

```properties
## 应用名称
spring.application.name = spring-cloud-consul

## 服务端口
server.port = 8080

## 管理安全失效
management.security.enabled = false

## 连接 Consul 服务器的配置
### Consul 主机地址
spring.cloud.consul.host = localhost
### Consul 服务端口
spring.cloud.consul.port = 8500
```



##### 编写 `DiscoveryClient` Controller

```java
package com.segumentfault.springcloudlesson5consulclient.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * {@link DiscoveryClient} {@link RestController}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RestController
public class DiscoveryClientController {


    private final DiscoveryClient discoveryClient;

    private final String currentApplicationName;

    @Autowired
    public DiscoveryClientController(DiscoveryClient discoveryClient,
                                     @Value("${spring.application.name}") String currentApplicationName) {
        this.discoveryClient = discoveryClient;
        this.currentApplicationName = currentApplicationName;
    }

    /**
     * 获取当前应用信息
     *
     * @return
     */
    @GetMapping("/current/service-instance")
    public ServiceInstance getCurrentServiceInstance() {
//        return discoveryClient.getInstances(currentApplicationName).get(0);
        return discoveryClient.getLocalServiceInstance();

    }


    /**
     * 获取所有的服务名
     *
     * @return
     */
    @GetMapping("/list/services")
    public List<String> listServices() {
        return discoveryClient.getServices();
    }

    /**
     * 获取所有的服务实例信息
     *
     * @return
     */
    @GetMapping("/list/service-instances")
    public List<ServiceInstance> listServiceInstances() {
        List<String> services = listServices();
        List<ServiceInstance> serviceInstances = new LinkedList<>();

        services.forEach(serviceName -> {
            serviceInstances.addAll(discoveryClient.getInstances(serviceName));
        });

        return serviceInstances;
    }
}
```





