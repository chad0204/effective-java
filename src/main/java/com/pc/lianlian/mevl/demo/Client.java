package com.pc.lianlian.mevl.demo;


import com.pc.lianlian.mevl.demo.model.RuleConditionModel;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * 背景：奖励发放条件、 优惠满减条件 、 优惠使用门槛条件等
 *
 * 目标：使用因子组装条件，条件是动态的，因子是静态的。
 *
 * 事件变化 -> 因子（需要的入参变化了）变化
 *
 * 怎样使事件发生变化，只需条件变化（配置），无需新的因子（编码）!!! 通过条件解耦
 *
 *
 *
 *


 例如
 条件A
 查询r1 需要参数a, b, c
 查询r2 需要参数r1, d
 查询r3 需要参数 r1, r2, e

 最后需要输出r1, r2, r3。事件参数必须包含a,b,c,d,e

 方案1：

 因子 r3 参数 a，b，c，d，e

 因子 r2 参数 a，b，c，d

 因子 r1 参数 a, b ,c


 问题1: 重复查询，查完r1， 还要在r2中再次查r1。r3中再次查r1,r2。
 条件原本需要r1，r2, r3三个因子, 如果可以(最终表达式无需三个条件进行"||"判断或者contains判断)，现在只需要r3即可，则不会重复查询多次。

 问题2：
 由于因子粒度比较大，导致如果事件不满足因子所需的参数，就需要开发新的因子。本来建三个因子，可以组合使用。现在代码重复比较多。

 问题3：
 创建因子时，不知道如何确认粒度


 方案2

 因子 r1 参数 a, b ,c

 因子 r2 参数 r1，d

 因子 r3 参数 r1, r2, e


 问题1：关联时校验复杂，在事件选择条件时，原先只需校验所有因子的参数即可，现在需要在参数中剔除前面已经出现的因子再进行校验。
 问题2： 创建因子时，不好判断因子需要的参数的粒度，粒度过细（比如查产品因子就用产品id，但是产品id不在事件中，需要从用户-》账单-》产品）可能需要过多前置因子。
 如果方案1, 一样不好评估因子的参数，因为你也不知道因子被哪个事件用到。而且使用方案2，因子的查询粒度基本就是外部接口或者数据库单表的粒度(参数为基本类型)，复用度比较高，无非是需要创建比较多的条件，而这是符合预期的（无需编码）。


 问题3：由于参数必须是基本类型，所以因子的结果，不好校验, 是否可以考虑条件创建的时候选择事件，这样就无需校验是否参数是否满足。


 综合考虑，使用方案2比较好，比如事件参数不符合当前已存在的因子所需参数，可创建符合事件参数的条件（通过几次中间因子进行参数转换），而无需开发新的因子。





 优化：具备后置条件的因子必须配置优先级。无后置条件的因子可以并行加载。




 *
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


        //msg user要买车， 定义用户 定义产品 ，原始因子是userId

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


        Map<String, Object> msg = new HashMap<>();
        conditionExecutorContext.setEventParams(msg);
        conditionExecutor.executor(conditionExecutorContext);
    }
}
