package dev.spider.gw.hook;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import dev.spider.gw.annotation.PostBodyValidPreHook;
import dev.spider.gw.service.impl.ServiceAImpl;
import dev.spider.gw.service.impl.ServiceBImpl;
import dev.spider.gw.valid.DefaultValidGroup;
import dev.spider.gw.valid.HelloValidGroup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author spider
 */
@Slf4j
public class ValidInterceptor extends HandlerInterceptorAdapter {

    static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    static final HashMap<String, Pair<Class[], Class>> routeMap = new HashMap<>();

    static {
        routeMap.put("1", Pair.of(new Class[]{HelloValidGroup.ValidA.class, DefaultValidGroup.Default.class}, ServiceAImpl.class));
        routeMap.put("2", Pair.of(new Class[]{HelloValidGroup.ValidB.class, DefaultValidGroup.Default.class}, ServiceBImpl.class));

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        PostBodyValidPreHook methodAnnotation = ((HandlerMethod) handler).getMethodAnnotation(PostBodyValidPreHook.class);
        if (Objects.nonNull(methodAnnotation)) {

            Optional<String> stringOptional = new ReqWrapper(request).wrappedBodyString();
            stringOptional.ifPresent(body -> {
                Class clz = methodAnnotation.bodyClass();
                String routeKey = methodAnnotation.routeKey();
                Object bean = null;
                Class[] validGroupClassArr = new Class[]{};
                Class serviceInvoke = null;
                try {
                    bean = JSON.parseObject(body, clz);
                    PropertyDescriptor pd = new PropertyDescriptor(routeKey, clz);
                    Method readMethod = pd.getReadMethod();
                    String route = (String) readMethod.invoke(routeKey, bean);
                    Pair<Class[], Class> classPair = routeMap.get(route);
                    validGroupClassArr = classPair.getLeft();
                    serviceInvoke = classPair.getRight();
                } catch (Exception ignore) {

                }
                HashSet<ConstraintViolation<Object>> allSet = Sets.newHashSet();
                for (Class aClass : validGroupClassArr) {
                    Set<ConstraintViolation<Object>> validate = validator.validate(bean, aClass);
                    allSet.addAll(validate);
                }

                Set<String> validResult = allSet.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
                if (!CollectionUtils.isEmpty(validResult)) {
                    String result = Joiner.on(",").join(validResult);
                    ReqSidCar.put(Pair.of(result, serviceInvoke));
                    String format = "\n----->\nPARAM IS ILLEGAL: \nUri:[{}] \nClass:[{}] \nDesc:{}\n<-----";
                    log.error(format, request.getServletPath(), clz, validResult);
                }
            });
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ReqSidCar.clear();
        super.afterCompletion(request, response, handler, ex);
    }
}
