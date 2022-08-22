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

import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

    public List<ConditionFactorDO> getNecessaryFactorList() {


        //先加载所有因子

        //先处理无优先级的，去掉入参中的因子
        //再按照优先级从后往前, 逐步剔除入参中的因子

        //汇总去重


        return null;
    }


    public static void main(String[] args) {
        String factors = "[{\"factorAliasName\":\"aaa\",\"factorName\":\"a\",\"priority\":1,\"preProcessorExp\":\"aaa > 1\"},{\"factorAliasName\":\"bbb\",\"factorName\":\"b\",\"priority\":1,\"preProcessorExp\":\"bbb> 1\"}]";

        List<ConditionFactorDO> conditionFactorDOS = JSON.parseArray(factors, ConditionFactorDO.class);


        System.out.println();
    }

}
