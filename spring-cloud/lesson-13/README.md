# [Spring Cloud Stream Binder 实现](https://segmentfault.com/l/1500000011386655)





## JMS 实现 ActiveMQ



### 增加 Maven 依赖



```xml
        <!-- 整合 Sprig Boot Starter ActiveMQ -->
        <!-- 间接依赖：
            spring jms
            jms api
            activemq
            spring boot jms
        -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-activemq</artifactId>
        </dependency>
```



### 启动 ActiveMQ Broker



#### 安装

```
$ brew install apache-activemq
```



#### 启动

```
$ activemq console
```



### 原生API：生产消息



请注意启动后的控制台输出：

```
INFO | Listening for connections at: tcp://Mercy-MacBook-Pro.local:61616?maximumConnections=1000&wireFormat.maxFrameSize=104857600
```



其中 `tcp://Mercy-MacBook-Pro.local:61616` 就是 broker URL，请注意将主机名转换成 localhost：

`tcp://localhost:61616`



```java
private static void sendMessage() throws Exception {
        // 创建 ActiveMQ 链接，设置 Broker URL
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // 创造 JMS 链接
        Connection connection = connectionFactory.createConnection();
        // 创建会话 Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建消息目的 - Queue 名称为 "TEST"
        Destination destination = session.createQueue("TEST");
        // 创建消息生产者
        MessageProducer producer = session.createProducer(destination);
        // 创建消息 - 文本消息
        ActiveMQTextMessage message = new ActiveMQTextMessage();
        message.setText("Hello,World");
        // 发送文本消息
        producer.send(message);

        // 关闭消息生产者
        producer.close();
        // 关闭会话
        session.close();
        // 关闭连接
        connection.close();
    }
```



### 原生API：消费消息



```java
    private static void receiveMessage() throws Exception {

        // 创建 ActiveMQ 链接，设置 Broker URL
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        // 创造 JMS 链接
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 创建会话 Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建消息目的 - Queue 名称为 "TEST"
        Destination destination = session.createQueue("TEST");
        // 创建消息消费者
        MessageConsumer messageConsumer = session.createConsumer(destination);
        // 获取消息
        Message message = messageConsumer.receive(100);

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("消息消费内容：" + textMessage.getText());
        }

        // 关闭消息消费者
        messageConsumer.close();
        // 关闭会话
        session.close();
        // 关闭连接
        connection.stop();
        connection.close();
    }
```



## Spring Boot JMS + ActiveMQ



###  Maven 依赖

```xml
        <!-- 整合 Sprig Boot Starter ActiveMQ -->
        <!-- 间接依赖：
            spring jms
            jms api
            activemq
            spring boot jms
        -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-activemq</artifactId>
        </dependency>
```



### 配置 ActiveMQ 属性

`application.properties`

```properties
## ActiveMQ 配置
spring.activemq.brokerUrl = tcp://localhost:61616
```



### 配置 JMS 属性

`application.properties`

```properties
## JMS 配置
spring.jms.template.defaultDestination = sf-users-activemq
```



### 改造 user-service-client：实现 ActiveMQ  User 对象消息生产

`UserServiceClientController.java`

```java
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/user/save/message/activemq")
    public boolean saveUserByActiveMQMessage(@RequestBody User user) throws Exception {
        jmsTemplate.convertAndSend(user);
        return true;
    }
```



### 启动 user-service-client



预先启动 "eureka-server" 以及 "config-server"



### 改造 user-service-provider : 实现 ActiveMQ  User 对象消息消费



> 提示：重复 ActiveMQ 配置 以及 JMS 配置



```java
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("/user/poll")
    public Object pollUser() {
        // 获取消息队列中，默认 destination = sf-users-activemq
        return jmsTemplate.receiveAndConvert();
    }
```







## ActiveMQ Spring Cloud Sream Binder 实现



### 创建 spring-cloud-stream-binder-activemq 工程



### 引入 Maven 依赖





### 实现 Binder 接口 - 仅实现消息发送

