package com.mmc.springbootdemo.service;

import com.mmc.springbootdemo.dao.UserMapper;
import com.mmc.springbootdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserMapper _userMapper;

    @Autowired
    public void setUserMapper(UserMapper UserMapper) {
        _userMapper = UserMapper;
    }

    public List<User> getAllUsers() {
        List<User> users = _userMapper.getAllUsers();
        return users;
    }

    public User findByName(String name){
        return _userMapper.findByName(name);
    }

    public void addUser(String name, Integer age) {
        _userMapper.insert(name, age);
    }
}