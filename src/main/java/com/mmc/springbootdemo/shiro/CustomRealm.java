package com.mmc.springbootdemo.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Override
    public String getName() {
        return "customRealm";
    }

    /**
     * subject的login方法调用这个
     * 在这个方法中，进行登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        log.info("准备登录");

        //用户名
        String username = (String) token.getPrincipal();
        log.info("username: "+username);

        //密码
        String password = new String((char[])token.getCredentials());
        log.info("password: "+password);

        //从数据库获取用户名密码进行匹配，这里为了方面，省略数据库操作
        if(!"admin".equals(username)){
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }
        //身份验证通过,返回一个身份信息
        AuthenticationInfo info = new SimpleAuthenticationInfo(username,password,getName());

        return info;
    }

    /**
     * 获取身份信息，我们可以在这个方法中，从数据库获取该用户的权限和角色信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        log.info("判断角色和权限");
        String username = (String) getAvailablePrincipal(principals);

        //我们可以通过用户名从数据库获取权限/角色信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //权限
        Set<String> permissions = new HashSet<String>();
        permissions.add("printer:print");
        permissions.add("printer:query");
        info.setStringPermissions(permissions);

        //角色
        Set<String> roles = new HashSet<String>();
        roles.add("role1");
        info.setRoles(roles);

        return info;
    }
}