```java
package com.segumentfault.spring.cloud.stream.binder.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.util.Assert;

/**
 * Active MQ MessageChannel Binder 实现
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public class ActiveMQMessageChannelBinder implements
        Binder<MessageChannel, ConsumerProperties, ProducerProperties> {

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 接受 ActiveMQ 消息
     *
     * @param name
     * @param group
     * @param inboundBindTarget
     * @param consumerProperties
     * @return
     */
    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel inboundBindTarget, ConsumerProperties consumerProperties) {
        // TODO: 实现消息消费
        return () -> {
        };
    }

    /**
     * 负责发送消息到 ActiveMQ
     *
     * @param name
     * @param outputChannel
     * @param producerProperties
     * @return
     */
    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel outputChannel, ProducerProperties producerProperties) {
        Assert.isInstanceOf(SubscribableChannel.class, outputChannel,
                "Binding is supported only for SubscribableChannel instances");

        SubscribableChannel subscribableChannel = (SubscribableChannel) outputChannel;

        subscribableChannel.subscribe(message -> {
            // 接受内部管道消息，来自于 MessageChannel#send(Message)
            // 实际并没有发送消息，而是此消息将要发送到 ActiveMQ Broker
            Object messageBody = message.getPayload();
            jmsTemplate.convertAndSend(name, messageBody);

        });

        return () -> {
            System.out.println("Unbinding");
        };
    }
}

```



### 实现 Spring Cloud Stream Binder 自动装配



```java
package com.segumentfault.spring.cloud.stream.binder.activemq;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ActiveMQ Stream Binder 自动装配
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@Configuration
@ConditionalOnMissingBean(Binder.class)
public class ActiveMQStreamBinderAutoConfiguration {

    @Bean
    public ActiveMQMessageChannelBinder activeMQMessageChannelBinder() {
        return new ActiveMQMessageChannelBinder();
    }
}

```



### 配置 META-INF/spring.binders



```properties
activemq :\
com.segumentfault.spring.cloud.stream.binder.activemq.ActiveMQStreamBinderAutoConfiguration
```



### 整合消息生产者 user-service-client



#### 引入 ActiveMQ Spring Cloud Stream Binder Maven 依赖



```xml
        <!-- 引入 Active MQ Spring Cloud Stream Binder 实现 -->
        <dependency>
            <groupId>com.segumentfault</groupId>
            <artifactId>spring-cloud-stream-binder-activemq</artifactId>
            <version>${project.version}</version>
        </dependency>
```



#### 配置 ActiveMQ Spring Cloud Stream Binder 属性



```properties
## Spring Cloud Stream 默认 Binder
spring.cloud.stream.defaultBinder=rabbit

### 消息管道 activemq-out 配置
spring.cloud.stream.bindings.activemq-out.binder = activemq
spring.cloud.stream.bindings.activemq-out.destination = sf-users-activemq
```



### 实现 Binder 接口 - 实现消息消费

```java
    @Override
    public Binding<MessageChannel> bindConsumer(String name, String group, MessageChannel inputChannel, ConsumerProperties consumerProperties) {

        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();
        try {
            // 创造 JMS 链接
            Connection connection = connectionFactory.createConnection();
            // 启动连接
            connection.start();
            // 创建会话 Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建消息目的
            Destination destination = session.createQueue(name);
            // 创建消息消费者
            MessageConsumer messageConsumer = session.createConsumer(destination);

            messageConsumer.setMessageListener(message -> {
                // message 来自于 ActiveMQ
                if (message instanceof ObjectMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        Object object = objectMessage.getObject();
                        inputChannel.send(new GenericMessage<Object>(object));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return () -> {
        };
    }
```





### 整合消息消费者 - user-service-provider



#### 引入 ActiveMQ Spring Cloud Stream Binder Maven 依赖



```xml
        <!-- 引入 Active MQ Spring Cloud Stream Binder 实现 -->
        <dependency>
            <groupId>com.segumentfault</groupId>
            <artifactId>spring-cloud-stream-binder-activemq</artifactId>
            <version>${project.version}</version>
        </dependency>
```



#### 配置 ActiveMQ Spring Cloud Stream Binder 属性



```properties
## Spring Cloud Stream 默认 Binder
spring.cloud.stream.defaultBinder=rabbit

### 消息管道 activemq-out 配置
spring.cloud.stream.bindings.activemq-in.binder = activemq
spring.cloud.stream.bindings.activemq-in.destination = sf-users-activemq
```



#### 实现 User 消息监听

```java

    @StreamListener("activemq-in")
    public void onUserMessage(User user) throws IOException {
        System.out.println("Subscribe by @StreamListener");
        userService.saveUser(user);
    }

    // 监听 ActiveMQ Stream
    userMessage.activeMQIn().subscribe(message -> {

        if (message instanceof GenericMessage) {
            GenericMessage genericMessage = (GenericMessage) message;
            User user = (User) genericMessage.getPayload();
            userService.saveUser(user);
        }
    });
```

