package com.mmc.springbootdemo.controller;

import com.mmc.springbootdemo.model.User;
import com.mmc.springbootdemo.service.JooqUserService;
import com.mmc.springbootdemo.service.MyBatisUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    private MyBatisUserService _mybatisUserService;
    private JooqUserService _jooqUserService;

    @Autowired
    public void setMyBatisUserService(MyBatisUserService myBatisUserService) {
        _mybatisUserService = myBatisUserService;
    }

    @Autowired
    public void setJooqUserService(JooqUserService jooqUserService){
        _jooqUserService = jooqUserService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getAllUsers() {

//        PageHelper.startPage(2, 4);
//        List users = _mybatisUserService.getAllUsers();

        List users = _jooqUserService.getAllUsers();

        log.info("这是测试信息");
        log.debug("这是调试信息");

        return users;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public User insertUser(String name, Integer age) {
        log.debug("插入用户", name, age);

//        _userService.addUser(name, age);

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName(name);
        user.setAge(age);
        users.add(user);
        _mybatisUserService.batchAddUsers(users);

        return _mybatisUserService.findByName(name);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteUser(Integer id){
        log.debug("删除用户", id);

        _jooqUserService.delete(id);
    }
}