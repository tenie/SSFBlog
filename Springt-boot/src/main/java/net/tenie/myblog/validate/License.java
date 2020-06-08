package net.tenie.myblog.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.tenie.myblog.validate.impl.LicenseValidator;
/**
* 车牌号校验 
*/
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LicenseValidator.class)
@Documented
public @interface License {
    
    String message() default "车牌号校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
