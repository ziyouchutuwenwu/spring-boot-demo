package com.mmc.springbootdemo.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Configuration implements Serializable {

    @Value("${com.example.blog.name}")
    public String name;
}
