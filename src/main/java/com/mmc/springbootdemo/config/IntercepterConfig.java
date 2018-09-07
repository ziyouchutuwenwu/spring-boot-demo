package com.mmc.springbootdemo.config;

import com.mmc.springbootdemo.interceptor.MyIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {

    private MyIntercepter _intercepter;

    @Autowired
    public IntercepterConfig(MyIntercepter intercepter){
        _intercepter = intercepter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(_intercepter).addPathPatterns("/**");
    }
}