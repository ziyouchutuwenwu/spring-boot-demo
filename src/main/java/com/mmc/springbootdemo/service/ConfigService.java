package com.mmc.springbootdemo.service;

import com.mmc.springbootdemo.dao.ConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigService {

    private ConfigDao _configDao;

    @Autowired
    public ConfigService(ConfigDao configDao) {
        _configDao = configDao;
    }

    public String getName(){
        return _configDao.getName();
    }
}
