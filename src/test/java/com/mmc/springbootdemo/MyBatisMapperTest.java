package com.mmc.springbootdemo;

import com.mmc.springbootdemo.dao.UserMapper;
import com.mmc.springbootdemo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisMapperTest {

    private UserMapper _userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        _userMapper = userMapper;
    }

    @Test
    public void getAllUsers(){
        List<User> users = _userMapper.getAllUsers();
        Assert.assertEquals(users.size(), 10);
    }
}