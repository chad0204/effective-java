package com.pc.lianlian.mevl.demo.queryclass;

import com.pc.lianlian.mevl.demo.FactorResult;
import com.pc.something.User;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author pengchao
 * @since 2022/8/18 15:52
 */
public class ProductService extends QueryService<ProductService.Product>  {


    @Override
    public Product doQuery(Map<String, Object> params) {
        Product product = new Product();
        product.setName("特斯拉");
        product.setPrice(300000L);
        //强制转换，所以需要
        product.setUser((User) params.get("user"));

        System.out.println(params);

        return product;
    }

    @Data
    public static class Product {
        private String name;
        private Long price;
        private User user;
    }
}
