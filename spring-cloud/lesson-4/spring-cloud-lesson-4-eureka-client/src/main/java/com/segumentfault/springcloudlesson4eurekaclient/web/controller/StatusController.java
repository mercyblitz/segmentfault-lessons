package com.segumentfault.springcloudlesson4eurekaclient.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 状态 控制器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since 1.0.0
 */
@RestController
public class StatusController {

    @GetMapping("/status")
    public String status() {
        return "OK";
    }

}
