# [Spring Cloud 消息总线](https://segmentfault.com/l/1500000011386685)





## 回顾 Spring  事件/监听



```java
package com.segumentfault.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * Spring 事件 Demo
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public class SpringEventDemo {

    public static void main(String[] args) {
        // 创建 Annotation 驱动的 Spring 应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册 EventConfiguration 到 Spring 应用上下文
        context.register(EventConfiguration.class);
        // 启动 Spring 应用上下文
        context.refresh();
        // AnnotationConfigApplicationContext 也是 ApplicationEventPublisher
        ApplicationEventPublisher publisher = context;
        // 发布一个 MyApplicationEvent
        publisher.publishEvent(new MyApplicationEvent("Hello,World"));
    }

    private static class MyApplicationEvent extends ApplicationEvent {

        public MyApplicationEvent(String message) {
            super(message);
        }
    }

    @Configuration
    public static class EventConfiguration {

        /**
         * 监听 {@link MyApplicationEvent}
         *
         * @param event {@link MyApplicationEvent}
         */
        @EventListener
        public void onEvent(MyApplicationEvent event) {
            System.out.println("监听事件 : " + event);
        }

    }

}
```





## Spring Cloud Bus





### 改造 `user-service-client` : 使用 AMQP 整合 Spring Cloud Bus



#### 增加 Maven 依赖



```xml
        <!-- 整合 Spring Cloud Bus : AMQP -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
```



##### 启动依赖服务



`user-service-client` 依赖：

* Eureka Server (1000)
* Config Server (7070)
* Rabbit MQ (5672)



#### 事件传播



如何定位 Application Context ID？

1. 失效管理端安全 - `management.security.enabled = false`

2. 通过访问 `http://localhost:8080/beans` 确认当前 Application Context ID

   ```json
   {
       "context": "user-service-client:8080",
       "parent": "user-service-client",
       "beans": []
   }
   ```

   Application Context ID : `user-service-client:8080`



##### 单点传播



POST http://localhost:8080/bus/refresh?destination=user-service-client:8080



执行 `curl`：

```
curl -X POST http://localhost:8080/bus/refresh?destination=user-service-client:8080
```



日志输出：

```
INFO 28041 --- [nio-8080-exec-3] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request. Keys refreshed []
```



##### 集群传播



POST http://localhost:8080/bus/refresh?destination=user-service-client:**



执行 `curl`：

```
curl -X POST http://localhost:8080/bus/refresh?destination=user-service-client:**
```



日志输出：

```
INFO 28041 --- [nio-8080-exec-5] o.s.cloud.bus.event.RefreshListener      : Received remote refresh request. Keys refreshed []
```



通过日志可知事件监听器均为：`org.springframework.cloud.bus.event.RefreshListener`:

```java
public class RefreshListener
		implements ApplicationListener<RefreshRemoteApplicationEvent> {

	private static Log log = LogFactory.getLog(RefreshListener.class);

	private ContextRefresher contextRefresher;

	public RefreshListener(ContextRefresher contextRefresher) {
		this.contextRefresher = contextRefresher;
	}

	@Override
	public void onApplicationEvent(RefreshRemoteApplicationEvent event) {
		Set<String> keys = contextRefresher.refresh();
		log.info("Received remote refresh request. Keys refreshed " + keys);
	}
}
```



`RefreshListener` 监听事件 `RefreshRemoteApplicationEvent`



##### 自定义 `RefreshRemoteApplicationEvent` 监听器



```java
@Configuration
public class BusConfiguration {

    @EventListener
    public void onRefreshRemoteApplicationEvent(RefreshRemoteApplicationEvent event) {

        System.out.printf(" Source : %s , originService : %s , destinationService : %s \n",
                event.getSource(),
                event.getOriginService(),
                event.getDestinationService());

    }
}
```



#### 事件跟踪



默认事件跟踪功能是失效，需要通过配置项激活：`spring.cloud.bus.trace.enabled=true`

端点：/trace 



##### 事件跟踪详情



