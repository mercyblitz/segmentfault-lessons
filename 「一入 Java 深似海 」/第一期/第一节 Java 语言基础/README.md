



## Java ⾯向对象基础



### ⽅法设计

- 单元：一个类或者一组类（组件）
  - 类采用名词结构
    - 动词过去式+名词
      - ContextRefreshedEvent
    - 动词ing + 名词
      - InitializingBean
    - 形容词 + 名词
      - ConfigurableApplicationContext
- 执行：某个方法
  - 方法命名：动词
    - execute
    - callback
    - run
  - 方法参数：名词
  - 异常：
    - 根（顶层）异常
      - Throwable
        - checked 类型：Exception
        - unchecked类型：RuntimeException
        - 不常见：Error
    - Java 1.4 `java.lang.StackTraceElement`
      - 添加异常原因（cause）
        - 反模式：吞掉某个异常
        - 性能：注意 `fillInStackTrace()` 方法的开销，避免异常栈调用深度
          - 方法一：JVM 参数控制栈深度（物理屏蔽）
          - 方法二：logback 日志框架控制堆栈输出深度（逻辑屏蔽）

## 泛型设计

Java 泛型属于编译时处理，运行时擦写。



## Java 函数式设计



### 匿名内置类

基本特性：

- 无名称类
- 声明位置(执行模块)：
  - static block 
  - 实例block 
  - 方法
  - 构造器
- 并非特殊的类结构
  - 类全名称：${package}.${declared_class}.${num}



## 函数式接口

基本特性：

- 所有的函数式接口都引用一段执行代码
- 函数式接口没有固定的类型，固定模式（ SCFP = Supplier + Consumer + Function + Predicate） + Action
- 利用方法引用来实现模式匹配



## 模块化

### 强封装性

基本特性

- 并非所有的 `public` Class 都可以被运用，需要 `exports` 来配合
- `exports` 所配置的 `package` 下必须要有 Class

负面问题

- 对人的要求就高了（对 Class 透明化）
  - 必须了解相关 `module-info.java` 语义
  - 需要了解某些类的依赖
  - 需要了解某些类职责

个人观点

- 收益不大，代价不小
- 对团队要求极高，容易出现互喷的情况
- Java 9 之前采用 jar 文件管理，Java 9 模块化之后，编程了 module-info.java 管理，还需要考虑与 Maven 依赖管理组件如何配合