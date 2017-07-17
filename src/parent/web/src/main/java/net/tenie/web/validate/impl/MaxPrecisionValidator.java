package net.tenie.web.validate.impl;

import java.text.DecimalFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.validate.MaxPrecision;
 

/**
* 最大小数位数校验 
*/

public class MaxPrecisionValidator implements ConstraintValidator<MaxPrecision, Number>{
    int maxPrecison;
    
    @Override
    public void initialize(MaxPrecision constraintAnnotation) {
        this.maxPrecison = constraintAnnotation.value();
    }

    /**
     * 执行小数位数校验
    
     */
    @Override
    public boolean isValid(Number number, ConstraintValidatorContext context) {
        //如果为空，则返回true
        if (number==null) {
            return true;
        }
        if(this.maxPrecison ==-1 ){
            return true;
        }
        DecimalFormat df = new DecimalFormat("0.########");
        String formatValue = df.format(number);
        String[] digitArray = formatValue.split("\\.");
        if(digitArray.length<2){
            return true;
        }
        int precisonLength = digitArray[1].length();
        //如果小数位数超过最大小数
        if(precisonLength>this.maxPrecison){
            return false;
        }
        return true;
    }

}
