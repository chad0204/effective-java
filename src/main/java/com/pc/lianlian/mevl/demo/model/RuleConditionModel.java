package com.pc.lianlian.mevl.demo.model;

import com.alibaba.fastjson.JSON;
import com.pc.lianlian.mevl.demo.PojoQueryUtil;
import com.pc.lianlian.mevl.demo.entity.ConditionFactorDO;
import com.pc.lianlian.mevl.demo.entity.FactorDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liuwm@lianlianpay.com
 * @description 用户规则条件
 * @date 2022/6/18 3:58
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleConditionModel implements java.io.Serializable {
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
     *
     * 应该拆分成顺序执行和并发执行两类
     *
     */
    private List<FactorModel> factorList;


    /**
     *
     * 加载因子必要的外部参数
     *
     * 用于校验事件参数是否满足条件的因子
     * @return
     */
    public List<FactorInputParamConfigModel> getNecessaryFactorParams() {
        //去掉无入参数解析的因子的入参
        Set<FactorInputParamConfigModel> inputParams = factorList.stream()
                .map(FactorModel::getInputParameterList)
                .flatMap(Collection::stream)
                .filter(x -> x.getParseExpression() == null)//表示直接从事件中获得的参数
                .collect(Collectors.toSet());
        return new ArrayList<>(inputParams);
    }

}
