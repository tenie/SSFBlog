package net.tenie.web.validate.impl;

import java.text.DecimalFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.tenie.web.validate.MaxPrecision;

public class MaxPrecisionValidator implements ConstraintValidator<MaxPrecision, Number>{
    int maxPrecison;
    
    @Override
    public void initialize(MaxPrecision constraintAnnotation) {
        this.maxPrecison = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext context) {
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
        if(precisonLength>this.maxPrecison){
            return false;
        }
        return true;
    }

}
