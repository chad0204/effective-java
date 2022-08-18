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
        //加载参数
        Boolean execute = loadFactors(ruleConditionContext);
        if (!execute) {
            return Boolean.FALSE;
        }

        //执行表达式
        Object eval = MVEL.eval(ruleConditionContext.getRuleConditionModel().getRuleConditionMvel(), ruleConditionContext.getParamMap());

        return (boolean) eval;
    }


    /**
     * 并行执行 中断执行
     * @param ruleConditionContext
     * @return
     */
    private Boolean loadFactors(ConditionExecutorContext ruleConditionContext) {
        //遍历处理因子, 后继的因子计算可能需要前面的因子的值
        List<ConditionFactorDO> factors = ruleConditionContext.getRuleConditionModel().getFactorList();

        //封装因子详情
        FactorDO product = new FactorDO();
        FactorDO user = new FactorDO();
        Map<Long, FactorDO> map = new HashMap<>();
        map.put(1001L, product);
        map.put(1002L, user);


        //优先级及封装
        List<FactorModel> sortedFactors = factors.stream()
                .sorted(Comparator.comparing(ConditionFactorDO::getPriority))
                .map(cf -> FactorModel.builder()
                        .factorDO(map.get(cf.getFactorId()))
                        .build())
                .collect(Collectors.toList());




        for (FactorModel factor: sortedFactors) {
            //包含完整的factor( conditionFactor + factor)
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

        //需要加载
        if (factor.getFactorDO().getDataSourceType() == 1) {
            //匹配到类
            String queryClass = factor.getFactorDO().getQueryClass();

            //必填入参类型
            List<FactorDO.RpcParamConfig> inputParameterList = factor.getFactorDO().getInputParameterList();

            //调用方法
            FactorResult query;
            if (queryClass.equals("userService")) {
                query = new UserService().query(ruleConditionContext.getParamMap());
            } else {
                query = new ProductService().query(ruleConditionContext.getParamMap());
            }

            //返回结果类型
            String dataType = factor.getFactorDO().getDataType();//返回类型

            paramMap.put(factor.getFactorAliasName(), query.getValue());
        }

        //后置处理
        MVEL.eval(factor.getPostProcessorExp(), ruleConditionContext.getParamMap());
    }


}
