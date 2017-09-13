package com.segmentfault.springbootlesson19.domain;

import com.segmentfault.springbootlesson19.domain.Person;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link Person} Test
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.09.13
 */
public class PersonTest {

    @Test
    public void testPerson() {
        Person person = new Person();
        person.setAge(10);
        Assert.assertEquals(Integer.valueOf(10), person.getAge());
        Assert.assertNotNull(person);
    }
}
