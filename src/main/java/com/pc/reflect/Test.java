package com.pc.reflect;

import java.lang.reflect.Field;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        Value value = new Value();
        value.setValue(10);

        //通过反射获取私有域的值
        Field field = value.getClass().getDeclaredField("value");
        field.setAccessible(true);
        System.out.println(field.get(value));


        //甚至可以修改private final域
        Field field1 = value.getClass().getDeclaredField("finalValue");
        field1.setAccessible(true);
        field1.set(value,99);
        System.out.println(field1.get(value));


    }


}

/**
 *
 */
class Value {
    private int value;
    private final int finalValue = 11;

    public void setValue(int value) {
        this.value = value;
    }
}
