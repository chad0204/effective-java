package com.pc.lianlian.mevl.demo;


import com.alibaba.fastjson.JSON;
import com.pc.lianlian.mevl.demo.entity.ConditionFactorDO;
import com.pc.lianlian.mevl.demo.entity.FactorDO;
import com.pc.lianlian.mevl.demo.entity.RuleConditionDO;
import com.pc.lianlian.mevl.demo.model.FactorInputParamConfigModel;
import com.pc.lianlian.mevl.demo.model.FactorModel;
import com.pc.lianlian.mevl.demo.model.RuleConditionModel;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
 条件原本需要r1，r2, r3三个因子, 只有在特定情况下(最终表达式无需三个条件进行"||"判断或者contains判断，只需要r3即可)不会重复查询多次。

 问题2：
 由于因子粒度比较大，导致如果事件不满足因子所需的参数，就需要开发新的因子（退回到硬编码）。本来建三个因子，可以组合使用。现在代码重复比较多。

 问题3：
 创建因子时，不知道如何确认粒度，容易陷入遇到新的条件或者事件，就需要开发新的因子这种情况。


 方案2

 因子 r1 参数 a, b ,c

 因子 r2 参数 r1，d

 因子 r3 参数 r1, r2, e

 注：入参必须是基本类型，因子可以是pojo


 问题1：关联时校验复杂，在事件选择条件时，原先只需校验所有因子的参数即可，现在需要在参数中剔除前面已经出现的因子再进行校验。（可以通过在创建条件时，就进行事件选择进行，跳过参数匹配度校验）
 问题2： 创建因子时，不好判断因子需要的参数的粒度，粒度过细（比如查产品因子就用产品id，但是产品id不在事件中，需要从用户-》账单-》产品）可能需要过多前置因子。
 如果方案1, 一样不好评估因子的参数，因为你也不知道因子被哪个事件用到。而且使用方案2，因子的查询粒度基本就是外部接口或者数据库单表的粒度(参数为基本类型)，复用度比较高，无非是需要创建比较多的条件，而这是符合预期的（无需编码）。


 问题3：由于参数必须是基本类型，所以因子的结果，不好校验, 是否可以考虑条件创建的时候选择事件，这样就无需校验是否参数是否满足。


 综合考虑，使用方案2比较好，比如事件参数不符合当前已存在的因子所需参数，可创建符合事件参数的条件（通过几次中间因子进行参数转换），而无需开发新的因子。



 优化：具备后置条件的因子必须配置优先级。无后置条件的因子可以并行加载。



 https://r7f0jq.axshare.com/#g=1&p=%E8%A7%84%E5%88%99%E6%9D%A1%E4%BB%B6


 [
 {
 "factorAliasName":"user",
 "factorId":1002,
 "factorName":"user",
 "priority":0,
 "preProcessorExp":"user.age >= 18"
 },
 {
 "factorAliasName":"product",
 "factorId":1001,
 "factorName":"product",
 "priority":1,
 "preProcessorExp":"product.name == '特斯拉'",
 "inputParametersConfig":[
 {
 "paramName":"userId",
 "parseExpression":"user.id"
 }
 ]
 }
 ]


 问题1. 活动关联规则时选择条件校验。建议修改成创建条件时选择事件。
 这里的校验，1.需要额外维护事件的参数信息; 2.不好区分出因子的入参是否是来自营销系统。3.关联时，选择多个事件时，大概率没有合适的因子组成的条件，需要开发因子（退回到硬编码）。 建议改成在创建条件时，选择事件，将条件挂在事件下，不用校验，创建条件是开发人员的动作。然后用户使用时，选择了事件，下拉展示合适的条件



 *
 *
 * @author pengchao
 * @since 2022/8/18 16:36
 */
public class Client {

