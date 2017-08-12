package com.segmentfault.springbootlesson12;

import org.apache.commons.validator.routines.IntegerValidator;

import java.util.Locale;

/**
 * Apache commons-validator Main
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.12
 */
public class ApacheCommonsValidatorMain {


    public static void main(String[] args) {

        IntegerValidator validator = IntegerValidator.getInstance();

        Integer value = validator.validate("10");

        System.out.println(value);

        value = validator.validate("A");

        System.out.println(value);

        System.out.println(validator.format(100000, Locale.ENGLISH));

    }

}
