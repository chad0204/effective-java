package com.pc.optional;

import java.util.Optional;

/**
 * 和Stream#map一样，将一个对象转换成另一个对象
 *
 * @author dongxie
 * @date 10:52 2019-12-26
 */
public class MapTest {
    public static void main(String[] args) {
        //将Optional<Model>转换成Optional<String>，如果model为null,什么都不做
        Model model = new Model("name",101);
        model = null;
        Optional<String> stringOptional = Optional.ofNullable(model).map(Model::getName);

        stringOptional.ifPresent(System.out::println);


    }
}

