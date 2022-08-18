package com.pc.lianlian.mevl.demo.queryclass;

import com.pc.lianlian.mevl.demo.FactorResult;
import com.pc.something.User;
import lombok.Data;

import java.util.Map;

/**
 * TODO
 *
 * @author pengchao
 * @since 2022/8/18 15:52
 */
public class ProductService {

    public FactorResult query(Map<String, Object> param) {

        Product product = new Product();
        product.setName("特斯拉");
        product.setPrice(300000L);
        product.setUser((User) param.get("user"));

        System.out.println(param);
        return FactorResult.builder()
                .value(product)
                .code("SUCCESS")
                .build();
    }

    @Data
    public static class Product {
        private String name;
        private Long price;
        private User user;
    }
}
