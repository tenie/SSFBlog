package net.tenie.myblog.validate.impl;

import java.text.DecimalFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.myblog.validate.MaxDigit;
 

/**
* 最大整数位校验 
*/

public class MaxDigitValidator implements ConstraintValidator<MaxDigit, Number>{
    //最大整数位数
    int maxDight;
    
    @Override
    public void initialize(MaxDigit constraintAnnotation) {
        this.maxDight = constraintAnnotation.value();
    }
 
    @Override
    public boolean isValid(Number number, ConstraintValidatorContext context) {
        //如果为空，则返回true
        if (number==null) {
            return true;
        }
        if(this.maxDight ==-1 ){
            return true;
        }
        DecimalFormat df = new DecimalFormat("0.########");
        String formatValue = df.format(number);
        String[] digitArray = formatValue.split("\\.");
        int digitLength = digitArray[0].length();
        if(digitLength>this.maxDight){
            return false;
        }
        return true;
    }

}
