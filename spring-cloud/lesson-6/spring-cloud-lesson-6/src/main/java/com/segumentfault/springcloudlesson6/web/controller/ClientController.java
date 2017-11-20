package com.segumentfault.springcloudlesson6.web.controller;

import com.segumentfault.springcloudlesson6.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Client {@link RestController}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 */
@RestController
public class ClientController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${serivce-provider.host}")
    private String serviceProviderHost;

    @Value("${serivce-provider.port}")
    private Integer serviceProviderPort;

    @Value("${serivce-provider.name}")
    private String serviceProviderName;


    @GetMapping("")
    public String index() {
        User user = new User();
        user.setId(1L);
        user.setName("小马哥");
//        return restTemplate.postForObject("http://" +
//                        serviceProviderHost + ":" + serviceProviderPort +
//                        "/greeting",
//                user, String.class);

        return restTemplate.postForObject("http://" +
                        serviceProviderName +
                        "/greeting",
                user, String.class);
    }

}
