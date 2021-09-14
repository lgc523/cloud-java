package dev.spider.consumer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lgc
 * @apiNote naCos 动态读取数据
 */
@RestController
@RefreshScope
public class NaCosConfigController {

    @Autowired
    ApplicationContext applicationContext;
    @Value(value = "${name.spider:default}")
    private String name;

    @Value(value = "${name.age:}")
    private Integer age;

    @GetMapping("/config")
    public String get() {
        String property = applicationContext.getEnvironment().getProperty("name.spider");
        System.out.println(property);
        return name;
    }
}
