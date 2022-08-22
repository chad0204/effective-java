package com.pc.lianlian.mevl.demo;


import com.alibaba.fastjson.JSON;
import com.pc.lianlian.mevl.demo.model.FactorModel;
import com.pc.lianlian.mevl.demo.queryclass.BillService;
import com.pc.lianlian.mevl.demo.queryclass.ProductService;
import com.pc.lianlian.mevl.demo.queryclass.UserService;
import org.apache.commons.lang3.StringUtils;
import org.mvel2.MVEL;

import java.util.Map;

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

        System.out.println("因子加载完成: " + JSON.toJSONString(ruleConditionContext));
        //执行最终表达式
        return (boolean) MVEL.eval(
                ruleConditionContext.getRuleConditionModel().getRuleConditionMvel(),
                ruleConditionContext.getParamMap()
        );
    }


    /**
     * 并行执行 中断执行
     * @param ruleConditionContext
     * @return
     */
    private Boolean loadFactors(ConditionExecutorContext ruleConditionContext) {

        for (FactorModel factor: ruleConditionContext.getRuleConditionModel().getFactorList()) {
            //解析入参的值, 实现因子加载结果复用
            parseParamValue(ruleConditionContext.getParamMap(), factor);

            loadFactor(ruleConditionContext, factor);

            if (StringUtils.isNotEmpty(factor.getPostProcessorExp())) {
                //后置处理器
                Boolean res = (Boolean) MVEL.eval(factor.getPostProcessorExp(), ruleConditionContext.getParamMap());
                if (!res) {
                    System.out.println("后置处理器校验失败: 输出因子和上下文" + factor);
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    private void parseParamValue(Map<String, Object> paramMap, FactorModel factor) {
        factor.getInputParameterList().forEach(param -> {
            if (StringUtils.isNotEmpty(param.getParseExpression())) {
                //通过参数解析器从上下文中获取
                Object value = MVEL.eval(param.getParseExpression(), paramMap);
                if (value == null) {
                    throw new IllegalArgumentException();
                }
                if (!value.getClass().getName().equals(param.getParamType())) {
                    throw new IllegalArgumentException();
                }
                param.setParamValue(value);
            } else {
                //直接从上下文参数中获取（没有入参表达式说明可以直接从事件中获取或者属于营销系统变量，系统变量
                // 不用设置入参，因子加载方法中从activity中获取）
                param.setParamValue(paramMap.get(param.getParamName()));
            }
        });
    }

    /**
     * 需要入参的类型和返回值的类型嘛
     * @param ruleConditionContext
     * @param factor
     */
    private void loadFactor(ConditionExecutorContext ruleConditionContext, FactorModel factor) {
        if (factor.getDataSourceType().equals(FactorTypeEnum.ASSEMBLE.getCode())) {
            //匹配到类
            String queryClass = factor.getQueryClass();
            //TODO 策略模式
            FactorResult result;
            if (queryClass.equals("userService")) {
                result = new UserService().load(ruleConditionContext, factor);
            } else if (queryClass.equals("productService")){
                result = new ProductService().load(ruleConditionContext, factor);
            } else {
                result = new BillService().load(ruleConditionContext, factor);
            }
            ruleConditionContext.getParamMap().put(factor.getFactorAliasName(), result.getData());
        } else {
            //TODO 探头因子, 不用维护, 如果是事件参数已存在于paramMap, 如果是活动参数,包含在activity中。
            // 比如 当前apollo配置的B2C_PLATFORM_ID_LIST, productCodeList这些可以在条件中设置
            Map<String, Object> eventParams = ruleConditionContext.getParamMap();
        }

    }


}