    public static void main(String[] args) {
        /*

        输入账单, 成年人才能买特斯拉, 最好判断钱够不够

        msg： billId

        1. factors :

        bill: 入参billId, 返回productIds, userId, 无后置处理.

        product: 入参bill.productIds, 返回产品信息, 后置处理 判断是否有productName == '特斯拉'

        user: 入参bill.userId, 返回用户信息, 后置处理 user.age>=18

        2. condition


        user.money > product.price

         */


        RuleConditionDO ruleConditionDO = new RuleConditionDO();
        ruleConditionDO.setId(111L);
        ruleConditionDO.setRuleConditionName("条件名称-成年人才能买车");
        ruleConditionDO.setRuleConditionMvel("total = 0 ;foreach (x : product) {total+=x.price};user.money >= total;");
        ruleConditionDO.setAction("策略");
        ruleConditionDO.setProductCode("productCode");

        //factor condition ext
        List<ConditionFactorDO> cfs = PojoQueryUtil.getConditionFactorExt();
        //factor
        Map<Long, FactorDO> factorMap = PojoQueryUtil.getFactorMap(cfs.stream().map(ConditionFactorDO::getFactorId).collect(Collectors.toList()));

        List<FactorModel> sortedFactors = cfs.stream()
                .sorted(Comparator.comparing(ConditionFactorDO::getPriority))
                .map(cf -> buildFactorModel(cf, factorMap.get(cf.getFactorId())))
                .collect(Collectors.toList());


        //do -> dto
        RuleConditionModel ruleConditionModel = RuleConditionModel.builder()
                .id(ruleConditionDO.getId())
                .ruleConditionName(ruleConditionDO.getRuleConditionName())
                .action(ruleConditionDO.getAction())
                .productCode(ruleConditionDO.getProductCode())
                .ruleConditionMvel(ruleConditionDO.getRuleConditionMvel())
                .factorList(sortedFactors)
                .build();

        ConditionExecutor conditionExecutor = new ConditionExecutor();

        ConditionExecutorContext conditionExecutorContext = new ConditionExecutorContext();
        conditionExecutorContext.setRuleConditionModel(ruleConditionModel);


        Map<String, Object> msg = new HashMap<>();
        msg.put("billId", 2333L);

        //将事件参数设置到执行上下文中
        conditionExecutorContext.getParamMap().putAll(msg);

        boolean res = conditionExecutor.executor(conditionExecutorContext);
        System.out.println(res);
    }


    private static FactorModel buildFactorModel(ConditionFactorDO cf, FactorDO factorDO) {
       return  FactorModel.builder()
               //condition'
               .preProcessorExp(cf.getPreProcessorExp())
               .postProcessorExp(cf.getPostProcessorExp())
               .factorAliasName(cf.getFactorAliasName())
               .conditionId(cf.getConditionId())
               .priority(cf.getPriority())
               //factor'
               .queryClass(factorDO.getQueryClass())
               .inputParameterList(buildFactoryParamModelModelList(factorDO.getInputParameters(), cf.getInputParametersParser()))
               .dataSourceType(factorDO.getDataSourceType())
               .dataType(factorDO.getDataType())
               .productCode(factorDO.getProductCode())
               .build();
    }

    private static List<FactorInputParamConfigModel> buildFactoryParamModelModelList(String inputParameterConfig,
                                                                                     String inputParameterParseConfig) {
        List<FactorInputParamConfigModel> factorParams =
                JSON.parseArray(inputParameterConfig, FactorInputParamConfigModel.class);

        if (StringUtils.isEmpty(inputParameterParseConfig)) {
            return factorParams.stream()
                    .map(factorParam -> buildFactoryParamModelModel(factorParam, null))
                    .collect(Collectors.toList());
        }

        List<FactorInputParamConfigModel> factorParseParams =
                JSON.parseArray(inputParameterParseConfig, FactorInputParamConfigModel.class);
        Map<String, FactorInputParamConfigModel> factorParamParseMap = factorParseParams.stream()
                .collect(Collectors.toMap(FactorInputParamConfigModel::getParamName, f -> f));

        return factorParams.stream()
                .map(factorParam -> buildFactoryParamModelModel(factorParam, factorParamParseMap.get(factorParam.getParamName())))
                .collect(Collectors.toList());
    }

    private static FactorInputParamConfigModel buildFactoryParamModelModel(FactorInputParamConfigModel factorParam,
                                                                           FactorInputParamConfigModel factorParamParseConfig) {
        return FactorInputParamConfigModel.builder()
                .paramName(factorParam.getParamName())
                .paramType(factorParam.getParamType())
                .parseExpression(factorParamParseConfig != null ? factorParamParseConfig.getParseExpression() : null)
                .build();
    }
}
