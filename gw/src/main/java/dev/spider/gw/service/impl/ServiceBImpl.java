package dev.spider.gw.service.impl;

import dev.spider.gw.entity.Req;
import dev.spider.gw.service.ServiceT;

/**
 * @author spider
 */
public class ServiceBImpl implements ServiceT {
    @Override
    public String pay(Req req) {
        return "serviceBPay";
    }
}
