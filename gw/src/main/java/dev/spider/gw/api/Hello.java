package dev.spider.gw.api;

import com.alibaba.fastjson.JSON;
import dev.spider.gw.annotation.PostBodyValidPreHook;
import dev.spider.gw.entity.Req;
import dev.spider.gw.hook.ReqSidCar;
import dev.spider.gw.service.ServiceT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spider
 */
@Slf4j
@RestController
public class Hello {

    @Autowired
    ApplicationContext ac;

    @PostMapping("valid")
    @PostBodyValidPreHook(bodyClass = Req.class, routeKey = "type", serviceClass = ServiceT.class)
    public String valid(@RequestBody Req req) {
        log.info("\nAPI-BODY:\n{}", JSON.toJSONString(req));
        boolean pass = ReqSidCar.isPass();
        Pair<String, Class> tlSidCar = ReqSidCar.get();
        String left = tlSidCar.getLeft();
        Class right = tlSidCar.getRight();
//        Object bean = ac.getBean(right);
        log.info("\nsidCar:{}", left);
        return left;
    }
}
