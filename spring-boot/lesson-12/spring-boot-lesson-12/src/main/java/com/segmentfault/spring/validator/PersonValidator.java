package com.segmentfault.spring.validator;

import com.segmentfault.domain.Person;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * {@link Person} {@link Validator}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see Person
 * @see Validator
 * @since 2017.08.12
 */
public class PersonValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Person person = Person.class.cast(target);

        String name = person.getName(); //名字需要校验

        if (!StringUtils.hasLength(name)) {

            errors.reject("person.name.not.null", "人的名字不能为空");

        }

    }
}