```json
  {
    "timestamp": 1513517631139,
    "info": {
      "signal": "spring.cloud.bus.sent",
      "type": "RefreshRemoteApplicationEvent",
      "id": "938c1305-02b8-4697-9ac4-5996908eb58d",
      "origin": "user-service-client:8080",
      "destination": "user-service-client:**"
    }
  },
  {
    "timestamp": 1513517631138,
    "info": {
      "signal": "spring.cloud.bus.ack",
      "event": "RefreshRemoteApplicationEvent",
      "id": "938c1305-02b8-4697-9ac4-5996908eb58d",
      "origin": "user-service-client:8080",
      "destination": "user-service-client:**"
    }
  }
```



##### 内部事件类型



* `RefreshRemoteApplicationEvent`
* `EnvironmentChangeRemoteApplicationEvent`
* `AckRemoteApplicationEvent`  : `ack 激活`





##### 自定义 `EnvironmentChangeRemoteApplicationEvent` 监听



```java
    @EventListener
    public void onEnvironmentChangeRemoteApplicationEvent(EnvironmentChangeRemoteApplicationEvent event) {

        System.out.printf("EnvironmentChangeRemoteApplicationEvent - " +
                        " Source : %s , originService : %s , destinationService : %s \n",
                event.getSource(),
                event.getOriginService(),
                event.getDestinationService());

    }
```



POST 请求 `/bus/env`:

```
curl -X POST http://localhost:8080/bus/env
```



控制台输出：

```
EnvironmentChangeRemoteApplicationEvent -  Source : org.springframework.cloud.bus.endpoint.EnvironmentBusEndpoint@656c356c , originService : user-service-client:8080 , destinationService : ** 
2017-12-17 21:40:42.440  INFO 33364 --- [nio-8080-exec-3] o.s.c.b.event.EnvironmentChangeListener  : Received remote environment change request. Keys/values to update {}
```



`EnvironmentChangeListener` 是默认的`EnvironmentChangeRemoteApplicationEvent` 监听器实现



`/trace` 的变化:

```json
  {
    "timestamp": 1513518042463,
    "info": {
      "signal": "spring.cloud.bus.sent",
      "type": "EnvironmentChangeRemoteApplicationEvent",
      "id": "1af8f5a0-6d1f-440a-82cd-e09876977d33",
      "origin": "user-service-client:8080",
      "destination": "**:**"
    }
  },
  {
    "timestamp": 1513518042462,
    "info": {
      "signal": "spring.cloud.bus.ack",
      "event": "EnvironmentChangeRemoteApplicationEvent",
      "id": "1af8f5a0-6d1f-440a-82cd-e09876977d33",
      "origin": "user-service-client:8080",
      "destination": "**"
    }
  },
```



##### 举例说明



单机：http://localhost:8080/bus/refresh?destination=user-service-client:8080

集群：http://localhost:8080/bus/refresh?destination=user-service-client:**

本地：http://localhost:8080/refresh



`user-service-client` 应用，它有三台服务实例 `8080`、 `8081`和`8082`，Application Context 分别 ：

* `user-service-client:8080`
* `user-service-client:8081`
* `user-service-client:8082`



广播集群事件：`curl -X POST http://localhost:8080/bus/refresh?destination=user-service-client:**`



http://localhost:8080/trace：

```json
  {
    "timestamp": 1513519229726,
    "info": {
      "signal": "spring.cloud.bus.ack",
      "event": "RefreshRemoteApplicationEvent",
      "id": "dbb44a68-1edb-49fd-ac20-fc19669d7c75",
      "origin": "user-service-client:8081",
      "destination": "user-service-client:**"
    }
  },
  {
    "timestamp": 1513519226752,
    "info": {
      "signal": "spring.cloud.bus.sent",
      "type": "RefreshRemoteApplicationEvent",
      "id": "dbb44a68-1edb-49fd-ac20-fc19669d7c75",
      "origin": "user-service-client:8080",
      "destination": "user-service-client:**"
    }
  },
  {
    "timestamp": 1513519226751,
    "info": {
      "signal": "spring.cloud.bus.ack",
      "event": "RefreshRemoteApplicationEvent",
      "id": "dbb44a68-1edb-49fd-ac20-fc19669d7c75",
      "origin": "user-service-client:8080",
      "destination": "user-service-client:**"
    }
  },
```



http://localhost:8081/trace：

