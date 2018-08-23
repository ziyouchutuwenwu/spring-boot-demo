package com.mmc.springbootdemo.dao;

import com.mmc.springbootdemo.model.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConfigurationDao {

    private Configuration _configuration;

    @Autowired
    public ConfigurationDao(Configuration configuration){
        _configuration = configuration;
    }

    public String getName(){
        return _configuration.name;
    }
}
