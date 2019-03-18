# 「一入 Java 深似海 」系列课程 - 第一期 第二节：Java 面向对象设计



## Java 接⼝设计



### 通⽤设计 - 可访问性

#### 四种修饰符

- public 

- (default)

- protected：不能用于修饰最外层 class

- private：不能用于修饰最外层 class


### 通⽤设计 - 可继承性



### 具体类设计

### 常⻅场景

- 功能组件
  - HashMap
- 接⼝/抽象类实现
  - HashMap <- AbstractMap <- Map
- 数据对象
  - POJO
- ⼯具辅助
  - *Utils
  - ViewHelper
  - Helper



### 抽象类设计

#### 常⻅场景

- 接⼝通⽤实现（模板模式）

  - AbstractList
  - AbstractSet
  - AbstractMap

- 状态/⾏为继承


### 接口设计

- Serializable
- Cloneable
- AutoCloseable
- EventListener



## 枚举设计

- 枚举(enum) 实际是 final class
- 枚举(enum) 成员修饰符为 public static final 
- `values()` 是 Java 编译器做的字节码提升
