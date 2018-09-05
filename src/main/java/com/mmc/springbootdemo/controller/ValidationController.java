package com.mmc.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.mmc.springbootdemo.exception.Result;
import com.mmc.springbootdemo.model.ValidateModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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
    public String validateDemo(@Valid ValidateModel validateModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "error";
        }
        return "ok";
    }
}