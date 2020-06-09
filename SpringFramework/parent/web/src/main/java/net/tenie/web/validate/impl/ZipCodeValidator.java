package net.tenie.web.validate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.tools.StringUtils;
import net.tenie.web.validate.ZipCode;
 
 
public class ZipCodeValidator implements ConstraintValidator<ZipCode, String>{

    @Override
    public void initialize(ZipCode constraintAnnotation) {
    }

    
    @Override
    public boolean isValid(String zipCode, ConstraintValidatorContext context) {
 
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
