package net.tenie.web.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.tenie.web.validate.impl.IDNumberValidator;


/**
* 身份证号校验规则
*/
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IDNumberValidator.class)
@Documented
public @interface IDNumber {
    
    String message() default "身份证号校验规则失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
