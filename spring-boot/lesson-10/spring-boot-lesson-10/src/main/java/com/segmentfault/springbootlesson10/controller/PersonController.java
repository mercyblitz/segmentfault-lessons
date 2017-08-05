package com.segmentfault.springbootlesson10.controller;

import com.segmentfault.springbootlesson10.entity.Person;
import com.segmentfault.springbootlesson10.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.04
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @PostMapping("/save")
    public Person save(@RequestBody Person person) {

        repository.savePerson(person);

        return person;

    }


    @GetMapping("/get/{id}")
    public Person get(@PathVariable String id) {

        return repository.findPerson(id);

    }


}
