package com.mmc.springbootdemo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.mmc.springbootdemo.filter.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("pre_param", "拦截器自作主张添加的参数");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器：postHandle");

        ResponseWrapper respWapper = (ResponseWrapper)response;
        byte[] bytes = respWapper.getBytes();

        JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
        jsonObject.put("intercepted", true);
        String jsonStr = JSONObject.toJSONString(jsonObject);

        ServletOutputStream outputStream = respWapper.getResponse().getOutputStream();
        outputStream.write(jsonStr.getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}