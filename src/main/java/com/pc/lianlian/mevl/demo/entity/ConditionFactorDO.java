package com.pc.lianlian.mevl.demo.entity;

import lombok.Data;
import java.util.List;

/**
 *
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
     * 定义别名，防止和事件中的入参变量名冲突。
     *
     * 因子和因子通过数据库唯一键去重，但是无法保证因子和事件中的变量名重复，且在定义因子时无法预见。
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
     * 因子入参解析器(json)
     */
    private String inputParametersParser;

    /**
     * 因子入参解析器
     */
//    private List<FactorParamParseConfig> inputParamParserList;

    /**
     * 前置处理
     */
    private String preProcessorExp;

    /**
     * 后置处理
     */
    private String postProcessorExp;




    @Data
    public static class FactorParamParseConfigModel {

        /**
         * 对应因子入参名称
         */
        private String paramName;

        /**
         * 参数表达式
         */
        private String parseExpression;

    }



}
