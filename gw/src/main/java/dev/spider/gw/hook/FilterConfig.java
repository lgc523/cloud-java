package dev.spider.gw.hook;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author spider
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean httpReqFiler() {
        FilterRegistrationBean reg = new FilterRegistrationBean();
        reg.setFilter(new HttpReqFilter());
        reg.setOrder(0);
        reg.addUrlPatterns("/*");
        reg.setName("httpReqFilter");
        return reg;
    }

}
