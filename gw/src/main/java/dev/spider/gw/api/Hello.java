package dev.spider.gw.api;

import com.alibaba.fastjson.JSON;
import dev.spider.gw.annotation.PostBodyValidPreHook;
import dev.spider.gw.entity.Req;
import dev.spider.gw.valid.DefaultValidGroup;
import dev.spider.gw.valid.HelloValidGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author spider
 */
@Slf4j
@RestController
public class Hello {

    @PostMapping("valid")
    @PostBodyValidPreHook(bodyClass = Req.class, validGroup = {DefaultValidGroup.Default.class, HelloValidGroup.ValidA.class})
    public String valid(@RequestBody Req req) {
        log.info("\nAPI-BODY:\n{}", JSON.toJSONString(req));
        return "hello";
    }
}
