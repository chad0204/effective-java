package com.pc.lianlian.mevl.demo.entity;

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
 * 如果是ORIGINAL类型的因子，比如两个msg的参数名称相同，那么就必须定义别名来区分唯一。
 * 多个因子同名？不可以的
 *
 *
 *
 * 如果只存一个json，那么可以不用新建表
 *
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
     *
     * 如果是
     *
     */
    private String factorAliasName;

    /**
     * 关联的因子
     */
    private Long factorId;


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



}
