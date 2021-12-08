package dev.spider.gw.hook;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * filter wrapper servletRequest make io repeatable
 *
 * @author spider
 */
@Slf4j
public class HttpReqFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = new ReqWrapper((HttpServletRequest) request);
        chain.doFilter(requestWrapper, response);
    }
}
