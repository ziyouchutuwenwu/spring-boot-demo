package com.mmc.springbootdemo.service.user.impl;

import com.mmc.springbootdemo.dao.mybatis.AnnotationUserMapper;
import com.mmc.springbootdemo.dao.mybatis.XmlUserMapper;
import com.mmc.springbootdemo.model.User;
import com.mmc.springbootdemo.service.user.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("mybatisService")
public class MyBatisUserService implements IUserService {

    private XmlUserMapper _xmlUserMapper;
    private AnnotationUserMapper _annotationUserMapper;

    @Autowired
    public void setXmlUserMapper(XmlUserMapper userMapper) {
        _xmlUserMapper = userMapper;
    }

    @Autowired
    public void setAnnotationUserMapper(AnnotationUserMapper userMapper) {
        _annotationUserMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
//        return _annotationUserMapper.getAllUsers();

        //这段使用xml的mapper测试
        List<User> result = new ArrayList<User>();

        List<my.mybatis.generator.auto.entity.User> userList =  _xmlUserMapper.selectAll();
        for ( my.mybatis.generator.auto.entity.User iterUser : userList ){
            User user = new User();
            user.setId(iterUser.getId());
            user.setAge(iterUser.getAge());
            user.setName(iterUser.getName());
            result.add(user);
        }

        return result;
    }

    @Override
    public User findByName(String name){
        return _annotationUserMapper.findByName(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doTranscation(){
        _annotationUserMapper.insert("rico", 10);
        throw new RuntimeException("mybatis 事务抛异常");
    }

    @Override
    public void addUser(String name, Integer age) {
        _annotationUserMapper.insert(name, age);
    }

    @Override
    public void batchAddUsers(List<User> users) {
        _annotationUserMapper.batchInsert(users);
    }

    @Override
    public void deleteUser(String name) {
        _annotationUserMapper.deleteUser(name);
    }

    @Override
    public void updateUser(String conditionName, Integer newAge) {
        _annotationUserMapper.updateUser(conditionName, newAge);
    }

    @Override
    public void clear() {
        _annotationUserMapper.truncate();
    }
}