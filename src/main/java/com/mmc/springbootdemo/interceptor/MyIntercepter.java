package com.mmc.springbootdemo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.mmc.springbootdemo.wrapper.response.ResponseWrapper;
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
        request.getParameterMap().put("pre_param", new String[] { "拦截器自作主张添加的参数" });

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器：postHandle");

        ResponseWrapper responseWrapper = (ResponseWrapper)response;
        byte[] bytes = responseWrapper.getBytes();

        // 原来的数据
        ServletOutputStream outputStream = responseWrapper.getResponse().getOutputStream();
        outputStream.write(bytes);

        // 加的自己的数据
        try{
            JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
            jsonObject.put("intercepted", true);
            String jsonStr = JSONObject.toJSONString(jsonObject);

            outputStream.write(jsonStr.getBytes());
        }
        catch (Exception e){
            log.error("拦截器添加json信息异常，返回信息目测不是json");

            outputStream.flush();
            outputStream.close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}