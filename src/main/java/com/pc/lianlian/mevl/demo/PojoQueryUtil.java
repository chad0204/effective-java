package com.pc.lianlian.mevl.demo;

import com.google.common.collect.Lists;
import com.pc.lianlian.mevl.demo.entity.FactorDO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author pengchao
 * @since 2022/8/19 16:12
 */
public class PojoQueryUtil {

    /**
     * 查询因子id查询因子详情
     * @param factorIds
     * @return
     */
    public static Map<Long, FactorDO> getFactorMap(List<Long> factorIds) {
        //封装因子详情
        FactorDO product = new FactorDO();
        product.setId(1001L);
        product.setDataType("com.pc.lianlian.mevl.demo.queryclass.ProductService.Product");
        product.setFactorName("product");
        product.setQueryClass("productService");
        product.setDataSourceType(FactorTypeEnum.ASSEMBLE.getCode());
        product.setInputParameters(Lists.newArrayList("user", "productId"));
        FactorDO user = new FactorDO();
        user.setId(1002L);
        product.setDataType("com.pc.lianlian.mevl.demo.queryclass.UserService.User");
        user.setFactorName("user");
        user.setDataSourceType(FactorTypeEnum.ASSEMBLE.getCode());
        user.setInputParameters(Lists.newArrayList("userId"));
        Map<Long, FactorDO> map = new HashMap<>();
        map.put(1001L, product);
        map.put(2002L, user);
        return map;
    }
}
