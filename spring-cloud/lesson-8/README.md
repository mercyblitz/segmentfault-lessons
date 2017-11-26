# Spring Cloud 服务短路





## 传统 Spring Web MVC



### 以 web 工程为例

#### 创建  DemoRestController:

```java
package com.segmentfault.spring.cloud.lesson8.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * Demo RestController
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@RestController
public class DemoRestController {

    private final static Random random = new Random();

    /**
     * 当方法执行时间超过 100 ms 时，触发异常
     *
     * @return
     */
    @GetMapping("")
    public String index() throws Exception {

        long executeTime = random.nextInt(200);

        if (executeTime > 100) { // 执行时间超过了 100 ms
            throw new TimeoutException("Execution is timeout!");
        }

        return "Hello,World";
    }

}
```



#### 异常处理

##### 通过`@RestControllerAdvice` 实现

```java
package com.segmentfault.spring.cloud.lesson8.web.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.TimeoutException;

/**
 * {@link DemoRestController} 类似于AOP 拦截
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@RestControllerAdvice(assignableTypes = DemoRestController.class)
public class DemoRestControllerAdvice {

    @ExceptionHandler(TimeoutException.class)
    public Object faultToleranceTimeout(Throwable throwable) {
        return throwable.getMessage();
    }
}
```



## Spring Cloud Netflix Hystrix



### 增加Maven依赖

```xml
	<dependencyManagement>
		<dependencies>

			<!-- Spring Boot 依赖 -->
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-dependencies</artifactId>
				<version>1.5.8.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>

			<!-- Spring Cloud 依赖 -->
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>Dalston.SR4</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>

		</dependencies>
	</dependencyManagement>

    <dependencies>

        <!-- 其他依赖省略 -->

        <!-- 依赖 Spring Cloud Netflix Hystrix -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>

    </dependencies>

```



### 使用`@EnableHystrix` 实现服务提供方短路

修改应用 `user-service-provider` 的引导类：

```java
package com.segumentfault.spring.cloud.lesson8.user.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@SpringBootApplication
@EnableHystrix
public class UserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderApplication.class, args);
    }
}
```



#### 通过`@HystrixCommand`实现

增加 `getUsers()` 方法到 `UserServiceProviderController`：

```java
/**
     * 获取所有用户列表
     *
     * @return
     */
    @HystrixCommand(
            commandProperties = { // Command 配置
                    // 设置操作时间为 100 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            },
            fallbackMethod = "fallbackForGetUsers" // 设置 fallback 方法
    )
    @GetMapping("/user/list")
    public Collection<User> getUsers() throws InterruptedException {

        long executeTime = random.nextInt(200);

        // 通过休眠来模拟执行时间
        System.out.println("Execute Time : " + executeTime + " ms");

        Thread.sleep(executeTime);

        return userService.findAll();
    }
```



为 `getUsers()` 添加 fallback 方法：

```java
    /**
     * {@link #getUsers()} 的 fallback 方法
     *
     * @return 空集合
     */
    public Collection<User> fallbackForGetUsers() {
        return Collections.emptyList();
    }
```



### 使用`@EnableCircuitBreaker` 实现服务调用方短路

调整 `user-ribbon-client` ，为`UserRibbonController` 增加获取用户列表，实际调用`user-service-provider` "/user/list" REST 接口



#### 增加具备负载均衡 `RestTemplate`

在`UserRibbonClientApplication` 增加 `RestTemplate` 申明

```java
    /**
     * 申明 具有负载均衡能力 {@link RestTemplate}
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
```



#### 实现服务调用

```java
    /**
     * 调用 user-service-provider "/user/list" REST 接口，并且直接返回内容
     * 增加 短路功能
     */
    @GetMapping("/user-service-provider/user/list")
    public Collection<User> getUsersList() {
        return restTemplate.getForObject("http://" + providerServiceName + "/user/list", Collection.class);
    }
```



#### 激活 `@EnableCircuitBreaker`

调整 `UserRibbonClientApplication`:

```java
package com.segumentfault.spring.cloud.lesson8.user.ribbon.client;

import com.netflix.loadbalancer.IRule;
import com.segumentfault.spring.cloud.lesson8.user.ribbon.client.rule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
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
public class UserRibbonClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRibbonClientApplication.class, args);
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
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
```



#### 增加编程方式的短路实现

```java
package com.segumentfault.spring.cloud.lesson8.user.ribbon.client.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;

/**
 * User Ribbon Client HystrixCommand
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public class UserRibbonClientHystrixCommand extends HystrixCommand<Collection> {

    private final String providerServiceName;

    private final RestTemplate restTemplate;

    public UserRibbonClientHystrixCommand(String providerServiceName, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey(
                "User-Ribbon-Client"),
                100);
        this.providerServiceName = providerServiceName;
        this.restTemplate = restTemplate;
    }

    /**
     * 主逻辑实现
     *
     * @return
     * @throws Exception
     */
    @Override
    protected Collection run() throws Exception {
        return restTemplate.getForObject("http://" + providerServiceName + "/user/list", Collection.class);
    }

    /**
     * Fallback 实现
     *
     * @return 空集合
     */
    protected Collection getFallback() {
        return Collections.emptyList();
    }

}
```



#### 改造 `UserRibbonController#getUsersList()` 方法

```java
package com.segumentfault.spring.cloud.lesson8.user.ribbon.client.web.controller;

import com.segumentfault.spring.cloud.lesson8.domain.User;
import com.segumentfault.spring.cloud.lesson8.user.ribbon.client.hystrix.UserRibbonClientHystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collection;

/**
 * 用户 Ribbon Controller
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@RestController
public class UserRibbonController {

    /**
     * 负载均衡器客户端
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${provider.service.name}")
    private String providerServiceName;

    @Autowired
    private RestTemplate restTemplate;

    private UserRibbonClientHystrixCommand hystrixCommand;

    /**
     * 调用 user-service-provider "/user/list" REST 接口，并且直接返回内容
     * 增加 短路功能
     */
    @GetMapping("/user-service-provider/user/list")
    public Collection<User> getUsersList() {
        return new UserRibbonClientHystrixCommand(providerServiceName, restTemplate).execute();
    }
}
```



## 为生产为准备



### Netflix Hystrix Dashboard



#### 创建 hystrix-dashboard 工程



#### 增加Maven 依赖

```xml
    <dependencies>

        <!-- 依赖 Hystrix Dashboard -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
        </dependency>

    </dependencies>
```



#### 增加引导类

```java
package com.segumentfault.spring.cloud.lesson8.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Hystrix Dashboard 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
    
}
```



#### 增加 application.properties

```properties
## Hystrix Dashboard 应用
spring.application.name = hystrix-dashboard

## 服务端口
server.port = 10000
```







* * ​