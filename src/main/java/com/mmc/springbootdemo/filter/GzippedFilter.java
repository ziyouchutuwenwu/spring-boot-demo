package com.mmc.springbootdemo.filter;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

@Slf4j
@WebFilter(filterName = "gzippedFilter", urlPatterns = "/*")
public class GzippedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器：gzippedFilter >>>> 初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.info("过滤器：before gzippedFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        ResponseWrapper respWapper = new ResponseWrapper(resp);

        // 这里必须要包装，否则拦截器部分是拿不到outputstream的字节的
        filterChain.doFilter(req, respWapper);

        byte[] bytesBeforeGzip = respWapper.getBytes();
        log.debug("压缩前大小：" + bytesBeforeGzip.length);

        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(byteArrayOut);

        gzipOut.write(bytesBeforeGzip);
        gzipOut.close();

        byte[] bytesAfterGzipped = byteArrayOut.toByteArray();
        log.debug("压缩后大小：" + bytesAfterGzipped.length);

        resp.setHeader("Content-Encoding", "gzip");
        resp.getOutputStream().write(bytesAfterGzipped);

        log.info("过滤器：after gzippedFilter");
    }

    @Override
    public void destroy() {
        log.info("过滤器：gzippedFilter >>>> 销毁");
    }
}
