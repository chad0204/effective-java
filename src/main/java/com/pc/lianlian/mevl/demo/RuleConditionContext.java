package com.pc.lianlian.mevl.demo;

import lombok.Data;

import java.util.Map;

/**
 *
 * @author pengchao
 * @since 2022/8/17 18:01
 */
@Data
public class RuleConditionContext {

    private RuleCondition ruleCondition;

    /**
     * 触发事件
     */
    private String event;

    /**
     * 活动
     */
    private String activity;

    /**
     * 因子加载的结果
     * 别名 - 结果
     *
     * 考虑使用这个结果作为下一个因子的参数
     */
    private Map<String, Object> paramMap;
}
