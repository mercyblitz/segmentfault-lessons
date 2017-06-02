package com.segmentfault.springbootlesson1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@SpringBootApplication
@Service
public class SpringBootLesson1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootLesson1Application.class, args);
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/rest")
    @ResponseBody
    public Map<String, Object> rest() {

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("1", "A");
        data.put("2", 2);

        return data;

    }



}
