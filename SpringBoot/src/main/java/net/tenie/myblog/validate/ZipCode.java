package net.tenie.myblog.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.tenie.myblog.validate.impl.ZipCodeValidator;

/**
* 校验邮编
*/
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ZipCodeValidator.class)
@Documented
public @interface ZipCode {
    
    String message() default "校验邮编失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
