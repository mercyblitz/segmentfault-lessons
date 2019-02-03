# [「一入 Java 深似海 」系列课程](https://segmentfault.com/n/1330000017785588) 


## 第二期：Java 集合框架

### [第一节 Java 模块化设计](https://segmentfault.com/l/1500000018003647)

- 主要内容
  - 模块化构建：Java、Maven、IDE 等构建方式

  - 模块化迁移：类库迁移、依赖管理分析、迁移案例等）

  - 模块化设计：模块解析、模块API 设计

  - 模块化反射：模块内省、模块运行时调整、模块注解

  - 课程总结

    

###  [第二节 Java 集合（Collections）框架基础运用](https://segmentfault.com/l/1500000018003713)

- 主要内容
  - 语义接口：包括 `Collection`、`Set`、`List`、`Queue` 、`Map` 等核心接口
  - 内建实现：讨论 JDK 中内建的集合接口实现，并且说明同类实现中的使用场景，如  `Vector`、`ArrayList` 以及 `LinkedList` 场景
  - 抽象实现：介绍 Java 集合框架的骨架实现，如  `AbstractCollection`、`AbstractSet`以及 `AbstractList` 等抽象类



### [第三节  Java 集合（Collections）框架高级运用](https://segmentfault.com/l/1500000018003765)

- 主要内容
  - Wrapper 实现：unmodifiable、synchronized 集合接口的使用场景
  - 适配实现：讨论`Set` 转变为 `Map`，以及 `Deque` 实现 LIFO 的 `Queue` 实现
  - 特殊实现：如 `WeakHashMap`、`IdentityHashMap` 等特殊实现的使用场景
  - 工厂方法：`List`、`Set` 以及 `Map` 的便利工厂方法运用，以及单体和副本的操作方法



### [第四节 Java 集合（Collections）框架算法运用](https://segmentfault.com/l/1500000018003874)

- 主要内容
  - 排序算法：主要讨论 JDK 中出现过的排序算法，如 Insertion Sort、Merge Sort 、以及 TimSort，包括基本思路、时间和空间复杂度
  - 搜索算法：讨论二进制搜索算法，如 `Collections#binarySearch` 方法