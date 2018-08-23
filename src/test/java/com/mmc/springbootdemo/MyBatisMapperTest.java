package com.mmc.springbootdemo;

import com.mmc.springbootdemo.dao.mybatis.UserMapper;
import com.mmc.springbootdemo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisMapperTest {

    private UserMapper _userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        _userMapper = userMapper;
    }

    @Test
    public void doUserMapperTest(){

        _userMapper.truncate();
        _userMapper.insert("rico", 123);

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(10);
        user1.setName("aaa");
        users.add(user1);

        User user2 = new User();
        user2.setAge(20);
        user2.setName("bbb");
        users.add(user2);

        _userMapper.batchInsert(users);

        User user;
        user = _userMapper.findByName("rico");
        assert(user.getAge() == 123);

        user = _userMapper.findByName("aaa");
        assert(user.getAge() == 10);

        user = _userMapper.findByName("bbb");
        assert(user.getAge() == 20);

        users = _userMapper.getAllUsers();
        assert(users.size() == 3);

        _userMapper.updateUser("rico", 111);
        user = _userMapper.findByName("rico");
        assert(user.getAge() == 111);

        _userMapper.deleteUser("rico");
        users = _userMapper.getAllUsers();
        assert(users.size() == 2);
    }
}