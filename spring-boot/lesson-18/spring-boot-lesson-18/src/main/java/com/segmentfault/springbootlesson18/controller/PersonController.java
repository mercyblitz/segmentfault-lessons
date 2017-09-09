package com.segmentfault.springbootlesson18.controller;

import com.segmentfault.springbootlesson18.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * {@link Person} {@link RestController}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.09
 */
@RestController
public class PersonController implements EnvironmentAware {

    @Autowired
    @Qualifier("person")
    private Person person;

    @Value("${person.id}")
    private Long id;

    @Value("${person.name:小马哥}")
    private String name;

    private Integer age;


    @GetMapping("/person/xiaomage")
    public Person xiaomage() {
        return person;
    }

    @GetMapping("/person/xiaomage/data")
    public Map<String, Object> xiaomageData() {

        Map<String, Object> data = new LinkedHashMap<>();

        data.put("id", id);
        data.put("name", name);
        data.put("age", age);

        return data;
    }

    @Override
    public void setEnvironment(Environment environment) {

        this.age = environment.getProperty("person.age", Integer.class);

    }

}
