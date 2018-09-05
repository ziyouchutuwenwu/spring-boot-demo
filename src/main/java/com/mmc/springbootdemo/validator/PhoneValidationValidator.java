package com.mmc.springbootdemo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidationValidator implements ConstraintValidator<PhoneValidation, String> {

    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$"
    );

    @Override
    public void initialize(PhoneValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( value == null || value.length() == 0 ) {

            //禁用默认的message的值, 重新添加错误提示语句
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("手机号不能为空").addConstraintViolation();

            return false;
        }

        Matcher m = PHONE_PATTERN.matcher(value);
        return m.matches();
    }
}