```json
  {
    "timestamp": 1513519229726,
    "info": {
      "signal": "spring.cloud.bus.sent",
      "type": "RefreshRemoteApplicationEvent",
      "id": "dbb44a68-1edb-49fd-ac20-fc19669d7c75",
      "origin": "user-service-client:8080",
      "destination": "user-service-client:**"
    }
  },
  {
    "timestamp": 1513519229724,
    "info": {
      "signal": "spring.cloud.bus.ack",
      "event": "RefreshRemoteApplicationEvent",
      "id": "dbb44a68-1edb-49fd-ac20-fc19669d7c75",
      "origin": "user-service-client:8081",
      "destination": "user-service-client:**"
    }
  },
```



传播单点事件：`curl -X POST http://localhost:8080/bus/refresh?destination=user-service-client:8081`



事件源：`8080`

```json
  {
    "timestamp": 1513519487670,
    "info": {
      "signal": "spring.cloud.bus.ack",
      "event": "RefreshRemoteApplicationEvent",
      "id": "6cbc9711-d1a5-4cce-b65c-098f8a6e41d5",
      "origin": "user-service-client:8081",
      "destination": "user-service-client:8081:**"
    }
  },
  {
    "timestamp": 1513519484687,
    "info": {
      "signal": "spring.cloud.bus.sent",
      "type": "RefreshRemoteApplicationEvent",
      "id": "6cbc9711-d1a5-4cce-b65c-098f8a6e41d5",
      "origin": "user-service-client:8080",
      "destination": "user-service-client:8081:**"
    }
  }
```



监听者：`8081`

```json
  {
    "timestamp": 1513519487665,
    "info": {
      "signal": "spring.cloud.bus.sent",
      "type": "RefreshRemoteApplicationEvent",
      "id": "6cbc9711-d1a5-4cce-b65c-098f8a6e41d5",
      "origin": "user-service-client:8080",
      "destination": "user-service-client:8081:**"
    }
  },
  {
    "timestamp": 1513519487664,
    "info": {
      "signal": "spring.cloud.bus.ack",
      "event": "RefreshRemoteApplicationEvent",
      "id": "6cbc9711-d1a5-4cce-b65c-098f8a6e41d5",
      "origin": "user-service-client:8081",
      "destination": "user-service-client:8081:**"
    }
  }
```





#### 源码分析



##### `BusAutoConfiguration`



###### 监听 Spring Event（本地事件）



```java
	@EventListener(classes = RemoteApplicationEvent.class)
	public void acceptLocal(RemoteApplicationEvent event) {
		if (this.serviceMatcher.isFromSelf(event)
				&& !(event instanceof AckRemoteApplicationEvent)) {
			this.cloudBusOutboundChannel.send(MessageBuilder.withPayload(event).build());
		}
	}
```



由于`@EventListener` 监听 Spring Event，事件`RemoteApplicationEvent` 属于本地事件，因此必然有发布该事件的源头。



###### 监听 Stream 事件（远程事件）

```java
	@StreamListener(SpringCloudBusClient.INPUT)
	public void acceptRemote(RemoteApplicationEvent event) {
		if (event instanceof AckRemoteApplicationEvent) {
			if (this.bus.getTrace().isEnabled() && !this.serviceMatcher.isFromSelf(event)
					&& this.applicationEventPublisher != null) {
				this.applicationEventPublisher.publishEvent(event);
			}
			// If it's an ACK we are finished processing at this point
			return;
		}
		if (this.serviceMatcher.isForSelf(event)
				&& this.applicationEventPublisher != null) {
			if (!this.serviceMatcher.isFromSelf(event)) {
				this.applicationEventPublisher.publishEvent(event);
			}
			if (this.bus.getAck().isEnabled()) {
				AckRemoteApplicationEvent ack = new AckRemoteApplicationEvent(this,
						this.serviceMatcher.getServiceId(),
						this.bus.getAck().getDestinationService(),
						event.getDestinationService(), event.getId(), event.getClass());
				this.cloudBusOutboundChannel
						.send(MessageBuilder.withPayload(ack).build());
				this.applicationEventPublisher.publishEvent(ack);
			}
		}
		if (this.bus.getTrace().isEnabled() && this.applicationEventPublisher != null) {
			// We are set to register sent events so publish it for local consumption,
			// irrespective of the origin
			this.applicationEventPublisher.publishEvent(new SentApplicationEvent(this,
					event.getOriginService(), event.getDestinationService(),
					event.getId(), event.getClass()));
		}
	}
```

