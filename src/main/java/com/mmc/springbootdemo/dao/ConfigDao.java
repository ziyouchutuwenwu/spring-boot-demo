package com.mmc.springbootdemo.dao;

import com.mmc.springbootdemo.model.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigDao {

    private Config _configuration;

    @Autowired
    public ConfigDao(Config configuration){
        _configuration = configuration;
    }

    public String getName(){
        return _configuration.name;
    }
}
