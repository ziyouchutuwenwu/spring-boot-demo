package com.mmc.springbootdemo.controller;

import com.mmc.springbootdemo.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private ConfigService _configService;

    @Autowired
    public ConfigController(ConfigService configureService){
        _configService = configureService;
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName() {
        return _configService.getName();
    }
}