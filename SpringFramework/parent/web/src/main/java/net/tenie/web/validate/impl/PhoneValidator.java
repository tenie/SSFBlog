package net.tenie.web.validate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.tools.StringUtils;
import net.tenie.web.validate.Phone;
 
 
public class PhoneValidator implements ConstraintValidator<Phone, String>{

    @Override
    public void initialize(Phone constraintAnnotation) {
    }
 
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
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
