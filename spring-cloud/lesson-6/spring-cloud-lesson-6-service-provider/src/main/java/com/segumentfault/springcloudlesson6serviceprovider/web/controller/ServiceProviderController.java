package com.segumentfault.springcloudlesson6serviceprovider.web.controller;

import com.segumentfault.springcloudlesson6serviceprovider.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务提供方 {@link RestController}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 */
@RestController
public class ServiceProviderController {

    @Value("${server.port}")
    private Integer port;

    @PostMapping("/greeting")
    public String greeting(@RequestBody User user) {
        return "Greeting , " + user + " on port : " + port;
    }

}
