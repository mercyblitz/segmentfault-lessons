# segmentfault-lession
Segment Fault 在线讲堂 代码工程


## Java 微服务实践 - Spring Boot 系列



### [第一节 初体验](https://segmentfault.com/l/1500000009515571) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-1))

* 主要内容

    * 微服务基本介绍：介绍微服务基本概念、技术发展的由来，以及目前流行的技术（主要针对Java 平台），Spring Boot、Spring Cloud、Spring Cloud Stream等
    * Spring Boot 介绍：介绍 Spring Boot 项目、选型理由、功能特性、参考文档、源码下载等
    * Spring Boot 初体验：Spring Boot 预备环境、新建 Spring Boot 应用、代码组织结构、运行方式以及创建简单REST 服务



### [第二节 Web篇（上）](https://segmentfault.com/l/1500000009659111) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-2))

* 主要内容：

    * 静态内容：简介传统 Web Server，Java Web Server 以及 Spring Boot 处理静态内容
    * 动态内容：介绍传统 Servlet 容器以及 Spring Boot 处理是如何处理动态内容
    * 模板引擎：介绍老、中、新三代模板引擎（传统 JSP ，中生代 Velocity、以及后现代 Thymeleaf）发展过程，Spring Boot 使用方法，以及三者的特征优劣



### [第三节 Web篇（中）](https://segmentfault.com/l/1500000009767025) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-3))

* 主要内容

    * REST 理论基础：基本概念、架构属性、架构约束、使用场景、实现框架（服务端、客户端）
    * REST 服务端实践：Spring Boot REST 应用、HATEOAS 应用、文档生成等
    * REST 客户端实践：传统浏览器、Apache HttpClient 、Spring RestTemplate 等相关实践



### [第四节 Web篇（下）](https://segmentfault.com/l/1500000009830944) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-4))

* 主要内容

    * 传统 Servlet 回顾：Servlet 核心接口、Servlet 组件开发和注册，以及应用部署等
    * Servlet on Spring Boot ：在 Spring Boot 环境下，开发和注册，以及部署Servlet 组件
    * JSP on Spring Boot：传统 JSP 组件在Spring Boot 环境下如何适配和运行



### [第五节 嵌入式Web容器](https://segmentfault.com/l/1500000009844304) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-5))

* 主要内容

    * 传统 Servlet 容器：介绍 Apache Tomcat、Eclipse Jetty。以 Tomcat 为例，常见容器配置和维护方式
    * 嵌入式Web容器：介绍嵌入式 Servlet 容器（Tomcat、Jetty）和 非 Servlet 容器（Undertown），配置和自定义嵌入式容器，以及相关限制



### [第六节 数据库 JDBC](https://segmentfault.com/l/1500000009904190)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-6))

* 主要内容

    * 数据源（DataSource）：分别介绍嵌入式数据源、通用型数据源以及分布式数据源
    * 事务（Transaction）：介绍事务原理，本地事务和分布式事务的使用场景
    * JDBC（JSR-221）：介绍JDBC 核心接口，数据源、数据库连接、执行语句、事务等核心API的使用方法
    * Spring Boot 整合：介绍和整合 spring-boot-starter-jdbc，解读 DataSource、JdbcTemplate 等自动装配原理



### [第七节 MyBatis](https://segmentfault.com/l/1500000009952220)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-7))

* 主要内容

    * MyBatis ：框架简介、核心API说明
    * 配置 ：全局XML配置、SQL Mapper XML 配置、SQL Mapper Annotation
    * 自动生成器    ：实例讲解代码、配置自动生成
    * Spring Boot 整合：整合 mybatis-spring-boot-starter



### [第八节 Java Persistence API](https://segmentfault.com/l/1500000009952527)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-8))

* 主要内容

    * JPA：介绍 JPA 技术背景、标准规范、JPA实现框架以及核心API
    * Spring Data JPA 整合：以 JPA 实现框架 Hibernate为例，实战整合 Spring Data JPA 技术
    * Spring Boot 整合：介绍和整合 spring-boot-stater-data-jpa



### [第九节 NoSQL](https://segmentfault.com/l/1500000009957330)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-9)) [[问答](https://segmentfault.com/l/1500000009957330/d/1560000010384965)]


* 主要内容：

    * NoSQL：介绍 NoSQL 使用背景、技术发展以及主流中间节
    * Spring Data 整合：以 Elasticsearch 为例，搭建 Elasticsearch ，整合 Spring Data 技术（如：ElasticsearchTemplate）
    * Spring Boot 整合：介绍和整合 spring-boot-starter-data-elaticsearch



### [第十节 缓存](https://segmentfault.com/l/1500000009970812)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-10)) [[问答](https://segmentfault.com/l/1500000009970812/d/1560000010509920)]

* 主要内容

    * Java Cache（JSR-107）：介绍 Java 标准 Cache 规范、核心 API、主流实现框架
    * Spring Cache：介绍 Spring Cache 核心 API、Cache 注册、管理等
    * Cache 实战：实战本地缓存 Guava 和分布式缓存 Redis
    * Spring Boot 整合：介绍和整合 spring-boot-starter-cache 和 spring-boot-starter-data-redis



### [第十一节 消息](https://segmentfault.com/l/1500000009971600)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-11)) [[问答](https://segmentfault.com/l/1500000009971600/d/1560000010580012)]

* 主要内容

    * JMS（JSR-914）：介绍 Java 标准消息服务规范、核心 API、以及主流实现框架
    * AMQP：简介 高级消息队列协议（AMQP）
    * Apache Kafka ：搭建 Kafka 环境，介绍核心 API
    * Spring Kafka 整合：Spring 实战整合 Kafka
    * Spring Boot 整合：Spring Boot 环境 整合 Kafka



