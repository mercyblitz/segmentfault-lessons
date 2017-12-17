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
