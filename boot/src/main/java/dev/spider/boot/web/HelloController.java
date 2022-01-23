package dev.spider.boot.web;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import dev.spider.boot.config.AppMetric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spider
 */
@RestController
public class HelloController {

    @Autowired
    AppMetric am;

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    @SentinelResource(value = "hello", fallback = "helloFallback")
    public String greeting(@RequestParam(value = "phone") String phone) {
        System.out.println(am.getAppName());
        System.out.println(am.getAppDate());
        System.out.println(am.getRandom());
        return "hello spider";
    }

    public String helloFallback(String phone, Throwable ex) {
        ex.printStackTrace();
        return "Oops,error occurred at " + phone;
    }

}
