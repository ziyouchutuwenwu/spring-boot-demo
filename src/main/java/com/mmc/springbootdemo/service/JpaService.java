package com.mmc.springbootdemo.service;

import com.mmc.springbootdemo.dao.JpaRepository;
import com.google.common.collect.Lists;
import com.mmc.springbootdemo.model.JpaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class JpaService {

    private JpaRepository _jpaRepo;

    @Autowired
    public JpaService(JpaRepository jpaRepo){
        _jpaRepo = jpaRepo;
    }


    public ArrayList<JpaUser> getAll(){
        return Lists.newArrayList(_jpaRepo.findAll());
    }
}
