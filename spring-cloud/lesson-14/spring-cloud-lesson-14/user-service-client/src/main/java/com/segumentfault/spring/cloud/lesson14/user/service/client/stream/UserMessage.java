package com.segumentfault.spring.cloud.lesson14.user.service.client.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 用户消息(输出)
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 0.0.1
 */
public interface UserMessage {

    @Output("user-message-out")
    MessageChannel output();

    @Output("activemq-out")
    MessageChannel activeMQOut();

}
