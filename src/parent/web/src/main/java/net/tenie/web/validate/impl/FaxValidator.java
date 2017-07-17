package net.tenie.web.validate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.tools.StringUtils;
import net.tenie.web.validate.Fax;
 

/**
* 传真格式校验 
*/

public class FaxValidator implements ConstraintValidator<Fax, String>{

    @Override
    public void initialize(Fax constraintAnnotation) {
    }

    /**
     * 执行校验 
     */
    @Override
    public boolean isValid(String fax, ConstraintValidatorContext context) {
        //如果为空，则返回true
        if (StringUtils.isBlank(fax)) {
            return true;
        }
        Pattern pattern = Pattern.compile("^(\\d{3,4}-)?\\d{7,8}$");
        Matcher matcher = pattern.matcher(fax);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

}
