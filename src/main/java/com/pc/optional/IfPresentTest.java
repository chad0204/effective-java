package com.pc.optional;

import java.util.Optional;

/**
 * 判断非空后进行操作，取代下面写法
 *      if(obj!=null) {
 *          doSomething
 *      }
 *
 * @author dongxie
 * @date 10:30 2019-12-26
 */
public class IfPresentTest {

    public static void main(String[] args) {
        Model model = new Model("name",11);

        Optional.ofNullable(model).ifPresent(x -> {
            System.out.println(x.getName());
        });


    }
}
