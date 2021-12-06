package dev.spider.gw.annotation;

import java.lang.annotation.*;

/**
 * @author spider
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ParamValidPreHook {

    /**
     * param group valid
     */
    String mark = "param need group valid";

    Class<?> bodyClass();

    String markParam();

}
