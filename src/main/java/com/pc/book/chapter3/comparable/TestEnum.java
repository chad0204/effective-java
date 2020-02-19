package com.pc.book.chapter3.comparable;

/**
 * 所有的枚举都实现了comparable接口
 *     反射搞不出来啊
 *
 * @author dongxie
 * @date 15:07 2019-12-30
 */
public class TestEnum {

    public static void main(String[] args) {

        //通过反射获取类实现的所有接口
        Class<?> clazz = Demo.class;

        clazz.getGenericInterfaces();



        Class<?> integerClass = Integer.class;

        integerClass.getGenericInterfaces();



    }

}

enum Demo {

}
