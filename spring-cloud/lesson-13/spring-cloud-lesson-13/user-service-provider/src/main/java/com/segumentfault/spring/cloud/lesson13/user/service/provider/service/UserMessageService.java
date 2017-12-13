package com.segumentfault.spring.cloud.lesson13.user.service.provider.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.segumentfault.spring.cloud.lesson13.api.UserService;
import com.segumentfault.spring.cloud.lesson13.domain.User;
import com.segumentfault.spring.cloud.lesson13.user.stream.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static com.segumentfault.spring.cloud.lesson13.user.stream.UserMessage.INPUT;

/**
 * 用户 消息服务
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
@Service
public class UserMessageService {

    @Autowired
    private UserMessage userMessage;

    @Autowired
    @Qualifier("inMemoryUserService")
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;


    @ServiceActivator(inputChannel = INPUT)
    public void listen(byte[] data) throws IOException {
        System.out.println("Subscribe by @ServiceActivator");
        saveUser(data);
    }

    @StreamListener(INPUT)
    public void onMessage(byte[] data) throws IOException {
        System.out.println("Subscribe by @StreamListener");
        saveUser(data);
    }

    @StreamListener("activemq-in")
    public void onUserMessage(User user) throws IOException {
        System.out.println("Subscribe by @StreamListener");
        userService.saveUser(user);
    }


    private void saveUser(String data) throws IOException {
        User user = objectMapper.readValue(data, User.class);
        userService.saveUser(user);
    }

    private void saveUser(byte[] data) {
        // message body 是字节流 byte[]
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            User user = (User) objectInputStream.readObject(); // 反序列化成 User 对象
            userService.saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    public void init() {

        SubscribableChannel subscribableChannel = userMessage.input();
        subscribableChannel.subscribe(message -> {
            System.out.println("Subscribe by SubscribableChannel");
            String contentType = message.getHeaders().get("contentType", String.class);
            if ("text/plain".equals(contentType)) {
                try {
                    saveUser((String) message.getPayload());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // message body 是字节流 byte[]
                byte[] body = (byte[]) message.getPayload();
                saveUser(body);
            }
        });

        // 监听 ActiveMQ Stream
        userMessage.activeMQIn().subscribe(message -> {

            if (message instanceof GenericMessage) {
                GenericMessage genericMessage = (GenericMessage) message;
                User user = (User) genericMessage.getPayload();
                userService.saveUser(user);
            }
        });
    }

}
