package dev.spider.gw.hook;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author spider
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean reg = new FilterRegistrationBean();

        reg.setFilter(ReplaceStreamFilter());
        reg.addUrlPatterns("/*");
        reg.setName("HttpFilter");
        return reg;
    }

    @Bean(name = "replaceStreamFilter")
    public Filter ReplaceStreamFilter() {
        return new HttpReqFilter();
    }
}
