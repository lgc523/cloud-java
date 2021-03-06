package dev.spider.provider.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lgc
 */
@RestController
@RequestMapping(value = "cfg")
@RefreshScope
public class NacosCfg {

    @Value("${cfg.useLocalCache:false}")
    private boolean useLocalCache;

    @GetMapping("get")
    public boolean get() {
        return useLocalCache;
    }
}
