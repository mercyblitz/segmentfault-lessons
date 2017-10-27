Segment Fault 在线讲堂 代码工程 
=====================================

# 简介

目前业界最流行的微服务架构正在或者已被各种规模的互联网公司广泛接受和认可，业已成为互联网开发人员必备技术。无论是互联网、云计算还是大数据，Java平台已成为全栈的生态体系，其重要性几乎不可替代。

“Java 微服务实践”为系列课程，内容包括目前最流行技术，分为 Spring Boot、Spring Cloud、Spring Cloud Stream 等系列，其目的希望能够帮助初学者深入浅出地掌握，同时更希望为高阶从业人员起到抛砖引玉的作用。同时，系列课程内容与主讲人的正在编写书籍同步，方便未来查阅。


# 讲师信息

小马哥，一线互联网技术专家，国内微服务技术客串讲师，目前主要负责微服务技术推广、架构设计、基础设施、迁移等。重点关注云计算、微服务以及软件架构等领域。从事十余年Java EE 开发，期间通过SUN Java（SCJP、SCWCD、SCBCD）以及Oracle OCA等的认证。


# 优惠报名

## [`Spring Boot / Spring Cloud 双系列`](https://segmentfault.com/ls/1650000011387052)（原价：~~1024~~，优惠价：`700`）

## [`Spring Cloud 系列`](https://segmentfault.com/ls/1650000011386794)（原价：~~512~~，优惠价：`399`）

## [`Spring Boot 系列`](https://segmentfault.com/ls/1650000011063780)（原价：~~512~~，优惠价：`360`）


# 课程详情

## Java 微服务实践 - Spring Boot 系列 （[一键报名，更优惠！](https://segmentfault.com/ls/1650000011063780)） [[Github 代码工程](https://github.com/mercyblitz/segmentfault-lessons)]


### [第一节 初体验](https://segmentfault.com/l/1500000009515571)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-1))

* 主要内容

    * 微服务基本介绍：介绍微服务基本概念、技术发展的由来，以及目前流行的技术（主要针对Java 平台），Spring Boot、Spring Cloud、Spring Cloud Stream等
    * Spring Boot 介绍：介绍 Spring Boot 项目、选型理由、功能特性、参考文档、源码下载等
    * Spring Boot 初体验：Spring Boot 预备环境、新建 Spring Boot 应用、代码组织结构、运行方式以及创建简单REST 服务



### [第二节 Web篇（上）](https://segmentfault.com/l/1500000009659111) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-2))

* 主要内容：

    * 静态内容：简介传统 Web Server，Java Web Server 以及 Spring Boot 处理静态内容
    * 动态内容：介绍传统 Servlet 容器以及 Spring Boot 处理是如何处理动态内容
    * 模板引擎：介绍老、中、新三代模板引擎（传统 JSP ，中生代 Velocity、以及后现代 Thymeleaf）发展过程，Spring Boot 使用方法，以及三者的特征优劣



### [第三节 Web篇（中）](https://segmentfault.com/l/1500000009767025)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-3))

* 主要内容

    * REST 理论基础：基本概念、架构属性、架构约束、使用场景、实现框架（服务端、客户端）
    * REST 服务端实践：Spring Boot REST 应用、HATEOAS 应用、文档生成等
    * REST 客户端实践：传统浏览器、Apache HttpClient 、Spring RestTemplate 等相关实践



### [第四节 Web篇（下）](https://segmentfault.com/l/1500000009830944)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-4))

* 主要内容

    * 传统 Servlet 回顾：Servlet 核心接口、Servlet 组件开发和注册，以及应用部署等
    * Servlet on Spring Boot ：在 Spring Boot 环境下，开发和注册，以及部署Servlet 组件
    * JSP on Spring Boot：传统 JSP 组件在Spring Boot 环境下如何适配和运行



### [第五节 嵌入式Web容器](https://segmentfault.com/l/1500000009844304)([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-5))

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



### [第十五节 安全](https://segmentfault.com/l/1500000009978481) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-15)) [[问答](https://segmentfault.com/l/1500000009978481/d/1560000010817910)]

* 主要内容

	* 客户端安全：介绍 CSRF、CSP、HTTP Struct Transport Security、X-Frames-Options、X-XSS-Protection 等
    * 服务端安全：Authentication 和 Authorization
    * Spring Boot 整合：介绍和整合 spring-boot-starter-security
    
    

### [第十六节 日志](https://segmentfault.com/l/1500000009978585) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-16)) [[问答](https://segmentfault.com/l/1500000009978585/d/1560000010867242)]

