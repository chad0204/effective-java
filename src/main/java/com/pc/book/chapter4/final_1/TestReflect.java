package com.pc.book.chapter4.final_1;

import java.lang.reflect.Field;

/**
 * 利用反射访问和修改final,private 字段
 *
 * @author pengchao
 * @date 11:10 2020-01-02
 */
public class TestReflect {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {

        //只要能拿到引用，任何属性都可以通过反射修改
        Demo demo = new Demo(10);
        demo.setValue(10);

        //通过反射获取私有域的值
        Field field = demo.getClass().getDeclaredField("value");
        field.setAccessible(true);
        System.out.println("value:"+field.get(demo));//可以访问private属性


        //甚至可以修改private final域
        Field field1 = demo.getClass().getDeclaredField("finalValue");
        field1.setAccessible(true);
        field1.set(demo,99);//可以修改private final 属性
        System.out.println("finalValue:"+field1.get(demo));


        Field field2 = demo.getClass().getDeclaredField("staticFinalValue");
        field2.setAccessible(true);
        field2.set(demo,99);//可以修改private final 属性
        System.out.println("staticFinalValue:"+field2.get(demo));



        String strValue = "我爱静静";
        Field fieldStr = strValue.getClass().getDeclaredField("value");
        fieldStr.setAccessible(true);
        fieldStr.set(strValue,fieldStr.get("是的呢"));
        System.out.println(strValue);


        //为了可变对象的安全，每次构建对象都是新的对象
        Demo demo1 = Demo.newDemo(10);





    }
}
