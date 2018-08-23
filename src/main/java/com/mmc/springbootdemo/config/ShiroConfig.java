package com.mmc.springbootdemo.config;

import com.mmc.springbootdemo.shiro.CustomRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//https://shiro.apache.org/spring-boot.html
@Configuration
public class ShiroConfig {

    //shiro的realm配置
    @Bean
    public Realm realm() {
        return new CustomRealm();
    }

    //表明哪些需要授权
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();

//        chainDefinition.addPathDefinition("/users/**", "authc, roles[admin]");

//        // /admin/**必须要有admin角色才可以访问
//        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");
//
//        // /docs/** 路径必须要有'document:read'权限
//        chainDefinition.addPathDefinition("/docs/**", "authc, perms[document:read]");
//
//        // 其他路径必须要登录
//        chainDefinition.addPathDefinition("/**", "authc");

        return chainDefinition;
    }
}
