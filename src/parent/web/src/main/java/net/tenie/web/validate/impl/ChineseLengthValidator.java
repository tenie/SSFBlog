package net.tenie.web.validate.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.tenie.web.tools.StringUtils;
import net.tenie.web.validate.ChineseLength; 

/**
* 检查中文长度 
*/

public class ChineseLengthValidator implements ConstraintValidator<ChineseLength, String>{

    // 定义日志接口
    private static final Logger logger = LoggerFactory.getLogger(ChineseLengthValidator.class);
    private int max;
    private int min;
    
    @Override
    public void initialize(ChineseLength constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
        logger.debug("length:"+constraintAnnotation.max()+";min"+constraintAnnotation.min());
    }

    /**
     * 执行校验 
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //如果为空，则返回true
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
