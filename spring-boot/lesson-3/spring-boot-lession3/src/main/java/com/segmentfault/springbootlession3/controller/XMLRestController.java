package com.segmentfault.springbootlession3.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XMLRestController {

    @GetMapping(path = "/xml/user",
            produces = MediaType.APPLICATION_XML_VALUE)
    public User user() {

        User user = new User();

        user.setName("XML");
        user.setAge(30);

        return user;
    }


}
