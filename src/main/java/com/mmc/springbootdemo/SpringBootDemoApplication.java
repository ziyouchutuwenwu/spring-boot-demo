package com.mmc.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/*
* 前面两个是mybatis
* 后面是mybatis-plus
* */
@MapperScan({"my.mybatis.generator.auto","com.mmc.springbootdemo.dao.mybatis", "my.mbp.generator.auto"})
@ServletComponentScan
@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}