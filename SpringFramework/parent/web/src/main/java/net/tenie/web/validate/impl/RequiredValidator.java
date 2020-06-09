package net.tenie.web.validate.impl;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.validate.Required;
 
 
public class RequiredValidator implements ConstraintValidator<Required, Object> {

    @Override
    public void initialize(Required constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        } 
        if (value instanceof String) {
            return ((String) value).trim().length() > 0;
        } 
        if (value instanceof Collection) {
            return !((Collection) value).isEmpty();
        } 
        if (value.getClass().isArray()) {
            return ((Object[]) value).length > 0;
        }
        return true;

    }
}
