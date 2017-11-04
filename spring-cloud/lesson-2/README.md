

## Environment



`Environment`: `PropertySources` =  1:1



`PropertySources`: `PropertySource` = 1:N

[0] `PropertySource` （Map）

​	spring.application.name = spring-cloud-config-client

[1] `PropertySource`（Map）

​	spring.application.name = spring-cloud-config-client-demo



### Spring Boot 配置文件



#### application.properties 或 application.xml



加载器：`PropertiesPropertySourceLoader`



#### application.yml 或者 application.yaml

加载器：`YamlPropertySourceLoader`



## Environment 端点



### 请求 URI ：`/env`



数据来源：`EnvironmentEndpoint`

Controller 来源：`EnvironmentMvcEndpoint`





## Bootstrap 配置



参考`BootstrapApplicationListener` 实现

 

> 注：程序启动参数的加载逻辑：
>
> `SpringApplication#configurePropertySources()`



## Bootstrap 配置文件



```java
String configName = environment.resolvePlaceholders("${spring.cloud.bootstrap.name:bootstrap}");
```

当 `spring.cloud.bootstrap.name` 存在时，使用该配置项，否则，使用 "bootstrap" 作为默认



```properties
## application.properties
## 通过调整 spring.cloud.bootstrap.enabled = false，尝试关闭 bootstrap 上下文
## 实际测试结果，没有效果
spring.cloud.bootstrap.enabled = false
```

> 注意：`BootstrapApplicationListener` 加载实际早于 `ConfigFileApplicationListener`
>
> 原因是：
>
>  `ConfigFileApplicationListener` 的 Order =  Ordered.HIGHEST_PRECEDENCE + 10（第十一位）
>
> `BootstrapApplicationListener`的 Order = Ordered.HIGHEST_PRECEDENCE + 5（第六位）



如果需要调整 控制 Bootstrap 上下文行为配置，需要更高优先级，也就是说 Order 需要  < Ordered.HIGHEST_PRECEDENCE + 5 (越小越优先)，比如使用程序启动参数：

```
--spring.cloud.bootstrap.enabld = true
```





### 调整 Bootstrap 配置



#### 调整 Bootstrap 配置文件名称



调整程序启动参数

```
--spring.cloud.bootstrap.name=spring-cloud
```

bootstrap 配置文件名称发生了改变"spring-cloud"，现有三个文件：

* `application.properties`
  * **spring.application.name = spring-cloud-config-client**
* `bootstrap.properties`
  * spring.application.name = spring-cloud-config-client-demo
* `spring-cloud.properties`
  * **spring.application.name = spring-cloud**



运行结果（部分）：

```json
"applicationConfig: [classpath:/application.properties]": {
    "spring.cloud.bootstrap.enabled": "false",
    "endpoints.env.sensitive": "false",
    "spring.application.name": "spring-cloud-config-client"
  },
  ...
  "applicationConfig: [classpath:/spring-cloud.properties]": {
    "spring.application.name": "spring-cloud-config-client"
  }
```



#### 调整 Bootstrap 配置文件路径



保留 **Bootstrap 配置文件名称**  程序启动参数：

```properties
--spring.cloud.bootstrap.name=spring-cloud
```



调整 **Bootstrap 配置文件路径** 程序启动参数：

```properties
--spring.cloud.bootstrap.location=config
```



现有四个文件：

* `application.properties`
  * **spring.application.name = spring-cloud-config-client**
* `bootstrap.properties`
  * spring.application.name = spring-cloud-config-client-demo
* `spring-cloud.properties`
  * **spring.application.name = spring-cloud**
* `config/spring-cloud.properties`
  * **spring.application.name = spring-cloud-2**



实际结果：

```json
  "applicationConfig: [classpath:/application.properties]": {
    "spring.cloud.bootstrap.enabled": "false",
    "endpoints.env.sensitive": "false",
    "spring.application.name": "spring-cloud-config-client"
  },
  ...
  "applicationConfig: [classpath:/config/spring-cloud.properties]": {
    "spring.application.name": "spring-cloud-config-client"
  },
  "applicationConfig: [classpath:/spring-cloud.properties]": {
    "spring.application.name": "spring-cloud-config-client"
  },
```



