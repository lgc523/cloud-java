package dev.spider.gw.service.impl;

import dev.spider.gw.service.ServiceT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author spider
 */
@Service
public class ServiceTContext {

    @Autowired
    Map<String, ServiceT> serviceTMap;

    public ServiceT getService(String type) {
        return serviceTMap.get(type);
    }
}
