package com.pc.lianlian.mevl.demo;

import com.pc.lianlian.mevl.demo.model.RuleConditionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * 条件执行上下文
 * @author pengchao
 * @since 2022/8/17 18:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConditionExecutorContext {

    /**
     * 活动详情
     */
    private String activity;

    /**
     * 条件详情
     */
    private RuleConditionModel ruleConditionModel;


    /**
     * 包含事件和因子
     */
    private ConcurrentHashMap<String, Object> paramMap = new ConcurrentHashMap<>();
}
