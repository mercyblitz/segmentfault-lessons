#  [「一入 Java 深似海 」系列 第七期 第一节 Java 反射](https://segmentfault.com/n/1330000020711719)



## 反射基础知识







### Java 反射的缺陷

#### 性能开销

java.lang.Class 所有类对象的定义类，同时是所有反射的入口

- 加载类需要时间和空间开销
- 反射解析也需要时间和空间开销
- 反射的执行需要校验



#### 安全限制

##### Java 安全框架 - Java Security

- java.security.Permission
  - java.security.BasicPermission
    - java.lang.reflect.ReflectPermission

java.lang.SecurityManager#checkPermission(java.security.Permission)



#### 暴露内部结构

反射会打破 OOP 对象/类的封装



### Java 类成员（Class Members）



#### 字段（Fields）

Java 字段两类：类字段、对象字段

字段（Field）本身是元数据，包含的内容是数据（属性），有称之为状态信息



#### 方法（Methods）

方法是描述对象“行为”的契约（抽象）或者实现（具体）

方法唯一约束是方法签名：

- 归属（定义所在）
- 访问修饰符（public）
- 返回类型（return type）
- 方法名称（method name）
- 方法参数（method parameters）
- 方法异常列表（method throws）



#### 构造器（Constructors）

签名：

- 归属（定义所在）
- 访问修饰符（public）
- 构造器名称（Constructor name = Class Name）
- 构造器参数（method parameters）
- 构造器异常列表（method throws）



方法与构造器又归类为可执行对象





## Java 反射 API 运用

Java 中所有类型不一定是对象（Object），然而每种类型都类对象（java.lang.Class）

Java 类型

- 对象类型（java.lang.Object 子类）
- 数组类型（[]）
- 原生类型（boolean、char、int 等）
- 特殊类型（void 仅用在方法返回类型中）

Java 类型对象（java.lang.Class）

- java.lang.Object.class
- x[].class
- x.class
- void.class





- java.lang.reflect.Type
  - java.lang.reflect.ParameterizedType - 带有泛型参数的类型
  - java.lang.Class                                        - 普通类型

