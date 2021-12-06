package dev.spider.gw.hook;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import dev.spider.gw.annotation.PostBodyValidPreHook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


/**
 * @author spider
 */
@Slf4j
public class ValidInterceptor extends HandlerInterceptorAdapter {

    static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        PostBodyValidPreHook methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PostBodyValidPreHook.class);
        AtomicBoolean chain = new AtomicBoolean(true);
        if (Objects.nonNull(methodAnnotation)) {
            Optional<String> stringOptional = new ServletRequestWrapper(request).getWrappedBodyString();
            stringOptional.ifPresent(body -> {
                Type clz = methodAnnotation.bodyClass();
                Class[] validGroupClassArr = methodAnnotation.validGroup();
                Object bean = JSON.parseObject(body, clz);
                HashSet<ConstraintViolation<Object>> allSet = Sets.newHashSet();
                for (Class aClass : validGroupClassArr) {
                    Set<ConstraintViolation<Object>> validate = validator.validate(bean, aClass);
                    allSet.addAll(validate);
                }
                Set<String> validResult = allSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
                if (!CollectionUtils.isEmpty(validResult)) {
                    chain.set(false);
                    log.error("\nPARAM IS ILLEGAL: \nUri:[{}] \nClass:[{}] \nDesc:{}", request.getServletPath(), clz, validResult);
                }
            });
        }
        return chain.get();
    }
}
