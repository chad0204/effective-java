package com.pc.lianlian.mevl.demo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.pc.lianlian.mevl.demo.entity.ConditionFactorDO;
import com.pc.lianlian.mevl.demo.entity.FactorDO;
import com.pc.lianlian.mevl.demo.model.FactorInputParamConfigModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pengchao
 * @since 2022/8/19 16:12
 */
public class PojoQueryUtil {

    public static List<ConditionFactorDO> getConditionFactorExt() {

        ConditionFactorDO bill = new ConditionFactorDO();
        bill.setFactorAliasName("bill");
        bill.setFactorId(1001L);
        bill.setPriority(0);

        ConditionFactorDO user = new ConditionFactorDO();
        FactorInputParamConfigModel userParam = new FactorInputParamConfigModel();
        userParam.setParamName("userId");
        userParam.setParseExpression("bill.userId");
        user.setInputParametersParser(JSON.toJSONString(Lists.newArrayList(userParam)));
        user.setFactorAliasName("user");
        user.setFactorId(1002L);
        user.setPriority(1);
        user.setPostProcessorExp("user.age >= 18");


        ConditionFactorDO product = new ConditionFactorDO();
        FactorInputParamConfigModel productParam = new FactorInputParamConfigModel();
        productParam.setParamName("productIds");
        productParam.setParseExpression("bill.productIds");
        product.setInputParametersParser(JSON.toJSONString(Lists.newArrayList(productParam)));
        product.setFactorAliasName("product");
        product.setFactorId(1003L);
        product.setPriority(2);
        product.setPostProcessorExp("foreach (name : product) { if (name.name == '特斯拉') {return true;}}");
        return Lists.newArrayList(bill, user, product);

    }

    /**
     * 查询因子id查询因子详情
     * @param factorIds
     * @return
     */
    public static Map<Long, FactorDO> getFactorMap(List<Long> factorIds) {

        FactorDO bill = new FactorDO();
        bill.setId(1001L);
        bill.setDataType("com.pc.lianlian.mevl.demo.queryclass.BillService$Bill");
        bill.setFactorName("bill");
        bill.setQueryClass("billService");
        bill.setDataSourceType(FactorTypeEnum.PROCESSED.getCode());
        FactorInputParamConfigModel billParam1 = new FactorInputParamConfigModel();
        billParam1.setParamName("billId");
        billParam1.setParamType("java.lang.Long");
        bill.setInputParameters(JSON.toJSONString(Lists.newArrayList(billParam1)));


        FactorDO user = new FactorDO();
        user.setId(1002L);
        user.setDataType("com.pc.lianlian.mevl.demo.queryclass.UserService$User");
        user.setFactorName("user");
        user.setQueryClass("userService");
        user.setDataSourceType(FactorTypeEnum.PROCESSED.getCode());
        //设置入参
        FactorInputParamConfigModel userParam1 = new FactorInputParamConfigModel();
        userParam1.setParamName("userId");
        userParam1.setParamType("java.lang.Long");
        user.setInputParameters(JSON.toJSONString(Lists.newArrayList(userParam1)));



        FactorDO product = new FactorDO();
        product.setId(1003L);
        product.setDataType("java.util.ArrayList");
        product.setFactorName("product");
        product.setQueryClass("productService");
        product.setDataSourceType(FactorTypeEnum.PROCESSED.getCode());
        //设置入参
        FactorInputParamConfigModel productParam1 = new FactorInputParamConfigModel();
        productParam1.setParamName("productIds");
        productParam1.setParamType("java.util.ArrayList");
        product.setInputParameters(JSON.toJSONString(Lists.newArrayList(productParam1)));






        Map<Long, FactorDO> map = new HashMap<>();
        map.put(1001L, bill);
        map.put(1002L, user);
        map.put(1003L, product);
        return map;
    }
}
