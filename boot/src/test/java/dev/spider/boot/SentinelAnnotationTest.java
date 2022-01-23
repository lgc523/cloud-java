package dev.spider.boot;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author spider
 */
public class SentinelAnnotationTest {
    @SentinelResource(value = "getName", blockHandler = "blockHandlerForGetName", fallback = "getNameFallback")
    public static String getName(String phone) {
        return "hi";
    }

    public static String blockHandlerForGetName(String phone, BlockException e) {
        //access forbid deal
        System.out.println("access forbid case");
        System.out.println(1 / 0);
        return "access forbid";
    }

    public static String getNameFallback(String phone, Throwable t) {
        System.out.println("fallBack " + t.getMessage());
        return "i catch you all mother fucker.";
    }

//    private static void getName() {
//        if (SphO.entry("getName")) {
//            try {
//                //protected resource
//            } catch (Exception ignore) {
//
//            } finally {
//                SphO.exit();
//            }
//        } else {
//            //resource access forbid
//        }
//    }

    public static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("getName");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) {
        initFlowRules();
        while (true) {
            getName("5");
        }
    }
}
