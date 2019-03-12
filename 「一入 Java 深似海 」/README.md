# [「一入 Java 深似海 」系列课程](https://segmentfault.com/n/1330000017785588)



## 课程简介

「一入 Java 深似海 」系列是[小马哥](https://mercyblitz.github.io/about/) 2019 年在 [SegmentFault](https://segmentfault.com/u/mercyblitz) 平台发布的全新付费课程，内容围绕 **Java 生态体系**而展开，从**编程语言**、**编程模型**、**代码设计**，**框架实现**以及**架构实践**等，致力于将个人经验悉数分享给诸位，无论您是刚入门学习的新人，还是从业数年的朋友希望能够从中得到一点启发。



## 个人简介

[小马哥](https://mercyblitz.github.io/about/)，Java 劝退师，Apache 和 Spring Cloud 等知名开源架构成员。

> Github : https://github.com/mercyblitz
>
> 微信/微博：**mercyblitz**



## 授课方式

课程全程安排在线上完成，通过在线直播和课后录播两种方式（暂时无法提供文件）。



## 时间安排

课程将采用周期直播的方式，以自然月为周期，如 2019 年 1 月为第一期。每个周期将安排 **4 - 5** 次直播，围绕单个主题深入讨论，具体时间和细节请参考【内容详情】。



## 优惠活动

### 早鸟用户

早鸟用户将享受推广期限时折扣，请及时关注当前页面的最新优惠活动。



### 全日制在校大学生

- 普通大学生：凭学生证，享受半价优惠

- 品学兼优者：凭奖学金证书，领取“半价优惠”折上折

  - 甲等（一等）：7折
  - 乙等（二等）：8折
  - 丙等（三等）：9折

- 贫困/残疾/特殊：提供相关证明，申请免费资格

  > 命运或许对您不公平，然而努力就有希望~



## 内容详情

> 提示：课程内容和时间可能根据实际情况作出调整，请及时关注最新的信息



### [第一期：Java 语言基础与代码设计](https://segmentfault.com/n/1330000017785786) 

#### [第一节：Java 语言基础](https://segmentfault.com/ls/1650000017791749/l/1500000017790143)

- 主要内容
  - Java 面向过程编程：介绍 Java 中主要的数据结构、方法调用、执行流程
  - Java 面向对象基础：针对面向对象三大特性“封装性、派生性、多态性”作出具体化的说明
  - Java 函数式基础：针对 Java 8 Lambda 语法特性展开讨论，包括“函数式接口、默认方法、方法引用” 等方面，着重探讨函数式编程设计
  - Java 模块化基础：主要围绕 Java 动态代理，字节码提升
  - 课程总结



#### [第二节：Java 面向对象设计（上）](https://segmentfault.com/ls/1650000017791749/l/1500000017790217)

- 主要内容
  - 类/接口设计：介绍类、抽象类、接口命名、访问性设计以及“封装性、派生性、多态性”技巧
  - 枚举设计：理解枚举引入的意义以及变化，介绍 JDK 中常见枚举的设计
  - 课程总结



#### [第三节：Java 面向对象设计（下）](https://segmentfault.com/ls/1650000017791749/l/1500000017790336)

- 主要内容
  - 泛型设计：了解泛型使用场景以及限制，通过 JDK 以及开源项目领悟其中的设计模式
  - 方法设计：介绍方法命名、返回类型、参数类型、名称以及数量的设计模式
  - 课程总结



#### [第四节：Java 函数式设计](https://segmentfault.com/ls/1650000017791749/l/1500000017790383)

- 主要内容
 - 函数式设计：讨论函数式设计技巧，如 `Supplier`、`Consumer` 以及 `Function` 在日常开发中的运用
  - `Stream` API：使用场景、常见操作以及注意事项
  - `Stream` API 设计：根据 `Stream` API 的特点，结合“SCFP”的特性从中设计相关的函数接口
  - 课程总结



### [第二期：Java 集合框架](https://segmentfault.com/ls/1650000018006398)

#### [第一节 Java 模块化设计](https://segmentfault.com/l/1500000018003647)

- 主要内容
  - 模块化构建：Java、Maven、IDE 等构建方式

  - 模块化迁移：类库迁移、依赖管理分析、迁移案例等）

  - 模块化设计：模块解析、模块API 设计

  - 模块化反射：模块内省、模块运行时调整、模块注解

  - 课程总结

    

####  [第二节 Java 集合（Collections）框架基础运用](https://segmentfault.com/l/1500000018003713)

- 主要内容
  - 语义接口：包括 `Collection`、`Set`、`List`、`Queue` 、`Map` 等核心接口
  - 内建实现：讨论 JDK 中内建的集合接口实现，并且说明同类实现中的使用场景，如  `Vector`、`ArrayList` 以及 `LinkedList` 场景
  - 抽象实现：介绍 Java 集合框架的骨架实现，如  `AbstractCollection`、`AbstractSet`以及 `AbstractList` 等抽象类



#### [第三节  Java 集合（Collections）框架高级运用](https://segmentfault.com/l/1500000018003765)

- 主要内容
  - Wrapper 实现：unmodifiable、synchronized 集合接口的使用场景
  - 适配实现：讨论`Set` 转变为 `Map`，以及 `Deque` 实现 LIFO 的 `Queue` 实现
  - 特殊实现：如 `WeakHashMap`、`IdentityHashMap` 等特殊实现的使用场景
  - 工厂方法：`List`、`Set` 以及 `Map` 的便利工厂方法运用，以及单体和副本的操作方法



#### [第四节 Java 集合（Collections）框架算法运用](https://segmentfault.com/l/1500000018003874)

- 主要内容
  - 排序算法：主要讨论 JDK 中出现过的排序算法，如 Insertion Sort、Merge Sort 、以及 TimSort，包括基本思路、时间和空间复杂度
  - 搜索算法：讨论二进制搜索算法，如 `Collections#binarySearch` 方法

  

### [第三期：Java 并发基础](https://segmentfault.com/ls/1650000018320970)

#### [第一节：Java 线程与进程](https://segmentfault.com/l/1500000018320263)

- 主要内容
   - Java 线程状态：理解 Java 线程状态（`Thread.State`）实际意义
   - Java 线程生命周期：介绍 `java.lang.Thread` API 启动、停止等生命周期方法，如：`Thread.start`、`Thread.interrupt`和 `Thread.interrupted`，并理解为什么弃用 `Thread.stop`, `Thread.suspend` 和 `Thread.resume` 方法
   - Java 线程通讯：实战 Java 线程之间通讯，以及父子线程中通讯的方式
   - Java 进程管理：介绍管理当前 JVM 进程、管理子进程以及 Java 9 API 在进程中的提升
   - 面试题解析：解析 Java 线程与进程相关的面试题
   
#### [第二节：Java 并发编程基础](https://segmentfault.com/l/1500000018320430)

- 主要内容
  - Java 并发理论基础：理解线程安全（Thread Safety）、阻塞同步（ Blocking Synchronization）非阻塞同步（Non-Blocking Synchronization）、 临界区（Critical Section）、锁（Lock）、监视器（Monitor）以及重进入（Reentrant）等
  - Java 同步原语：从 JDK 级别到 JVM 级别，深度分析和理解 Java `synchronized` 、`volatile` 以及 CAS 操作等同步原语
  - Java 线程 Liveness：实战演示 Java 线程死锁（DeadLock）和饥饿（Starvation）
  - Java 并发经典模型：实战演示 Java 并发场景下的生产者和消费者模型
  - 面试题解析：解析 Java 并发基础面试题
  
#### [第三节：Java 并发框架（J.U.C）基础运用](https://segmentfault.com/l/1500000018320563)

- 主要内容
  - Java 并发锁：介绍包括重进入锁（`ReentrantLock`）、重进入读写锁（`ReentrantReadWriteLock`）、邮票锁（`StampedLock`）等运用
  - Java 原子操作：理解 `Atomic*` API 以及 `*Adder` API 使用场景
  - Java 并发限制：包括 `CountDownLatch`、`CyclicBarrier` 以及 `Semaphore`
  - Java 线程池：掌握 `Executor`、`ThreadPoolExecutor` 、`ScheduledExecutorService` 以及 `Callable` 和 `Future` API 正确使用
  - 面试题解析：解析 J.U.C 相关的面试题
  
#### [第四节：Java 并发框架（J.U.C）高级运用](https://segmentfault.com/l/1500000018320681)

- 主要内容
  - Java 并发集合框架：掌握 `CopyOnWrite*`、`ConcurrentSkipList*` 、`ConcurrentHashMap` 以及 `*BlockingDueue` API 使用场景和实现差异
  - Java 7 Fork/Join 框架：掌握 Java 7 Fork/Join 框架的使用以及理解使用场景
  - Java 8 `CompletableFuture` 设计：分享  `CompletableFuture` 设计技巧
  - Java 9 Flow 框架：Flow API 理解 Reactive Streams 框架的实现原理，并且解读 JDK 9 内建实现在 Reactive Streams API，如 HttpClient 实现
  - 面试题解析：解析 Java 并发集合框架等相关的面试题


### 第四期：Java 并发原理

- 第一节：OS 并发原理
- 第二节：Java 内存模型（Java Memory Model）
- 第三节：Java 并发框架（J.U.C） AQS 原理
- 第四节：JVM 并发实现




### 第五期：Java I/O



### 第六期：Java 元编程



### 【其他持续更新中...】



## 社区交流

- 小马哥 VIP 交流群：**571055366**

  >  VIP 交流群仅提供给报名的小伙伴，敬请谅解！



## 课程资源

- 代码工程：https://github.com/mercyblitz/segmentfault-lessons



## 相关课程

### [Java 微服务实战系列课堂](https://segmentfault.com/n/1330000009887617)

- [Java 微服务实践 - Spring Boot / Spring Cloud](https://segmentfault.com/ls/1650000011387052)

- [Java 微服务实践 - Spring Boot 系列](https://segmentfault.com/ls/1650000011063780)

- [Java 微服务实践 - Spring Cloud 系列](https://segmentfault.com/ls/1650000011386794)
