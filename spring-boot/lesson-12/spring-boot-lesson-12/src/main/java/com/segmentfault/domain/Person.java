package com.segmentfault.domain;

import com.segmentfault.bean.validation.constraints.PersonNamePrefix;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 人 领域对象
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.12
 */
public class Person {

    @NotNull
    @PersonNamePrefix(prefix = "segmentfault-")
    private String name;

    @Min(value = 0)
    @Max(value = 200
            , message = "{person.age.max.message}"
    )
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
