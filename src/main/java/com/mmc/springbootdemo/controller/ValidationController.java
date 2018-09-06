package com.mmc.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.mmc.springbootdemo.exception.Result;
import com.mmc.springbootdemo.model.ValidateModel;
import com.mmc.springbootdemo.validator.fluent.AgeValidator;
import com.mmc.springbootdemo.validator.fluent.NameValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Locale;

import static com.baidu.unbiz.fluentvalidator.ResultCollectors.toSimple;

@Slf4j
@RestController
@RequestMapping(value = "/validation", method = RequestMethod.GET)
public class ValidationController {
    // http://127.0.0.1:7890/validation/save?name=name&email=126@126.com&age=18&gender=MALE&birthday=2/22/1985&phone=13589567201
    // http://127.0.0.1:7890/validation/save?name=n&email=126@126.com&age=18&gender=MALE&birthday=2/22/1985
    // http://127.0.0.1:7890/validation/save?name=name&email=&age=18&gender=MALE&birthday=2/22/1985&phone=13589567201
    // http://127.0.0.1:7890/validation/save?name=name&email=126@126.com&age=18&gender=MALE&birthday=2/22/1985&phone=13589567

    // 这里使用全局异常处理
    @RequestMapping(value = "save", method = RequestMethod.GET)
    @ResponseBody
    public Result validateSave(@Valid ValidateModel validateModel) {

        Locale locale = LocaleContextHolder.getLocale();

        log.info("lang" + locale.getCountry());

        log.info("Good" + validateModel.getBirthday());
        Result okResult = new Result();
        okResult.setCode(200);
        okResult.setMessage(JSONObject.toJSON(validateModel).toString());

        return okResult;
    }

    // http://127.0.0.1:7890/validation/get?name=name&email=126@126.com&age=18&gender=MALE&birthday=2/22/1985&phone=13589567

    // 这里全局异常会被忽略
    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public String validateGet(@Valid ValidateModel validateModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "error";
        }
        return "ok";
    }

    // http://127.0.0.1:7890/validation/fluent?name=rico&age=3589567

    // 采用fluent-validator的验证器
    @RequestMapping(value = "fluent", method = RequestMethod.GET)
    @ResponseBody
    public String validateFluent(String name, Integer age) {

        com.baidu.unbiz.fluentvalidator.Result validationResult = FluentValidator.checkAll()
                .on(name, new NameValidator())
                .on(age, new AgeValidator())
                .doValidate()
                .result(toSimple());

        if ( !validationResult.isSuccess()) {
            log.debug(validationResult.getErrors().toString());

            return String.format("error, reason is %s", validationResult.getErrors().toString());
        }

        return "ok";
    }
}