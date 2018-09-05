package com.mmc.springbootdemo.model;

import com.mmc.springbootdemo.validator.PhoneValidation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
public class ValidateModel {

    @Size(min=2, max=30)
    private String name;

    // 自定义错误信息
    @NotEmpty(message = "自定义错误信息，Email不能为空")
    @Email
    private String email;

    @NotNull
    @Min(18) @Max(100)
    private Integer age;

    @NotNull
    private Gender gender;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull @Past
    private Date birthday;

    // 自定义规则注解
    @PhoneValidation
    private String phone;

    private enum Gender {
        MALE, FEMALE
    }
}