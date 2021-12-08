package dev.spider.gw.annotation;

import java.lang.annotation.*;

/**
 * @author spider
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PostBodyValidPreHook {

    /**
     * param group valid
     */
    String desc = "get valid param group and biz routes through filter http_post() body key to";

    /**
     * body Class type
     */
    Class bodyClass();

    /**
     * route key (single)
     */
    String routeKey();

    Class serviceClass();
}
