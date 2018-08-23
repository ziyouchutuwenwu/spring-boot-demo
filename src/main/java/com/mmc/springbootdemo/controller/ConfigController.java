package com.mmc.springbootdemo.controller;

import com.mmc.springbootdemo.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    private ConfigurationService _configService;

    @Autowired
    public ConfigController(ConfigurationService configureService){
        _configService = configureService;
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public String getName() {
        return _configService.getName();
    }
}