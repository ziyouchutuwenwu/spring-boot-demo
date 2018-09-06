package com.mmc.springbootdemo.validator.fluent;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

public class AgeValidator extends ValidatorHandler<Integer> implements Validator<Integer> {

    @Override
    public boolean validate(ValidatorContext context, Integer age) {
        if ( age > 2 ) {
            context.addErrorMsg(String.format("age invalid, value=%s", age.toString()));
            return false;
        }
        return true;
    }
}