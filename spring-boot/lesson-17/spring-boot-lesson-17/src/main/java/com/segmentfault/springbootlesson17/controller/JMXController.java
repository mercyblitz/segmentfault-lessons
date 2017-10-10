package com.segmentfault.springbootlesson17.controller;

import com.segmentfault.springbootlesson17.jmx.SimpleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.30
 */
@RestController
public class JMXController {

    @Autowired
    private SimpleBean simpleBean;


    @GetMapping("/jmx/simple-bean")
    public SimpleBean simpleBean(@RequestParam(required = false) Long id,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) Integer value) {

        if (id != null) {
            simpleBean.setId(id);
        }

        if (name != null) {
            simpleBean.setName(name);
        }

        if (value != null) {
            simpleBean.setValue(value);
        }

        return simpleBean;

    }

}
