package com.mmc.springbootdemo.controller;

import com.mmc.springbootdemo.model.User;
import com.mmc.springbootdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class JsonController {

    private UserService _userService;

    @Autowired
    public void setUserService(UserService userService) {
        this._userService = userService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getAllUsers() {

//        PageHelper.startPage(2, 4);
        List users = _userService.getAllUsers();

        log.info("这是测试信息");
        log.debug("这是调试信息");

        return users;
    }

    @RequestMapping(value = "/add_user", method = RequestMethod.GET)
    public User insertUser(String name, Integer age) {
        log.debug("插入用户", name, age);
        _userService.addUser(name, age);
        return _userService.findByName(name);
    }
}