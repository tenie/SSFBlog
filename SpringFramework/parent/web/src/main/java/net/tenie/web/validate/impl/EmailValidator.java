package net.tenie.web.validate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import net.tenie.web.tools.StringUtils;
import net.tenie.web.validate.Email;
 

/**
* 邮箱校验 
*/

public class EmailValidator implements ConstraintValidator<Email, String>{

    
    @Override
    public void initialize(Email constraintAnnotation) {
    }

 
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(email)) {
            return true;
        } 
        if(email.length()>60){
            return false;
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

}