* 主要内容

	* 日志框架：介绍主流日志框架，以及发展历程 Apache Log4j -> Java Logging -> Logback -> Apache Log4j2
    * 统一日志API：介绍统一日志API Apache commons-logging 以及 slf4-api
    * 日志设计模式：说明主流日志框架的设计模式
    * Spring Boot 整合：集合 spring-boot-starter-logging ，分析Spring Boot 日志系统设计



### [第十七节 监管](https://segmentfault.com/l/1500000009978661) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-17)) [[问答](https://segmentfault.com/l/1500000009978661/d/1560000010933454)]

* 主要内容

	* JMX（JSR-3）：介绍 Java 标准管理规范，着重说明 MBean、MXBean、属性（Attribute）、操作（Operation）等相关概念
    * 核心API：实战的方式深入探讨服务端和客户端核心API的使用，加深理解
    * 客户端：讲解主流客户端（JConsole、JVisualVM）以及 HTTP 桥接框架 Jolokia
    * Spring Boot 整合：介绍 Spring 对 JMX 的扩展支持，随后再深入探讨自动装配模块
    


### [第十八节 配置](https://segmentfault.com/l/1500000009978661) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-18)) [[问答](https://segmentfault.com/l/1500000009978729/d/1560000011093017)]

* 主要内容

	* 外部配置：介绍外部配置文件（Properties以及YAML方式)、命令行，以及占位符的使用
    * 配置引用：讲解如何通过编码的方式获取配置项值，以及将该值赋值致Bean的属性上
    * Prfoles：说明 Profiles 使用场景，以及在实际生产环节中如何合理的使用
    * Environment：通过实战的方式解决和说明Spring Environment接口，以及它与外部配置、Profiles之间的关系



### [第十九节 测试](https://segmentfault.com/l/1500000009978826) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-19)) [[问答](https://segmentfault.com/l/1500000009978826/d/1560000011154349)]

* 主要内容
	
    * 单环测试：简介测试驱动开发，说明单元测试的必要性，以及主流的 Java 单元测试框架
    * Spring 应用测试 ：讲解如何 Spring Test 对Spring 应用进行集成测试
    * Spring Boot 应用测试：讲解 Spring Boot 应用如何合理地、细粒度地进行集成测试
    * 高端测试框架：介绍 AssertJ以及Mockito 如何高效地进行应用功能测试



### [第二十节 自定义启动器](https://segmentfault.com/l/1500000009978904) ([课件](https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-boot/lesson-20)) [[问答](https://segmentfault.com/l/1500000009978904/d/1560000011202690)]

* 主要内容

	* Spring Boot Starter：全面系统地介绍Spring Boot Starter的开发
    * 开发经验：根据实际的经验，分享相关的开发注意事项
	* 系列总结：`Java 微服务实践 - Spring Boot 系列`收尾，简单回顾期间的相关技术议题，并且预告下一个系列议题：`Java 微服务实践 - Spring Cloud 系列`



## Java 微服务实践 - Spring Cloud 系列（[一键报名，更优惠！](https://segmentfault.com/ls/1650000011386794)） 

Spring Cloud 为开发人员提供快速构建分布式系统的一些通用模式，其中包括：配置管理、服务发现、服务短路、智能路由、微型网关、控制总线、一次性令牌、全局锁、领导选举、分布式会话和集群状态。分布式系统间的协调导向样板模式，并且使用 Spring Cloud 的开发人员能够快速地构建实现这些模式的服务和应用。这些服务和应用也将在任何环境下工作良好，无论是开发者的笔记本、还是数据中心裸机或者管控平台。


### 课程特点

Spring Cloud 系列课程致力于以实战的方式覆盖 Spring Cloud 的功能特性，更为重要的是，小马哥希望通过“授人以渔”的方式，不仅让小伙伴们能够认识到技术的衍进并非凭空遐想，而是在其特定的场景下“生根发芽”，并且结合自身十余年的学习方法和工作经验，将技术的发展脉络贯穿其中。循序渐进式地引导朋友们，站在哲学的高度，体会 Spring Cloud 的作者设计意图。同时，结合 Spring Cloud 的源码加深理解，最终达到形成系统性的知识和技术体系的目的。


