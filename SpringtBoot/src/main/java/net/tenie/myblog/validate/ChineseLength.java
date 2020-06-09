package net.tenie.myblog.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.tenie.myblog.validate.impl.ChineseLengthValidator;
 

/**
* 含有中文的长度校验
*/
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChineseLengthValidator.class)
@Documented
public @interface ChineseLength {

    String message() default "中文的长度校验失败";
    /** 
    * 定义最少长度 
     */
    int min() default -1;
    /** 
    * 最定最大长度 
     */
    int max() default -1;
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
