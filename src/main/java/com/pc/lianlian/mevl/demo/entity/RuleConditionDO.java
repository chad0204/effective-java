package com.pc.lianlian.mevl.demo.entity;

import lombok.Data;

/**
 *
 * @author pengchao
 * @since 2022/8/18 14:31
 */
@Data
public class RuleConditionDO {

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
     * 变量因子json 其实建议做成关联表 不然后期扩展字段会毕竟难受
     */
    private String factors;
}