### [第一节 云原生应用（Cloud Native Applications）](https://segmentfault.com/l/1500000011384570/play) ([课件](https://github.com/mercyblitz/segmentfault-lessons/blob/master/spring-cloud/lesson-1/)) [[问答](https://segmentfault.com/l/1500000011384570/d/1560000011687339)]

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 - 2 小时）
    * 系列介绍：介绍 Spring Cloud 系列课程内容、讲述方式以及整体技术栈，大体上分为：技术核心理念、使用方法和经验、以及源码分析三大类
    * 储备力量：介绍深入系统化学习 Spring Cloud 所预备的理论和技术储备。架构方面，如：面向服务架构（SOA）、微服务架构、事件驱动架构。技术方面，如：Java 8、Spring Framework、Spring Boot，以及搭建云应用的运行环境基本要素
    * 理解 12-Factor 应用：介绍 12-Factor 应用，并且结合技术体系，如传统 Java EE、 Spring Framework 以及 Spring Boot 
    * Bootstrap 应用上下文：介绍 Spring Cloud 新引入的 Bootstrap 应用上下文，说明其与 Spring Framework 应用上下文之间的联系，进一步理解 Bootstrap 应用上下文在 Spring Boot 应用中的层次关系
    * 端点介绍：介绍 Spring Cloud 在 Spring Boot 基础上新引入的端点（Endpoint），比如：上下文重启:`/restart`、生命周期：`/pause`、`/resume`等
* 本章小结（10 分钟）
* 问答互动（10 分钟）




### [第二节 配置客户端（Spring Cloud Config Client）](https://segmentfault.com/l/1500000011385195/play) （[课件]https://github.com/mercyblitz/segmentfault-lessons/tree/master/spring-cloud/lesson-2） [[问答](https://segmentfault.com/l/1500000011385195/d/1560000011752523)]

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 - 2 小时）
    * 技术回顾：回顾提及的 Environment、以及 Spring Boot 配置相关的事件和监听器，如`ApplicationEnvironmentPreparedEvent`和`ConfigFileApplicationListener`，
    * Bootstrap 配置属性：解密 Bootstrap 配置属性与 Spring Framework / Spring Boot 配置架构的关系，介绍如何调整 Bootstrap 配置文件路径、覆盖远程配置属性、自定义 Bootstrap 配置以及自定义 Bootstrap 配置属性源
    * Environment 端点：介绍`/env` 端点的使用场景，并且解读其源码，了解其中奥秘
    * 安全：介绍客户端配置安全相关议题


* 本章小结（10 分钟）
* 问答互动（10 分钟）



### 第三节 配置服务器（Spring Cloud Config Server）

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 - 2 小时）
    * 基本使用：介绍`@EnableConfigServer`、`Environment` 仓储、秘钥管理等基本使用方法
    * 分布式配置官方实现：介绍 Spring 官方标准分布式配置实现方式：Git实现 和 文件系统实现
    * 动态配置属性 Bean：介绍`@RefreshScope`基本用法和使用场景，并且说明其中的局限性
    * 健康指标：介绍 Spring Boot 标准端口（`/health`）以及 健康指标（Health Indicator）
    * 分布式配置自定义实现：基于配置管理容器 Zookeeper ，自定义实现分布式配置能力
    * 健康指标自定义实现：实现分布式配置的健康指标自定义实现


* 本章小结（10 分钟）
* 问答互动（10 分钟）



### 第四节 服务发现/注册（Service Discovery/Registry）

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 - 2 小时）
    * 前微服务时代：介绍前微服务时代，服务发现和注册在 SOA 甚至是更早的时代的技术实现和实施方法，如 WebService 中的UDDI、REST 中的 HEATOAS
    * 高可用架构：简介高可用架构的基本原则，计算方法和系统设计
    * Eureka 客户端：介绍 Spring Cloud Discovery 结合 Netflix Eureka 客户端的基本使用方法，包括服务发现激活、Eureka 客户端注册配置 以及 API 使用等
    * Eureka 服务器：介绍 Eureka 服务器作为服务注册中心的搭建方法，以及內建 Dashboard 基本运维手段


* 本章小结（10 分钟）
* 问答互动（10 分钟）



### 第五节 高可用服务治理（HA Service Government）

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 - 2 小时）
    * Eureka 高可用集群：搭建 Eureka 高可用集群，消除服务注册中心单点失效的问题
    * Eureka 异地化集群：搭建 Eureka 异地化集群，实现不同区域/地区之间的服务状态同步
    * Consul 高可用集群：相对于 Eureka 作为服务注册中心，Consul 提供更加的状态一致性保证，通过搭建 Consul  高可用服务治理集群


* 本章小结（10 分钟）
* 问答互动（10 分钟）



### 第六节 负载均衡（Load Balance）

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 - 2 小时）
    * 理论基础：简介负载均衡客户端和服务端的相关理论，包括调度算法：如先来先服务、轮训、多级队列等。基本特性：非对称负载、健康检查、优先级队列等
    * 技术回顾：回顾 Spring Framework HTTP 组件 RestTemplate 的使用方法，结合 ClientHttpRequestInterceptor 实现简单负载均衡客户端
    * 整合Netflix Ribbon：作为 Spring Cloud 客户端负载均衡实现 ，Netflix Ribbon 提供了丰富的组件，包括负载均衡器、负载均衡规则、PING 策略等，根据前章所积累的经验，实现客户端负载均衡
