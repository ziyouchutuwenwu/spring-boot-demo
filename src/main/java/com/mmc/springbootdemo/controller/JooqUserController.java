package com.mmc.springbootdemo.controller;

import com.mmc.springbootdemo.model.User;
import com.mmc.springbootdemo.service.user.IUserService;
import com.mmc.springbootdemo.service.user.impl.JooqUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users/jooq")
public class JooqUserController {

    private IUserService _jooqUserService;

    @Autowired
    @Qualifier("jooqService")
    public void setJooqService(IUserService jooqUserService) {
        _jooqUserService = jooqUserService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> getAllUsers() {

//        PageHelper.startPage(2, 4);
        List<User> users = _jooqUserService.getAllUsers();

        log.debug("这是getAllUsers查询");

        return users;
    }

    @RequestMapping(value = "/find_by_name", method = RequestMethod.GET)
    public User findByName(String name) {

        User user = _jooqUserService.findByName(name);

        log.info("这是findByName查询");

        return user;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public User insertUser(String name, Integer age) {

        log.debug("这是insertUser", name, age);

        _jooqUserService.addUser(name, age);

        return _jooqUserService.findByName(name);
    }

    @RequestMapping(value = "/batch_add", method = RequestMethod.GET)
    public User batchAddUsers(String name, Integer age) {

        log.debug("这是batchAddUsers", name, age);

        List<User> users = new ArrayList<>();
        User user = new User();
        user.setName(name);
        user.setAge(age);
        users.add(user);
        _jooqUserService.batchAddUsers(users);

        return _jooqUserService.findByName(name);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void deleteUser(String name){
        log.debug("这是deleteUser", name);

        _jooqUserService.deleteUser(name);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public void updateUser(String conditionName, Integer newAge){
        log.debug("这是updateUser", conditionName, newAge);

        _jooqUserService.updateUser(conditionName, newAge);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public void clearUsers(){
        log.debug("这是clearUsers");

        _jooqUserService.clear();
    }

    @RequestMapping(value = "/transcation_demo", method = RequestMethod.GET)
    public void transcationDemo(){
        log.debug("这是transcationDemo");

        _jooqUserService.doTranscation();
    }
}