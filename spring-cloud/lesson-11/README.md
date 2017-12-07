# [Spring Cloud 服务网关](https://segmentfault.com/l/1500000011386451)





## Spring Cloud Zuul



### 增加依赖

```xml
        <!-- 依赖 Spring Cloud Netflix Zuul -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zuul</artifactId>
        </dependency>
```



### 创建 Zuul 代理应用



```java
package com.segumentfault.spring.cloud.lesson11.zuul.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Zuul 代理引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulProxyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulProxyApplication.class, args);
    }

}
```



### 配置 Zuul 应用



`application.properties`

```properties
## Zuul 代理应用
spring.application.name = zuul-proxy

## 服务端口
server.port = 6060

## 管理安全失效
management.security.enabled = false
```



### 配置 zuul 路由规则

`application.properties`

```properties
## 指定 user-service-provider
zuul.routes.user-service-provider = /user-service/**

## 配置 ribbon
user-service-provider.ribbon.listOfServers = http://localhost:9090/

## http://localhost:8080/user-service/* => http://localhost:9090/*
```





### 配合 HTTP 客户端

> 注意：实际配置 Ribbon 底层 HTTP 调用客户端，并非 zuul 独享此功能



#### 默认客户端：HttpClient



装配类：`HttpClientRibbonConfiguration`



#### 配置客户端：OkHttpClient



装配类：`OkHttpRibbonConfiguration`

激活配置：`ribbon.okhttp.enabled=true`



## Spring Cloud 整合

### 服务端口信息

> 端口信息
>
> ​	zuul-proxy : 6060
>
> ​	config-server : 7070
>
> ​        user-service-client: 8080
>
> ​        user-service-provider : 9090
>
> ​        eureka-server : 10000



### 服务依赖关系



* eureka-server

  * user-service-provider (1)

  * config-server (2)

    * user-service-client
      * zuul-proxy

    ​

### config-server 配置 zuul-proxy 信息



`configs/zuul-config.properties`

```properties
## Zuul Proxy 配置内容

## 指定 user-service-provider
zuul.routes.user-service-provider = /user-service/**
## 指定 user-service-client
zuul.routes.user-service-client = /user-client/**
```



### zuul-proxy 作为配置客户端



#### 增加 config client 依赖

```xml
        <!-- 依赖 Spring Cloud Config Client -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
```



#### 配置 config client 信息



`bootstrap.properties`

```properties
## Zuul 代理应用
spring.application.name = zuul-proxy

## 配置客户端应用关联的应用
## spring.cloud.config.name 是可选的
## 如果没有配置，采用 ${spring.application.name}
spring.cloud.config.name = zuul-config
## 关联 profile
spring.cloud.config.profile = default
## 关联 label
spring.cloud.config.label = master
## 激活 Config Server 服务发现
spring.cloud.config.discovery.enabled = true
## Config Server 服务器应用名称
spring.cloud.config.discovery.serviceId = config-server
## Spring Cloud Eureka 客户端 注册到 Eureka 服务器
eureka.client.serviceUrl.defaultZone = http://localhost:10000/eureka
```



### zuul-proxy 激活服务发现



#### 增加 Eureka Client 依赖

```xml
        <!-- 依赖 Spring Cloud Netflix Eureka -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
        </dependency>
```





ZuulFilter 调用链



`ZuulFilter#run()` <- `ZuulFilter#runFilter()` <- `FilterProcessor#runFilters`



`FilterProcessor#preRoute()`

`FilterProcessor#route()`

`FilterProcessor#postRoute()`

`ZuulServletFilter` `ZuulServlet`

```java
            RequestContext context = RequestContext.getCurrentContext();
            context.setZuulEngineRan();

            try {
                preRoute();
            } catch (ZuulException e) {
                error(e);
                postRoute();
                return;
            }
            try {
                route();
            } catch (ZuulException e) {
                error(e);
                postRoute();
                return;
            }
            try {
                postRoute();
            } catch (ZuulException e) {
                error(e);
                return;
            }
```



## Zuul 自动装配



`ZuulServletFilter` 适用范围更大，可以拦截所有的`Servlet`，包括 `ZuulServlet`



`ZuulServlet` 会有URL 匹配的模式，url-pattern



Zuul 有两种的激活模式：

* `@EnableZuulProxy`

  导入`ZuulProxyMarkerConfiguration`，随后生成一个`ZuulProxyMarkerConfiguration.Marker()` Bean，这个Bean 作为`ZuulProxyAutoConfiguration` 的装配前置条件。

  请注意：`ZuulProxyMarkerConfiguration` 扩展了 `ZuulServerAutoConfiguration`，所以 `ZuulServlet` 和`ZuulController`会被自动装配

  `ZuulController` 有 `DispatcherServlet` 来在控制,它的映射地址是："/*"，

  `DispatcherServlet` 中注册了一个`ZuulHandlerMapping` ，它控制映射到`ZuulController`，可以参考`ZuulServerAutoConfiguration`:

  ```java
  	@Bean
  	public ZuulController zuulController() {
  		return new ZuulController();
  	}

  	@Bean
  	public ZuulHandlerMapping zuulHandlerMapping(RouteLocator routes) {
  		ZuulHandlerMapping mapping = new ZuulHandlerMapping(routes, zuulController());
  		mapping.setErrorController(this.errorController);
  		return mapping;
  	}
  ```

  通过源码分析，`ZuulController`  将请求委派给`ZuulServlet`，所以所有的`ZuulFilter` 实例都会被执行。

  > 因此，访问 http://localhost:6060/user-service-client/user/find/all ，实际将请求递交给 DispatcherServlet
  >
  > 发送请求"/user-service-client/user/find/all" 

  * `DispatcherServlet`
    * `ZuulHandlerMapping`
      * `ZuulController`
        * `ZuulServlet`
          * `RibbonRoutingFilter`	


* `@EnableZuulServer`

  导入`ZuulServerMarkerConfiguration` ，随后生成一个 `ZuulServerMarkerConfiguration.Marker()` Bean ，主要用作引导装配`ZuulServerAutoConfiguration`



`ZuulServerAutoConfiguration`与 父类 `ZuulProxyAutoConfiguration` 区别：

父类`ZuulProxyAutoConfiguration` 提供了`RibbonRoutingFilter`



调用层次：

* `DispatcherServlet`
  * `ZuulHandlerMapping`
    * `ZuulController`
      * `ZuulServlet`
        * `ZuulFilter`





