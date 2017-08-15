package com.segmentfault.bean.validation;

import com.segmentfault.bean.validation.constraints.PersonNamePrefix;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@link PersonNamePrefix} {@link ConstraintValidator}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see PersonNamePrefix
 * @see ConstraintValidator
 * @since 2017.08.12
 */
public class PersonNamePrefixConstraintValidator implements ConstraintValidator<PersonNamePrefix, String> {

    private String prefix;

    @Override
    public void initialize(PersonNamePrefix constraintAnnotation) {
        this.prefix = constraintAnnotation.prefix();
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {

        if (!name.startsWith(prefix)) {

            context.disableDefaultConstraintViolation();

            ConstraintValidatorContext.ConstraintViolationBuilder builder =
                    context.buildConstraintViolationWithTemplate("人的名称必须以\"" + prefix + "\"起始！");
            builder.addConstraintViolation();

            return false;
        }

        return true;
    }
}
