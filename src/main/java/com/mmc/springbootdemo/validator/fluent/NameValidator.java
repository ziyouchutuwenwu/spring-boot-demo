package com.mmc.springbootdemo.validator.fluent;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

public class NameValidator extends ValidatorHandler<String> implements Validator<String> {

    @Override
    public boolean validate(ValidatorContext context, String name) {
        if ( name.isEmpty() || name.length() > 20 ) {
            context.addErrorMsg(String.format("name invalid, value=%s", name));
            return false;
        }
        return true;
    }
}