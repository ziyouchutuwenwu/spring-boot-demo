package com.mmc.springbootdemo.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BindExceptionHanlder {

    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException exception) {

        // exception.getFieldError():随机返回一个对象属性的异常信息。如果要一次性返回所有对象属性异常信息，则调用 exception.getAllErrors()
        FieldError fieldError = exception.getFieldError();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.
                append(fieldError.getField()).
                append("=[").
                append(fieldError.getRejectedValue()).
                append("]").
                append(fieldError.getDefaultMessage());

        Result errorResult = new Result();
        errorResult.setCode(400);
        errorResult.setMessage(stringBuilder.toString());

        return errorResult;
    }
}