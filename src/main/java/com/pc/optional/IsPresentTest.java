package com.pc.optional;

import java.util.Optional;

/**
 * 测试get和isPresent
 *     将对象从Optional中取出的方法和判断对象是否为null的方法
 *
 * @author dongxie
 * @date 10:21 2019-12-26
 */
public class IsPresentTest {

    public static void main(String[] args) {
        Model model = new Model("name",11);
        model = null;

        Optional<Model> optionalModel = Optional.ofNullable(model);


    }
}