### [第十二节 验证](https://segmentfault.com/l/1500000009971716) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-12)) [[问答](https://segmentfault.com/l/1500000009971716/d/1560000010630935)]

* 主要内容

    * Bean Validation（JSR-303）：介绍 Java Bean 验证、核心 API、实现框架 Hibernate Validator
    * Apache commons-validator ：介绍最传统 Apache 通用验证器框架，如：长度、邮件等方式
    * Spring Validator：介绍 Spring 内置验证器 API、以及自定义实现
    * Spring Boot 整合：解读 spring-boot-starter-validation



### [第十三节 WebSocket](https://segmentfault.com/l/1500000009971764) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-13)) [[问答](https://segmentfault.com/l/1500000009971764/d/1560000010698298)]

* 主要内容

    * WebSocket（JSR-356）：简介 WebSocket 协议、解释专业术语（如端点、端和会话等）
    * 生命周期：理解 WebSocket 生命周期（如：开启会话、关闭会话、接受消息等）
    * 核心接口：介绍 Java WebSocket 中的核心API（如：@ServerEndpoint、@ClientEndpoint、@OnOpen等）
    * 实现容器：当前实现 WebSocket 的主流容器，以及 Tomcat Comet 技术
    * Spring Boot 整合：介绍和整合 spring-boot-starter-websocket



### [第十四节 WebService](https://segmentfault.com/l/1500000009978309) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-14)) [[问答](https://segmentfault.com/l/1500000009978309/d/1560000010748545)]

* 主要内容

    * Web Services：介绍 WSDL 协议、SOAP协议 以及 XML-PRC
    * JAX-RS（JSR-224）：简介 The Java API for XML-Base Web Servces 2.0 规范
    * Web Serices Metadata（JSR-181）：介绍 Web Services Metadata 编程模型、注解、Java 映射等
    * Spring Boot 整合：介绍和整合 spring-boot-starter-ws



### [第十五节 安全](https://segmentfault.com/l/1500000009978481) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-15)) [[问答]](https://segmentfault.com/l/1500000009978481/d/1560000010817910)

* 主要内容

    * 客户端安全：介绍 CSRF、CSP、HTTP Struct Transport Security、X-Frames-Options、X-XSS-Protection 等
    * 服务端安全：Authentication 和 Authorization
    * Spring Boot 整合：介绍和整合 spring-boot-starter-security
    
    

### [第十六节 日志](https://segmentfault.com/l/1500000009978585)

* 主要内容

    * 日志框架：介绍主流日志框架，以及发展历程 Apache Log4j -> Java Logging -> Logback -> Apache Log4j2
    * 统一日志API：介绍统一日志API Apache commons-logging 以及 slf4-api
    * 日志设计模式：说明主流日志框架的设计模式
    * Spring Boot 整合：集合 spring-boot-starter-logging ，分析Spring Boot 日志系统设计



### [第十七节 监管](https://segmentfault.com/l/1500000009978661)

* 主要内容

    * JMX（JSR-3）：介绍 Java 标准管理规范，着重说明 MBean、MXBean、属性（Attribute）、操作（Operation）等相关概念
    * 核心API：实战的方式深入探讨服务端和客户端核心API的使用，加深理解
    * 客户端：讲解主流客户端（JConsole、JVisualVM）以及 HTTP 桥接框架 Jolokia
    * Spring Boot 整合：介绍 Spring 对 JMX 的扩展支持，随后再深入探讨自动装配模块
    


### [第十八节 配置](https://segmentfault.com/l/1500000009978661)

* 主要内容

    * 外部配置：介绍外部配置文件（Properties以及YAML方式)、命令行，以及占位符的使用
    * 配置引用：讲解如何通过编码的方式获取配置项值，以及将该值赋值致Bean的属性上
    * Prfoles：说明 Profiles 使用场景，以及在实际生产环节中如何合理的使用
    * Environment：通过实战的方式解决和说明Spring Environment接口，以及它与外部配置、Profiles之间的关系



### [第十九节 测试](https://segmentfault.com/l/1500000009978826)

* 主要内容
	
    * 单环测试：简介测试驱动开发，说明单元测试的必要性，以及主流的 Java 单元测试框架
    * Spring 应用测试 ：讲解如何 Spring Test 对Spring 应用进行集成测试
    * Spring Boot 应用测试：讲解 Spring Boot 应用如何合理地、细粒度地进行集成测试
    * 高端测试框架：介绍 AssertJ以及Mockito 如何高效地进行应用功能测试



### [第二十节 自定义启动器](https://segmentfault.com/l/1500000009978904)

* 主要内容

	* Spring Boot Starter：全面系统地介绍Spring Boot Starter的开发
    * 开发经验：根据实际的经验，分享相关的开发注意事项
	* 系列总结：`Java 微服务实践 - Spring Boot 系列`收尾，简单回顾期间的相关技术议题，并且预告下一个系列议题：`Java 微服务实践 - Spring Cloud 系列`



## Java 微服务实践 - Spring Cloud 系列


### 持续更新中，请期待



# 技术交流


## QQ群

![QQ交流群](https://segmentfault.com/img/bVPiTl)


## 讲师联系方式

### SF：mercyblitz
### QQ：mercyblitz@163.com
### 邮箱：mercyblitz@gmail.com
### 微信：mercyblitz

为了更高效的沟通，请注明联系来源哦！
