package com.mmc.springbootdemo;

import com.mmc.springbootdemo.model.User;
import com.mmc.springbootdemo.service.user.impl.JooqUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JooqServiceTest {

    private JooqUserService _userService;

    @Autowired
    public void setJooqUserService(JooqUserService userService){
        _userService = userService;
    }

    @Test
    public void doJooqServiceTest(){

        _userService.clear();
        _userService.addUser("rico", 123);

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(10);
        user1.setName("aaa");
        users.add(user1);

        User user2 = new User();
        user2.setAge(20);
        user2.setName("bbb");
        users.add(user2);

        _userService.batchAddUsers(users);

        User user;
        user = _userService.findByName("rico");
        assert(user.getAge() == 123);

        user = _userService.findByName("aaa");
        assert(user.getAge() == 10);

        user = _userService.findByName("bbb");
        assert(user.getAge() == 20);

        users = _userService.getAllUsers();
        assert(users.size() == 3);

        _userService.updateUser("rico", 111);
        user = _userService.findByName("rico");
        assert(user.getAge() == 111);

        _userService.deleteUser("rico");
        users = _userService.getAllUsers();
        assert(users.size() == 2);
    }
}