package com.mmc.springbootdemo.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// 指定真正实现校验规则的类
@Constraint(validatedBy = PhoneValidationValidator.class)
@Documented
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface PhoneValidation {
    String message() default "不是正确的手机号码";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PhoneValidation[] value();
    }
}