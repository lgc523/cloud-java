package dev.spider.gw.service.impl;

import dev.spider.gw.entity.Req;
import dev.spider.gw.service.ServiceT;
import org.springframework.stereotype.Service;

/**
 * @author spider
 */
@Service("serviceB")
public class ServiceBImpl implements ServiceT {
    @Override
    public String pay(Req req) {
        return "serviceBPay";
    }
}
