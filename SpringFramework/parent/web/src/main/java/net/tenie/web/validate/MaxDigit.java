package net.tenie.web.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.tenie.web.validate.impl.MaxDigitValidator;

/**
* 最大整数位数
*/
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxDigitValidator.class)
@Documented
public @interface MaxDigit {
    
    int value() default -1;
    
    String message() default "最大整数位数失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
