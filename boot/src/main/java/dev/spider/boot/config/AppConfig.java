package dev.spider.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * @author spider
 */
@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {


    @Autowired
    Environment env;

    @Bean
    public AppMetric am() {
        AppMetric am = new AppMetric();
        am.setAppName(env.getProperty("app.name"));
        am.setAppDate(env.getProperty("app.date"));
        am.setRandom(env.getProperty("app.random"));
        return am;
    }
}
