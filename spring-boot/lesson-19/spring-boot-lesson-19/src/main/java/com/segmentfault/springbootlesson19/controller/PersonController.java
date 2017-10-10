package com.segmentfault.springbootlesson19.controller;

import com.segmentfault.springbootlesson19.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.13
 */
@RestController
public class PersonController {

    @Autowired
    private Person person;

    @GetMapping
    public Person person() {
        return person;
    }

}
