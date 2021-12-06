package dev.spider.gw.api;

import com.alibaba.fastjson.JSON;
import dev.spider.gw.annotation.ParamValidPreHook;
import dev.spider.gw.entity.Req;
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
    @ParamValidPreHook(bodyClass = Req.class, markParam = "type")
    public String valid(@RequestBody Req req) {
        log.info("api-body:{}", JSON.toJSONString(req));
        return "hello";
    }
}
