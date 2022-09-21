package com.pc.lianlian.mevl.demo.queryclass;

import cn.hutool.core.collection.CollectionUtil;
import com.pc.lianlian.mevl.demo.model.FactorModel;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author pengchao
 * @since 2022/8/17 20:32
 */
public class UserService extends QueryService<UserService.User> {


    @Override
    public User doQuery(Map<String, Object> inputParamMap, String activity) {

        //模拟查询
        User user = new User();
        user.setName("特朗普");
        user.setAge(19);
        user.setMoney(1000000L);
        user.setId((Long)inputParamMap.get("userId"));
        System.out.println("查询成功: " + user);
        return user;
    }

    @Data
    public static class User {
        private Long id;
        private String name;
        private Integer age;
        private Long money;
    }
}
