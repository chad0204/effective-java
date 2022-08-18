package com.pc.lianlian.mevl.demo.model;

import com.pc.lianlian.mevl.demo.entity.FactorDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author pengchao
 * @since 2022/8/18 11:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FactorModel {
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
     * 对应的因子 需要查询
     */
    private FactorDO factorDO;
}
