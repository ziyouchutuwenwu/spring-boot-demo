package com.mmc.springbootdemo.service;

import com.mmc.springbootdemo.dao.ConfigurationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    private ConfigurationDao _configurationDao;

    @Autowired
    public ConfigurationService(ConfigurationDao configurationDao) {
        _configurationDao = configurationDao;
    }

    public String getName(){
        return _configurationDao.getName();
    }
}
