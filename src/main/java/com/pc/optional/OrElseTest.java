package com.pc.optional;

import java.util.Optional;

/**
 *  当对象为null时，设置一个默认值继续运行或者抛出异常
 *      String name = obj!=null ? obj.getName() : "Unknown"
 *
 *      or
 *
 *      if(obj.getName() == null) {
 *          throw new RuntimeException();
 *      }
 *
 * @author dongxie
 * @date 10:42 2019-12-26
 */
public class OrElseTest {

    public static void main(String[] args) {
        Model model = new Model("name",101);
//        model = null;
        String name = Optional.ofNullable(model).orElse(new Model("unKnown",102)).getName();
        System.out.println(name);


        String name1 = Optional.ofNullable(model).orElseThrow(NullPointerException::new).getName();
        System.out.println(name1);
    }
}