* 本章小结（10 分钟）
* 问答互动（10 分钟）




### 第七节 Netflix Ribbon 源码解读

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 小时）
    * RestTemplate 部分：解读 RestTemplateCustomizer 设计意图、处理逻辑以及请求拦截实现
    * 客户端部分：解读`@LoadBalanced` 实现原理以及最终实际请求的相关实现
    * 负载均衡部分：解读负载均衡上下文、负载均衡器、负载均衡规则、PING 策略等相关实现


* 本章小结（10分钟）
* 问答互动（20分钟）



### 第八节 服务短路（Circuit Breakers）

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 - 2 小时）
    * 核心理念：介绍服务短路的名词由来、目的，以及相关的类似慨念。随后讲述其中设计哲学、触发条件、处理手段以及客户端和服务端实现方法
    * Spring Cloud Hystrix：作为服务端服务短路实现，介绍 Spring Cloud Hystrix 常用限流的功能，同时，说明健康指标以及数据指标在生产环境下的现实意义
    * 生产准备特性：介绍聚合数据指标 Turbine 、Turbine Stream，以及整合 Hystrix Dashboard




### 第九节 Spring Cloud Hystrix 源码解读

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 小时）
  * RxJava：回顾传统观察者模式、反应器模式等设计模式，说明这些模式与 Reactive 模式的联系，更好地解读 Reactive Java 框架相关实现源码，如：`Observer`、`Subscriber`等
  * Netflix Hystrix：Netflix Hystrix 作为 Spring Cloud Hystrix 底层实现，结合 RxJava 框架进行实现，如核心接口`HystrixCommand`，属性设置`HystrixCommandProperties`以及配置信息`HystrixConfiguration`等
  * Spring Cloud Hystrix：详细解读`@EnableCircuitBreaker` 处理过程，以及`@HystrixCommand` AOP 拦截实现等

* 本章小结（10分钟）
* 问答互动（20分钟）



### 第十节 服务调用（Service Call）

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 - 2 小时）
  * 核心理念：回顾远程服务调用（RPC）的核心理念，介绍接口定义语言（IDL）以及服务存根（Stubs）以及通讯协议，如二进制协议 RMI、文本协议 REST 等
  * Spring Cloud Feign ：介绍声明式客户端REST实现 Spring Cloud Feign的使用方式（如`@EnableFeignClients` 、 `@FeignClient`)，结合 Eureka 构建分布式服务应用
  * 整合支持：Spring Cloud Feign 整合 Hystrix 以及 Ribbon

* 本章小结（10分钟）
* 问答互动（10分钟）

### 第十一节 Spring Cloud 服务网关

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 小时）
  * 核心概念：介绍服务网关使用场景、服务能力、依赖关系、架构以及类型
  * 服务端实现：介绍 Spring Cloud Zuul 使用场景、依赖管理、生命周期、源码分析、设计模式
  * 整合支持：解读 Hystrix 以及 Ribbon
* 本章小结（10分钟）
* 问答互动（20分钟）

### 第十二节 消息驱动整合

* 受众群体：具备一定的`Java`服务端编程经验更佳
* 课程内容（1.5 小时）
	* Spring Cloud Stream 
	* Kafka 绑定实现
	* RabbitMQ 绑定实现
* 本章小结（10分钟）
* 问答互动（20分钟）

### 第十三节 Spring Cloud Stream Binder 实现

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 小时）
	* Spring Cloud Stream Binder：实现 ActiveMQ Spring Cloud Stream Binder
* 本章小结（10分钟）
* 问答互动（20分钟）

### 第十四节 消息总线

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 小时）
	* 面向消息服务器
	* Kafka 实现
	* RabbitMQ 实现 

* 本章小结（10分钟）
* 问答互动（20分钟）

### 第十五节 分布式应用跟踪

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 小时）
	* 分布式应用跟踪
	* ZipKin 整合
	
* 本章小结（10分钟）
* 问答互动（20分钟）

### 第十六节 Spring Cloud 系列回顾

* 受众群体：具备一定的`Java`服务端编程经验更佳

* 课程内容（1.5 小时）

* 本章小结（10分钟）
* 问答互动（20分钟）


# 技术交流


## QQ群

![QQ交流群](https://segmentfault.com/img/bVPiTl)


## 讲师联系方式

### SF：mercyblitz
### QQ：mercyblitz@163.com
### 邮箱：mercyblitz@gmail.com
### 微信：mercyblitz

为了更高效的沟通，请注明联系来源哦！
