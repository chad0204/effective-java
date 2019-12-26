package com.pc.optional;

import java.util.Optional;

/**
 * 满足某个条件才操作
 *      if(obj!=null && "a".equals(obj.getName)) {
 *          doSomething
 *      }
 *
 * @author dongxie
 * @date 10:36 2019-12-26
 */
public class FilterTest {

    public static void main(String[] args) {
        Model model = new Model("name",101);
//        model = null;

        Optional.ofNullable(model).filter(x->"name".equals(x.getName()) && x.getId()>100).ifPresent(x-> {
            System.out.println(x.getId());
        });


    }
}
