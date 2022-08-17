package com.pc.lianlian.mevl.demo;

import lombok.Data;

import java.util.List;

/**
 *
 * 因子
 *
 * @author pengchao
 * @since 2022/8/17 17:28
 */
@Data
public class FactorDO {

    /**
     *  主键
     */
    private Long id;

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
     * 必填入参json
     */
    private String inputParameter;

    /**
     * 必填入参list （inputParameter解析得到）
     */
    private List<RpcParamConfig> inputParameterList;

    /**
     *
     */

    /**
     * 所属产品线
     */
    private String productCode;

    /**
     * 加载类
     */
    private String queryClass;


    @Data
    public static class RpcParamConfig {

        private String paramValue;

        private String paramType;
    }


}
