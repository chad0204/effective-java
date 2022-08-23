package com.pc.lianlian.mevl.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 入参配置
 *
 * @author pengchao
 * @since 2022/8/23 14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FactorInputParamConfigModel {

    private String paramName;

    private String paramType;

    private String parseExpression;

    private Object paramValue;
}
