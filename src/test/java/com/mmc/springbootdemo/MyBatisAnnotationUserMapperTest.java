package com.mmc.springbootdemo;

import com.mmc.springbootdemo.dao.mybatis.AnnotationUserMapper;
import com.mmc.springbootdemo.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBatisAnnotationUserMapperTest {

    private AnnotationUserMapper _annotationUserMapper;

    @Autowired
    public void setAnnotationUserMapper(AnnotationUserMapper userMapper){
        _annotationUserMapper = userMapper;
    }

    @Test
    public void doUserMapperTest(){

        _annotationUserMapper.truncate();
        _annotationUserMapper.insert("rico", 123);

        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setAge(10);
        user1.setName("aaa");
        users.add(user1);

        User user2 = new User();
        user2.setAge(20);
        user2.setName("bbb");
        users.add(user2);

        _annotationUserMapper.batchInsert(users);

        User user;
        user = _annotationUserMapper.findByName("rico");
        assert(user.getAge() == 123);

        user = _annotationUserMapper.findByName("aaa");
        assert(user.getAge() == 10);

        user = _annotationUserMapper.findByName("bbb");
        assert(user.getAge() == 20);

        users = _annotationUserMapper.getAllUsers();
        assert(users.size() == 3);

        _annotationUserMapper.updateUser("rico", 111);
        user = _annotationUserMapper.findByName("rico");
        assert(user.getAge() == 111);

        _annotationUserMapper.deleteUser("rico");
        users = _annotationUserMapper.getAllUsers();
        assert(users.size() == 2);
    }
}