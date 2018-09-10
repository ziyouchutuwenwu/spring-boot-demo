package com.mmc.springbootdemo.controller;

import com.mmc.springbootdemo.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/config")
public class ConfigController {

    private ConfigService _configService;

    @Autowired
    public ConfigController(ConfigService configureService){
        _configService = configureService;
    }

//    @RequestMapping(value = "/name", method = RequestMethod.GET)
    @RequestMapping(value = "/name", method = RequestMethod.POST)
    public String getName(HttpServletRequest request) {

        String preParamFromIntercepter = request.getParameter("pre_param");
        String locale = request.getParameter("lang");

        log.debug(preParamFromIntercepter, locale);

        return _configService.getName();
    }
}