package com.segmentfault.controller;

import com.segmentfault.domain.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * {@link Person} Controller
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.12
 */
@RestController
public class PersonController {


    @PostMapping("/person/save")
    public Person save(@Valid @RequestBody Person person) {

        return person;

    }

}
