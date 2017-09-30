package com.segmentfault.springbootlesson19.configuration;

import com.segmentfault.springbootlesson19.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * {@link Person} Bean 配置
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.13
 */
@Configuration
public class PersonConfiguration {


    @Bean("primaryPerson")
    @Primary
    public Person person() {

        Person person = new Person();

        person.setId(1L);
        person.setName("小马哥");
        person.setAge(32);

        return person;

    }
}
