package dev.spider.boot;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author spider
 */
public class SimpleSentinelTest {

    private static void doSomething() {
        try (Entry entry = SphU.entry("doSomething")) {
            //protected resource
        } catch (Exception ignore) {

        }
    }

    @Before
    public void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("doSomething");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }

    @Test
    public void testSentinelSimpleMethod() {
        while (true) {
            doSomething();
        }
    }

}
