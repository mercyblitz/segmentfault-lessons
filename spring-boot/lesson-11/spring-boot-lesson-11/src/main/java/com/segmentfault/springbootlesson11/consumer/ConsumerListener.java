package com.segmentfault.springbootlesson11.consumer;

import com.segmentfault.springbootlesson11.domain.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者监听
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.09
 */
@Component
public class ConsumerListener {


    @KafkaListener(topics = "sf-2")
    public void consumer(String message) {

        System.out.println(message);

    }


    @KafkaListener(topics = "sf-users")
    public void consumer(User user) {

        System.err.println(user);

    }

}
