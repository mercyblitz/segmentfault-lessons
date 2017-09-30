package com.segmentfault.springbootlesson15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 安全 Controller
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.23
 */
@Controller
public class SecurityController {

    @GetMapping("")
    public String index() {


        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/xss")
    public String xss(Model model) {

        // JS Code 需要被 Escape
        model.addAttribute("jsCode", "<script>alert('XSS attack')</script>");
        // HTML Code 不需要被 Escape（Unescape）
        model.addAttribute("htmlCode", "<span>Hello,World</span>");

        return "xss";
    }


}
