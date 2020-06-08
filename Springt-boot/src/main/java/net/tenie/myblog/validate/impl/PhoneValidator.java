package net.tenie.myblog.validate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.myblog.tools.StringUtils;
import net.tenie.myblog.validate.Phone;
 

/**
* 执行手机号校验 
*/

public class PhoneValidator implements ConstraintValidator<Phone, String>{

    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    /**
     * 执行校验 
     */
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        //如果为空，则返回true
        if (StringUtils.isBlank(phone)) {
            return true;
        }
        Pattern pattern = Pattern.compile("^1[34578]\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

}
