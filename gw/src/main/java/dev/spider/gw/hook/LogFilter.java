package dev.spider.gw.hook;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author spider
 */
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}