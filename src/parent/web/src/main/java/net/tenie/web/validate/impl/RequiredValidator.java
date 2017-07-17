package net.tenie.web.validate.impl;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.validate.Required;
 
/**
 * 对必填字段进行校验 
 */

public class RequiredValidator implements ConstraintValidator<Required, Object> {

    @Override
    public void initialize(Required constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        // 判断是字符串的空格的长度
        if (value instanceof String) {
            return ((String) value).trim().length() > 0;
        }
        // 如果是集合类型
        if (value instanceof Collection) {
            return !((Collection) value).isEmpty();
        }
        // 如果是数组
        if (value.getClass().isArray()) {
            return ((Object[]) value).length > 0;
        }
        return true;

    }
}
