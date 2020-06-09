package net.tenie.web.validate.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.tenie.web.tools.StringUtils;
import net.tenie.web.validate.ChineseLength; 

public class ChineseLengthValidator implements ConstraintValidator<ChineseLength, String>{
 
    private static final Logger logger = LoggerFactory.getLogger(ChineseLengthValidator.class);
    private int max;
    private int min;
    
    @Override
    public void initialize(ChineseLength constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
        logger.debug("length:"+constraintAnnotation.max()+";min"+constraintAnnotation.min());
    }

 
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        int byteLength = value.getBytes().length;
        if(max!=-1){
            if(byteLength>max){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{lengthMaxValidate}").addConstraintViolation();
                return false;
            }
        }
        if(min !=-1){
            if(byteLength<min){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{lengthMinValidate}").addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}
