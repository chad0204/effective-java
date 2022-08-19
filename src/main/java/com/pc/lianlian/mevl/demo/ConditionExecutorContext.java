package com.pc.lianlian.mevl.demo;

import com.pc.lianlian.mevl.demo.biz.Msg;
import com.pc.lianlian.mevl.demo.model.RuleConditionModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private RuleConditionModel ruleConditionModel;

    /**
     * 触发事件参数
     */
    private Map<String, Object> eventParams;

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
