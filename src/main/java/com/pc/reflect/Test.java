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

    }


}

class Value {
    private int value;



    public void setValue(int value) {
        this.value = value;
    }
}
