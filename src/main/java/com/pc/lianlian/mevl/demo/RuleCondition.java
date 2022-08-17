package com.pc.lianlian.mevl.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author liuwm@lianlianpay.com
 * @description 用户规则条件
 * @date 2022/6/18 3:58
 */
@Data
@Slf4j
public class RuleCondition implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 条件名称
     */
    private String ruleConditionName;

    /**
     * 条件表达式
     */
    private String ruleConditionMvel;


    /**
     * 条件库支持的产品列表
     */
    private String productCode;

    /**
     * 策略，如果条件满足，可以根据策略执行一些操作
     */
    private String action;

    /**
     * 因子
     */
    private List<ConditionFactorDO> factors;


}
