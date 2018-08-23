package com.mmc.springbootdemo;

import com.mmc.springbootdemo.service.JooqUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JooqTest {

    private JooqUserService _userService;

    @Autowired
    public JooqTest(JooqUserService userService){
        _userService = userService;
    }

    @Test
    public void getAllUsersTest() {
        List users = _userService.getAllUsers();
        Assert.assertEquals(users.size(), 10);
    }
}