#### 覆盖远程配置属性



默认情况，Spring Cloud 是允许覆盖的，`spring.cloud.config.allowOverride=true`



通过程序启动参数，调整这个值为"**false**"

```properties
--spring.cloud.config.allowOverride=false
```



启动后，重新Postman 发送 POST 请求，调整`spring.application.name` 值为 "**spring-cloud-new**"

> 注意官方文档的说明：the remote property source has to grant it permission by setting `spring.cloud.config.allowOverride=true` (it doesn’t work to set this locally).



#### 自定义 Bootstrap 配置

1. 创建`META-INF/spring.factories`文件（类似于 Spring Boot 自定义 Starter）

2. 自定义 Bootstrap 配置 Configuration

   ```java
   package com.segmentfault.springcloudlesson2.boostrap;

   import org.springframework.context.ApplicationContextInitializer;
   import org.springframework.context.ConfigurableApplicationContext;
   import org.springframework.context.annotation.Configuration;
   import org.springframework.core.env.ConfigurableEnvironment;
   import org.springframework.core.env.MapPropertySource;
   import org.springframework.core.env.MutablePropertySources;
   import org.springframework.core.env.PropertySource;

   import java.util.HashMap;
   import java.util.Map;

   /**
    * Bootstrap 配置 Bean
    *
    * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
    * @since Configuration
    */
   @Configuration
   public class MyConfiguration implements ApplicationContextInitializer {

       @Override
       public void initialize(ConfigurableApplicationContext applicationContext) {
           
           // 从 ConfigurableApplicationContext 获取 ConfigurableEnvironment 实例
           ConfigurableEnvironment environment = applicationContext.getEnvironment();
           // 获取 PropertySources
           MutablePropertySources propertySources = environment.getPropertySources();
           // 定义一个新的 PropertySource，并且放置在首位
           propertySources.addFirst(createPropertySource());

       }

       private PropertySource createPropertySource() {

           Map<String, Object> source = new HashMap<>();

           source.put("name", "小马哥");

           PropertySource propertySource = new MapPropertySource("my-property-source", source);

           return propertySource;

       }
   }
   ```

   ​

3. 配置`META-INF/spring.factories`文件，关联Key `org.springframework.cloud.bootstrap.BootstrapConfiguration`

   ```properties
   org.springframework.cloud.bootstrap.BootstrapConfiguration= \
   com.segmentfault.springcloudlesson2.boostrap.MyConfiguration
   ```

   ​

   ​

#### 自定义 Bootstrap 配置属性源



1. 实现`PropertySourceLocator`

   ```java
   package com.segmentfault.springcloudlesson2.boostrap;

   import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
   import org.springframework.core.env.*;

   import java.util.HashMap;
   import java.util.Map;

   /**
    * 自定义 {@link PropertySourceLocator} 实现
    *
    * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
    * @since PropertySourceLocator
    */
   public class MyPropertySourceLocator implements PropertySourceLocator {

       @Override
       public PropertySource<?> locate(Environment environment) {

           if (environment instanceof ConfigurableEnvironment) {

               ConfigurableEnvironment configurableEnvironment = ConfigurableEnvironment.class.cast(environment);

               // 获取 PropertySources
               MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
               // 定义一个新的 PropertySource，并且放置在首位
               propertySources.addFirst(createPropertySource());

           }
           return null;
       }

       private PropertySource createPropertySource() {

           Map<String, Object> source = new HashMap<>();

           source.put("spring.application.name", "小马哥的 Spring Cloud 程序");
           // 设置名称和来源
           PropertySource propertySource = new MapPropertySource("over-bootstrap-property-source", source);

           return propertySource;

       }
   }
   ```

   ​

2. 配置`META-INF/spring.factories`

   ```properties
   org.springframework.cloud.bootstrap.BootstrapConfiguration= \
   com.segmentfault.springcloudlesson2.boostrap.MyConfiguration,\
   com.segmentfault.springcloudlesson2.boostrap.MyPropertySourceLocator
   ```

   ​







