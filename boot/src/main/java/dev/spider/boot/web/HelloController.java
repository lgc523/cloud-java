package dev.spider.boot.web;

import dev.spider.boot.config.AppMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spider
 */
@RestController
public class HelloController {

    @Autowired
    AppMetric am;

    @RequestMapping("hello")
    public String greeting() {
        System.out.println(am.getAppName());
        System.out.println(am.getAppDate());
        System.out.println(am.getRandom());
        return "hello spider";
    }
}
