package com.segmentfault.springbootlesson11.controller;

import com.segmentfault.springbootlesson11.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Kafka RestController
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.09
 */
@RestController
public class KafkaController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @GetMapping(value = "/message/send")
    public String sendMessage(@RequestParam String message) {

        kafkaTemplate.send("sf-2", 0, message);

        return message;
    }

    @PostMapping(value = "/user/save")
    public User saveUser(@RequestBody User user) {

        kafkaTemplate.send("sf-users", 0, user);

        return user;

    }


}
