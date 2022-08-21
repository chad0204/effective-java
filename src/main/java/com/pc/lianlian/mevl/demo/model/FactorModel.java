package com.pc.lianlian.mevl.demo.model;

import com.pc.lianlian.mevl.demo.entity.FactorDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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
     * 定义别名，防止和事件中的入参变量名冲突。
     *
     * 因子和因子通过数据库唯一键去重，但是无法保证因子和事件中的变量名重复，且在定义因子时无法预见。
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



    // factor do
    /**
     * 因子名称
     */
    private String factorName;

    /**
     *  描述
     */
    private String factorDesc;

    /**
     * 数据类型
     * java.lang.String
     */
    private String dataType;

    /**
     *  因子类型
     *
     *  探头 0
     *  自计算
     *
     */
    private Integer dataSourceType;


    /**
     * 必填入参list （inputParameter解析得到）
     *
     */
    private List<FactorParamConfig> inputParameterList;

    /**
     * 所属产品线
     */
    private String productCode;

    /**
     * 加载类
     */
    private String queryClass;


    @Data
    public static class FactorParamConfig {

        private String paramName;

        private String paramType;

        private String parseExpression;//user.id

        private Object paramValue;//

    }



}
