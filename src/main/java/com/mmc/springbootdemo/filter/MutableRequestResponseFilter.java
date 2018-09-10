package com.mmc.springbootdemo.filter;

import com.mmc.springbootdemo.wrapper.request.ParameterRequestWrapper;
import com.mmc.springbootdemo.wrapper.response.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(filterName = "MutableRequestResponseFilter", urlPatterns = "/*")
public class MutableRequestResponseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器：before MutableRequestResponseFilter");

        ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper((HttpServletRequest)request);
        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) response);
        filterChain.doFilter(requestWrapper, responseWrapper);

        log.info("过滤器：after MutableRequestResponseFilter");
    }

    @Override
    public void destroy() {
    }
}
