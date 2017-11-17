package com.segumentfault.springcloudlesson5consulclient.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 检查 Controller
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 */
@RestController
public class CheckController {

    @GetMapping("/check")
    public String check() {
        return "OK";
    }

}
