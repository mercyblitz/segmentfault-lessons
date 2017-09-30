package com.segmentfault.springbootlesson20.autoconfigure;

import com.segmentfault.springbootlesson20.domain.Person;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * {@link Person} 自动装配
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.16
 */
//@Configuration
@ConditionalOnWebApplication
@ConditionalOnProperty(prefix = "person", name = "enabled", havingValue = "true")
@AutoConfigureAfter(EmbeddedServletContainerAutoConfiguration.class)
public class PersonAutoConfiguration {


    @ConfigurationProperties(prefix = "person")
    @Bean
    public Person person() {
        return new Person();
    }

//    @Bean
//    public PersonRestController personRestController(Person person) {
//        return new PersonRestController(person);
//    }

}
