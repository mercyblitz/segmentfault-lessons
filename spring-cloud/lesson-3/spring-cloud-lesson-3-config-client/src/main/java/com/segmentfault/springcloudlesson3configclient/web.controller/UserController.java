package com.segmentfault.springcloudlesson3configclient.web.controller;

import com.segmentfault.springcloudlesson3configclient.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 Controller
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RestController
@EnableConfigurationProperties(User.class)
public class UserController {

    //通过构造器注入
    private final User user;

    @Autowired
    public UserController(User user) {
        this.user = user;
    }

    @GetMapping("/user")
    public User user() {
        return user;
    }
}
