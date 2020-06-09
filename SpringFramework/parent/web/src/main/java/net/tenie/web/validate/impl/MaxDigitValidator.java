package net.tenie.web.validate.impl;

import java.text.DecimalFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.validate.MaxDigit;
 
public class MaxDigitValidator implements ConstraintValidator<MaxDigit, Number>{
    int maxDight;
    
    @Override
    public void initialize(MaxDigit constraintAnnotation) {
        this.maxDight = constraintAnnotation.value();
    }
 
    @Override
    public boolean isValid(Number number, ConstraintValidatorContext context) {
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
