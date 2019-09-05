package com.pc.reflect;

import java.lang.reflect.Field;

/**
 *
 * @author pengchao
 * @date 17:13 2019-09-03
 */
public class TestPrivate {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        PrivateField privateObject = new PrivateField();

        Field fieldInt = PrivateField.class.getDeclaredField("privateInt");//获取的申明的所有域.getField获取的是公共域

//        int privateInt1 = (int) fieldInt.get(privateObject);//直接访问不行

        fieldInt.setAccessible(true);//允许访问
        int privateInt2 = (int) fieldInt.get(privateObject);



        Field fieldString = PrivateField.class.getDeclaredField("privateString");



    }
}


class PrivateField {

    private int privateInt = 10;

    private String privateString = "privateString";


}