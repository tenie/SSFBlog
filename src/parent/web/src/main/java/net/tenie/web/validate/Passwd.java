package net.tenie.web.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import net.tenie.web.validate.impl.PasswdValidator;
 

/**
* 密码校验 
*/
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswdValidator.class)
@Documented
public @interface Passwd {
    String message() default "密码校验失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
