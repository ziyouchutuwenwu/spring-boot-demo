package com.mmc.springbootdemo.service.user;

import com.mmc.springbootdemo.model.User;
import java.util.List;

public interface IUserService {
    void addUser(String name, Integer age);
    void batchAddUsers(List<User> users);

    void deleteUser(String name);
    void updateUser(String conditionName, Integer newAge);

    void clear();

    List<User> getAllUsers();
    User findByName(String name);

    void doTranscation();
}