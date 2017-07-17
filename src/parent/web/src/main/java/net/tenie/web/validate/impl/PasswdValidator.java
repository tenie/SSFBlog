package net.tenie.web.validate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.tools.StringUtils;
import net.tenie.web.validate.Passwd;
 

/**
* 密码格式校验 
*/

public class PasswdValidator implements ConstraintValidator<Passwd, String>{

    @Override
    public void initialize(Passwd constraintAnnotation) {
    }

    /**
     * 执行密码校验 
     */
    @Override
    public boolean isValid(String passwd, ConstraintValidatorContext context) {
        //如果为空，则返回true
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
