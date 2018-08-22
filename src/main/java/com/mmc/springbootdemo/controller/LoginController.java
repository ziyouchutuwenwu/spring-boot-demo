package com.mmc.springbootdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @RequestMapping(value = "/do_login", method = RequestMethod.POST)
    public void doLogin(String name, String password) {

        Subject currenUser = SecurityUtils.getSubject();

        //如果还未认证
        if (!currenUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
//            token.setRememberMe(true);

            try {
                currenUser.login(token);
            } catch (UnknownAccountException uae) {
                log.info("没有该用户： " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                log.info(token.getPrincipal() + " 的密码不正确!");
            } catch (LockedAccountException lae) {
                log.info(token.getPrincipal() + " 被锁定 ，请联系管理员");
            } catch (AuthenticationException ae) {
                log.info(token.getPrincipal() + " 未知的认证异常");
            }
        }

        if (currenUser.isAuthenticated()) {
            log.info("用户 " + currenUser.getPrincipal() + " 登录成功");
        }

        //是否有role1这个角色
        if (currenUser.hasRole("role1")) {
            log.info("有角色role1");
        } else {
            log.info("没有角色role1");
        }
        //是否有对打印机进行打印操作的权限
        if (currenUser.isPermitted("printer:print")) {
            log.info("可以对打印机进行打印操作");
        } else {
            log.info("不可以对打印机进行打印操作");
        }
    }
}
