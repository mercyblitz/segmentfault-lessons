package com.segmentfault.springbootlesson18.configuration;

import com.segmentfault.springbootlesson18.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.09
 */
@Configuration
public class PersonConfiguration {

    @Bean
    @Profile("prod")
    public Person zhangxueyou() {
        Person person = new Person();
        return person;
    }

    @Bean
    @Profile("test")
    public Person zhangkai() {
        Person person = new Person();
        return person;
    }

}
