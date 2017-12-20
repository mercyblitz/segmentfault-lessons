package com.segumentfault.spring.cloud.stream.binder.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.Binder;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.ConsumerProperties;
import org.springframework.cloud.stream.binder.ProducerProperties;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.Assert;

import javax.jms.*;

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
     * @param inputChannel
     * @param consumerProperties
     * @return
     */
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

    /**
     * 负责发送消息到 ActiveMQ
     *
     * @param name
     * @param outputChannel
     * @param producerProperties
     * @return
     */
    @Override
    public Binding<MessageChannel> bindProducer(String name, MessageChannel outputChannel, ProducerProperties
            producerProperties) {
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
