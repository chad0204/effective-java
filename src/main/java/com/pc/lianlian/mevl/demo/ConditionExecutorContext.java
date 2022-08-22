package com.pc.lianlian.mevl.demo;

import com.pc.lianlian.mevl.demo.biz.Msg;
import com.pc.lianlian.mevl.demo.model.RuleConditionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Object> paramMap = new HashMap<>();
}
