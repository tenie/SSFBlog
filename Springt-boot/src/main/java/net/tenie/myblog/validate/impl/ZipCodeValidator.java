package net.tenie.myblog.validate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.myblog.tools.StringUtils;
import net.tenie.myblog.validate.ZipCode;
 

/**
* 执行邮编校验 
*/

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String>{

    @Override
    public void initialize(ZipCode constraintAnnotation) {
    }

    
    @Override
    public boolean isValid(String zipCode, ConstraintValidatorContext context) {
        //如果为空，则返回true
        if (StringUtils.isBlank(zipCode)) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[1-9]\\d{5}(?!\\d)$");
        Matcher matcher = pattern.matcher(zipCode);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

}
