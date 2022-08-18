package com.pc.lianlian.mevl.demo;

import com.pc.lianlian.mevl.demo.entity.FactorDO;
import com.pc.lianlian.mevl.demo.model.RuleConditionModel;

import java.util.stream.Collectors;

/**
 *
 * @author pengchao
 * @since 2022/8/18 16:36
 */
public class Client {

    public static void main(String[] args) {

        /*

        成年人才能买特斯拉，并且钱要够

        1. factors :
        product, product.name == 特斯拉 判断是不是特斯拉
        user,  user.age>=18 判断是否成年

        2. condition

        user.money > product.price

         */

        String factors = "[{\"factorAliasName\":\"product\",\"factorId\":1001,\"factorName\":\"product\",\"priority\":0,\"preProcessorExp\":\"product.name == '特斯拉'\"},{\"factorAliasName\":\"user\",\"factorId\":1002,\"factorName\":\"user\",\"priority\":1,\"preProcessorExp\":\"user.age >= 18\"}]";
        RuleConditionModel ruleConditionModel = RuleConditionModel.builder()
                .id(111L)
                .ruleConditionName("条件名称")
                .action("策略")
                .productCode("productCode")
                .ruleConditionMvel("user.money >= product.price")
                .factors(factors)
                .build();


        ConditionExecutor conditionExecutor = new ConditionExecutor();

        ConditionExecutorContext conditionExecutorContext = new ConditionExecutorContext();
        conditionExecutorContext.setRuleConditionModel(ruleConditionModel);
        conditionExecutor.executor(conditionExecutorContext);
    }
}
