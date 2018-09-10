package com.mmc.springbootdemo.config;

import com.mmc.springbootdemo.filter.MutableRequestResponseFilter;
import com.mmc.springbootdemo.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // 数字越小，加载越早，Integer.MAX_VALUE
    @Bean
    public FilterRegistrationBean<MutableRequestResponseFilter> mutableRequestResponseFilterRegistration() {

        FilterRegistrationBean<MutableRequestResponseFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new MutableRequestResponseFilter());
        registration.addUrlPatterns("/*");
        registration.setName("mutableRequestResponseFilter");
        registration.setOrder(-1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<MyFilter> myFilterlRegistration() {

        FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.setName("myFilter");
        registration.setOrder(1);
        return registration;
    }
}
