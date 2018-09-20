package com.mmc.springbootdemo;

import my.mbp.generator.auto.entity.Users;
import my.mbp.generator.auto.mapper.UsersMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MBPTest {

    @Autowired
    private UsersMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<Users> userList = userMapper.selectList(null);
        Assert.assertEquals(10, userList.size());
        userList.forEach(System.out::println);
    }
}