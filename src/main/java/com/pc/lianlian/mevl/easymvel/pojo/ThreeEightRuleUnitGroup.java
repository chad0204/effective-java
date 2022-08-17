package com.pc.lianlian.mevl.easymvel.pojo;

import org.jeasy.rules.annotation.*;
import org.jeasy.rules.support.UnitRuleGroup;

/**
 * TODO
 *
 * @author pengchao
 * @since 2022/8/17 16:24
 */
@Rule(name = "被3和8同时整除", description = "这是一个组合规则")
public class ThreeEightRuleUnitGroup extends UnitRuleGroup {

    public ThreeEightRuleUnitGroup(Object... rules) {
        for (Object rule : rules) {
            addRule(rule);
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }

}
