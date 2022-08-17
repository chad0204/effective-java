package com.pc.lianlian.mevl.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pc.lianlian.mevl.demo.query.Service;
import com.pc.something.User;
import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author pengchao
 * @since 2022/8/17 17:17
 */
public class ConditionExecutor {

    public boolean executor(RuleConditionContext ruleConditionContext) {
        //加载参数
        Boolean execute = loadFactors(ruleConditionContext);
        if (!execute) {
            return Boolean.FALSE;
        }

        //执行表达式
        Object eval = MVEL.eval(ruleConditionContext.getRuleCondition().getRuleConditionMvel(), ruleConditionContext.getParamMap());

        return (boolean) eval;
    }


    private Boolean loadFactors(RuleConditionContext ruleConditionContext) {
        //遍历处理因子, 后继的因子计算可能需要前面的因子的值
        List<ConditionFactorDO> factors = ruleConditionContext.getRuleCondition().getFactors();

        //优先级
        for (ConditionFactorDO factor: factors) {
            loadFactor(ruleConditionContext, factor);

            if (StringUtils.isNotEmpty(factor.getPreProcessorExp())) {
                //后置处理
                MVEL.eval(factor.getPreProcessorExp(), ruleConditionContext.getParamMap());
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    private void loadFactor(RuleConditionContext ruleConditionContext, ConditionFactorDO factor) {


        Map<String, Object> paramMap = ruleConditionContext.getParamMap();



        //需要加载
        if (factor.getFactorDO().getDataSourceType() == 1) {
            //匹配到类
            String queryClass = factor.getFactorDO().getQueryClass();
            Service service = new Service();

            //必填入参类型
            List<FactorDO.RpcParamConfig> inputParameterList = factor.getFactorDO().getInputParameterList();

            //
            String res = service.query(ruleConditionContext.getParamMap());

            //返回结果类型
            String dataType = factor.getFactorDO().getDataType();//返回类型

            paramMap.put(factor.getFactorAliasName(), res);
        }


        //后置处理
        MVEL.eval(factor.getPostProcessorExp(), ruleConditionContext.getParamMap());

    }


}
