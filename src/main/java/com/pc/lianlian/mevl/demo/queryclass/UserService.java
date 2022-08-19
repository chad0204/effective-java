package com.pc.lianlian.mevl.demo.queryclass;

import com.pc.lianlian.mevl.demo.FactorResult;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *
 * @author pengchao
 * @since 2022/8/17 20:32
 */
public class UserService extends QueryService<UserService.User> {


    @Override
    public User doQuery(Map<String, Object> params) {
        User user = new User();
        user.setName("特朗普");
        user.setAge(17);
        user.setMoney(1000000L);
        System.out.println(params);
        return user;
    }

    @Data
    public static class User {
        private String name;
        private Integer age;
        private Long money;
    }
}
