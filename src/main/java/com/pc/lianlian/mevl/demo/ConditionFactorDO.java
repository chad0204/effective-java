package com.pc.lianlian.mevl.demo;

import lombok.Data;

/**
 *
 * 条件因子
 *
 * 条件关联因子后生成
 *
 * 因子的别名
 * 因子的后置处理
 * 因子的优先级
 *
 *
 * 因子名称和原变量名称相同
 * 多个因子同名？不可以
 *
 * @author pengchao
 * @since 2022/8/17 17:28
 */
@Data
public class ConditionFactorDO {

    /**
     *  主键
     */
    private Long id;

    /**
     * 关联的condition
     */
    private Long conditionId;

    /**
     * alias
     * 定义别名 可以作为下一个因子的参数
     */
    private String factorAliasName;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 前置处理
     */
    private String preProcessorExp;

    /**
     * 后置处理
     */
    private String postProcessorExp;

    /**
     * 对应的因子
     */
    private FactorDO factorDO;


}
