package com.pc.lianlian.mevl.demo.queryclass;

import com.pc.lianlian.mevl.demo.FactorResult;
import com.pc.something.User;
import lombok.Data;

import java.util.Map;

/**
 *
 * @author pengchao
 * @since 2022/8/17 20:32
 */
public class UserService {


    public FactorResult query(Map<String, Object> param) {
        User user = new User();
        user.setName("特朗普");
        user.setAge(17);
        user.setMoney(1000000L);
        System.out.println(param);
        return FactorResult.builder()
                .value(user)
                .code("SUCCESS")
                .build();
    }

    @Data
    public static class User {
        private String name;
        private Integer age;
        private Long money;
    }
}
