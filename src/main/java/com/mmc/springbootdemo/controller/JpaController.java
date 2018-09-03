package com.mmc.springbootdemo.controller;

import com.mmc.springbootdemo.model.User;
import com.mmc.springbootdemo.service.JpaService;
import com.mmc.springbootdemo.service.user.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/jpa_users")
public class JpaController {

    private JpaService _jpaService;

    @Autowired
    public JpaController(JpaService jpaService) {
        _jpaService = jpaService;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getAll() {

        List users = _jpaService.getAll();

        log.debug("这是getAll查询");

        return users;
    }
}
