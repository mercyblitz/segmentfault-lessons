package com.segumentfault.spring.cloud.lesson14.user.service.client.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.segumentfault.spring.cloud.lesson14.api.UserService;
import com.segumentfault.spring.cloud.lesson14.domain.User;
import com.segumentfault.spring.cloud.lesson14.user.service.client.stream.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * {@link UserService} 客户端 {@link RestController}
 * <p>
 * 注意：官方建议 客户端和服务端不要同时实现 Feign 接口
 * 这里的代码只是一个说明，实际情况最好使用组合的方式，而不是继承
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RestController
public class UserServiceClientController implements UserService {

    @Autowired
    private UserService userService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private UserMessage userMessage;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    public UserServiceClientController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/user/save/message")
    public boolean saveUserByMessage(@RequestBody User user) {
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("sf-users", 0, user);
        return future.isDone();
    }

    @PostMapping("/user/save/message/rabbit")
    public boolean saveUserByRabbitMessage(@RequestBody User user) throws JsonProcessingException {
        MessageChannel messageChannel = userMessage.output();
        // User 序列化成 JSON
        String payload = objectMapper.writeValueAsString(user);
        GenericMessage<String> message = new GenericMessage<String>(payload);
        // 发送消息
        return messageChannel.send(message);
    }

    @PostMapping("/user/save/message/activemq")
    public boolean saveUserByActiveMQMessage(@RequestBody User user) throws Exception {
        jmsTemplate.convertAndSend(user);
        return true;
    }

    @PostMapping("/user/save/message/activemq/stream")
    public boolean saveUserByActiveMQStream(@RequestBody User user) throws Exception {
        MessageChannel messageChannel = userMessage.activeMQOut();
        GenericMessage message = new GenericMessage(user);
        return messageChannel.send(message);
    }


    // 通过方法继承，URL 映射 ："/user/save"
    @Override
    public boolean saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // 通过方法继承，URL 映射 ："/user/find/all"
    @Override
    public List<User> findAll() {
        return userService.findAll();
    }

}
