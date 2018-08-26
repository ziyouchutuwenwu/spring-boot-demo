package com.mmc.springbootdemo.service.user.impl;

import com.mmc.springbootdemo.dao.mybatis.UserMapper;
import com.mmc.springbootdemo.model.User;
import com.mmc.springbootdemo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MyBatisUserService implements IUserService {

    private UserMapper _userMapper;

    @Autowired
    public MyBatisUserService(UserMapper userMapper) {
        _userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
        return _userMapper.getAllUsers();
    }

    @Override
    public User findByName(String name){
        return _userMapper.findByName(name);
    }

    @Override
    public void doTranscation() {
    }

    @Override
    public void addUser(String name, Integer age) {
        _userMapper.insert(name, age);
    }

    @Override
    public void batchAddUsers(List<User> users) {
        _userMapper.batchInsert(users);
    }

    @Override
    public void deleteUser(String name) {
        _userMapper.deleteUser(name);
    }

    @Override
    public void updateUser(String conditionName, Integer newAge) {
        _userMapper.updateUser(conditionName, newAge);
    }

    @Override
    public void clear() {
        _userMapper.truncate();
    }
}