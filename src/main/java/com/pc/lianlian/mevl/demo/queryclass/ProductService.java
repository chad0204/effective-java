package com.pc.lianlian.mevl.demo.queryclass;


import com.google.common.collect.Lists;
import com.pc.something.User;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pengchao
 * @since 2022/8/18 15:52
 */
public class ProductService extends QueryService<List<ProductService.Product>>  {


    @Override
    public List<Product> doQuery(Map<String, Object> inputParamMap, String activity) {

        Product product1 = new Product();
        product1.setName("特斯拉");
        product1.setPrice(300000L);
        product1.setId(((List<Long>) inputParamMap.get("productIds")).get(0));

        Product product2 = new Product();
        product2.setName("iphone");
        product2.setPrice(10000L);
        product2.setId(((List<Long>) inputParamMap.get("productIds")).get(1));

        System.out.println("查询成功: " + Lists.newArrayList(product1, product2));
        return Lists.newArrayList(product1, product2);
    }

    @Data
    public static class Product {
        private Long id;
        private String name;
        private Long price;
    }
}
