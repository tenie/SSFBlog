package net.tenie.web.validate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.tools.StringUtils;
import net.tenie.web.validate.Passwd;
 

public class PasswdValidator implements ConstraintValidator<Passwd, String>{

    @Override
    public void initialize(Passwd constraintAnnotation) {
    }
    @Override
    public boolean isValid(String passwd, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(passwd)) {
            return true;
        }
        Pattern pattern = Pattern.compile("^(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[\\!\\$\\%\\^\\&\\*\\(\\)\\-\\+\\=\\-_#@]+$).{8,}$");
        Matcher matcher = pattern.matcher(passwd);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

}
