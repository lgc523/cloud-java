package dev.spider.gw.hook;

import com.alibaba.fastjson.JSON;
import dev.spider.gw.annotation.ParamValidPreHook;
import dev.spider.gw.valid.ValidHello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author spider
 */
@Slf4j
public class ValidInterceptor extends HandlerInterceptorAdapter {

    static final HashMap<String, Class> typeMap = new HashMap<>();
    static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    static {

        typeMap.put("1", ValidHello.ValidA.class);
        typeMap.put("2", ValidHello.ValidB.class);
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        ParamValidPreHook methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(ParamValidPreHook.class);
        if (Objects.nonNull(methodAnnotation)) {

//            String body = new RequestWrapper(request).getBodyString();

            Class reqClass = methodAnnotation.bodyClass();
            String target = methodAnnotation.markParam();
            ServletInputStream inputStream = request.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            inputStream.skip(-(bytes.length));
            String body = new String(bytes, StandardCharsets.UTF_8);
            log.info("body:\n{}", body);
            String[] split = body.split(",");
            List<String> collect = Arrays.stream(split).filter(s -> s.contains(target)).collect(Collectors.toList());
            String type = "";
            if (!CollectionUtils.isEmpty(collect)) {
                String s = collect.get(0);
                String[] split1 = s.split(":");
                type = split1[1];
                String realType = type.replace("\"", "").replace("}", "");
                log.info("type:{}", realType);
                type = realType;
            }
            Class group = typeMap.get(type);

            Set<ConstraintViolation<Class>> validate = validator.validate(reqClass, group);
            log.info("valid result:\n{}", JSON.toJSONString(validate));
            return true;
        }
        return true;
    }
}
