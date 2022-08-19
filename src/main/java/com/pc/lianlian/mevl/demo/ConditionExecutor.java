package com.pc.lianlian.mevl.demo;


import com.pc.lianlian.mevl.demo.entity.ConditionFactorDO;
import com.pc.lianlian.mevl.demo.entity.FactorDO;
import com.pc.lianlian.mevl.demo.model.FactorModel;
import com.pc.lianlian.mevl.demo.queryclass.ProductService;
import com.pc.lianlian.mevl.demo.queryclass.UserService;
import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * 条件维度的执行
 *
 * @author pengchao
 * @since 2022/8/17 17:17
 */
public class ConditionExecutor {


    public boolean executor(ConditionExecutorContext ruleConditionContext) {
        //加载因子
        Boolean execute = loadFactors(ruleConditionContext);
        if (!execute) {
            return Boolean.FALSE;
        }

        //执行最终表达式
        Object eval = MVEL.eval(ruleConditionContext.getRuleConditionModel().getRuleConditionMvel(), ruleConditionContext.getParamMap());

        return (boolean) eval;
    }


    /**
     * 并行执行 中断执行
     * @param ruleConditionContext
     * @return
     */
    private Boolean loadFactors(ConditionExecutorContext ruleConditionContext) {
        List<ConditionFactorDO> factors = ruleConditionContext.getRuleConditionModel().getFactorList();
        Map<Long, FactorDO> factorMap = PojoQueryUtil.getFactorMap(factors.stream().map(ConditionFactorDO::getFactorId).collect(Collectors.toList()));

        //优先级及封装
        List<FactorModel> sortedFactors = factors.stream()
                .sorted(Comparator.comparing(ConditionFactorDO::getPriority))
                .map(cf -> FactorModel.builder()
                        .factorDO(factorMap.get(cf.getFactorId()))
                        .build())
                .collect(Collectors.toList());

        for (FactorModel factor: sortedFactors) {
            FactorModel factorModel = FactorModel.builder()
                    .factorAliasName(factor.getFactorAliasName())
                    .postProcessorExp(factor.getPostProcessorExp())
                    .preProcessorExp(factor.getPreProcessorExp())
                    .build();

            loadFactor(ruleConditionContext, factorModel);

            if (StringUtils.isNotEmpty(factor.getPreProcessorExp())) {
                //后置处理
                MVEL.eval(factor.getPreProcessorExp(), ruleConditionContext.getParamMap());
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    /**
     * 需要入参的类型和返回值的类型嘛
     * @param ruleConditionContext
     * @param factor
     */
    private void loadFactor(ConditionExecutorContext ruleConditionContext, FactorModel factor) {
        Map<String, Object> paramMap = ruleConditionContext.getParamMap();

        if (factor.getFactorDO().getDataSourceType().equals(FactorTypeEnum.ASSEMBLE.getCode())) {
            //匹配到类
            String queryClass = factor.getFactorDO().getQueryClass();

            //调用方法
            if (queryClass.equals("userService")) {
                FactorResult<UserService.User> user = new UserService().query(
                        ruleConditionContext.getParamMap(),
                        factor.getFactorDO().getInputParameters(),
                        factor.getFactorDO().getDataType());
                paramMap.put(factor.getFactorAliasName(), user.getData());
            } else {
                FactorResult<ProductService.Product> product = new ProductService().query(
                        ruleConditionContext.getParamMap(),
                        factor.getFactorDO().getInputParameters(),
                        factor.getFactorDO().getDataType());
                paramMap.put(factor.getFactorAliasName(), product.getData());
            }


            //pojo没法复用，转map，如何匹配到入参的名字？？？？？？




        } else {
            //原始参数，从消息中来
            //所以这里是否可以不定义因子，直接存入 event = msg（规则是否都是消息,如果是api调用，存入参数）
            //新加一个事件，本来可以直接用组装因子即可，现在却需要配置多个原始因子
            Map<String, Object> eventParams = ruleConditionContext.getEventParams();



        }

        //后置处理
        MVEL.eval(factor.getPostProcessorExp(), ruleConditionContext.getParamMap());

    }


}
