package net.tenie.web.validate.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.tools.StringUtils;
import net.tenie.web.validate.License;
 

/**
 * 车牌号校验 
 */

public class LicenseValidator implements ConstraintValidator<License, String> {

    @Override
    public void initialize(License constraintAnnotation) {
    }

    /**
     * 执行车牌号校验 
     */
    @Override
    public boolean isValid(String license, ConstraintValidatorContext context) {
        //如果为空，则返回true
        if (StringUtils.isBlank(license)) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$");
        Matcher matcher = pattern.matcher(license);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

}