`acceptRemote` 监听 Stream 事件，同时发送 Spring Event（本地事件）。



`ServiceMatcher#isForSelf(RemoteApplicationEvent)` 用于匹配 `RemoteApplicationEvent` 是否为当前应用实例而来。

```java
this.serviceMatcher.isForSelf(event)
```



`ServiceMatcher#isFromSelf(RemoteApplicationEvent)` 用于判断当前事件是否为自己发送。

```java
this.serviceMatcher.isFromSelf(event)
```





##### 整体流程



假设 `user-service-client:8080` 执行`/bus/refresh` 端口，发送了一个`RefreshRemoteApplicationEvent`事件:

`curl -X POST http://localhost:8080/bus/refresh?destination=user-service-client:8082`



`user-service-client:8080` : Bus 事件的发布者、监听者

`user-service-client:8081` : Bus 事件监听者

`user-service-client:8082`: Bus 事件监听者



当 Stream Binder 接收到发布者`RefreshRemoteApplicationEvent`事件，广播该事件到所有的监听者：

* `user-service-client:8080`  : 判断事件是自己发送，`SentApplicationEvent`

* `user-service-client:8081`：判断事件不是为自己发送，忽略

* `user-service-client:8082`：判断事件是为自己发送，执行`RefreshRemoteApplicationEvent` 事件监听。如果 `ack` 激活的，`cloudBusOutboundChannel` 会发送`AckRemoteApplicationEvent` 到管道里。可以由：

  ```java
      @StreamListener(SpringCloudBusClient.OUTPUT)
      public void onAckRemoteApplicationEvent(AckRemoteApplicationEvent event) {}
  ```

  ​





#### 自定义实现 `RemoteApplicationEvent`



##### 扩展  `RemoteApplicationEvent` ：`UserRemoteApplicationEvent`

```java
package com.segumentfault.spring.cloud.lesson14.user.service.client.bus;

import com.segumentfault.spring.cloud.lesson14.domain.User;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * 用户{@link RemoteApplicationEvent}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public class UserRemoteApplicationEvent extends RemoteApplicationEvent {

    private UserRemoteApplicationEvent() {
    }

    public UserRemoteApplicationEvent(User user, String originService,
                                      String destinationService) {
        super(user, originService, destinationService);
    }

}

```



##### 添加 `@RemoteApplicationEventScan`

```java
@Configuration
@RemoteApplicationEventScan(basePackageClasses = UserRemoteApplicationEvent.class)
public class BusConfiguration {
}
```



##### 发布 `UserRemoteApplicationEvent`



```java
package com.segumentfault.spring.cloud.lesson14.user.service.client.web.controller;

import com.segumentfault.spring.cloud.lesson14.domain.User;
import com.segumentfault.spring.cloud.lesson14.user.service.client.bus.UserRemoteApplicationEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Bus 事件 Controller
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@RestController
public class BusEventController implements ApplicationContextAware, ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    private ApplicationContext applicationContext;


    @PostMapping("/bus/event/publish/user")
    public boolean publishUserEvent(@RequestBody User user,
                                    @RequestParam(value = "destination", required = false) String destination) {

        String serviceInstanceId = applicationContext.getId();
        UserRemoteApplicationEvent event = new UserRemoteApplicationEvent(user, serviceInstanceId, destination);
        eventPublisher.publishEvent(event);
        return true;
        
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
```





##### 监听 `UserRemoteApplicationEvent`



```java
    @EventListener
    public void onUserRemoteApplicationEvent(UserRemoteApplicationEvent event) {

        System.out.printf("UserRemoteApplicationEvent - " +
                        " Source : %s , originService : %s , destinationService : %s \n",
                event.getSource(),
                event.getOriginService(),
                event.getDestinationService());
    }
```



##### 测试 `UserRemoteApplicationEvent`



Postman  http://localhost:8080/bus/event/publish/user?destination=user-service-client:8081

POST JSON data：

```json
{
	"id":1,
	"name":"小马哥"
}
```



