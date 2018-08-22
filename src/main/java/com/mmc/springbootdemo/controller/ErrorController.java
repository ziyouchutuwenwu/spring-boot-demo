package com.mmc.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
    @RequestMapping(value = "/500", method = RequestMethod.GET)
    public String div0() {
        int i=5/0;
        return "";
    }
}