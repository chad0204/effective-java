package com.pc.reflect;


interface HasBatteries {}
interface Waterproof {}
interface Shoots {}

class Toy {
    Toy() {}
    Toy(int i) {}
}

class FancyToy extends Toy implements HasBatteries,Waterproof,Shoots {
    FancyToy() {
        super(1);
    }
}

/**
 * Class API
 */

public class ToyTest {

    static void printInfo(Class<?> clazz) {
        System.out.println("ClassName:"+clazz.getName());
        System.out.println("IsInterface:"+clazz.isInterface());
        System.out.println("SimpleName:"+clazz.getSimpleName());
        System.out.println("Canonical name:"+clazz.getCanonicalName());
        System.out.println("++++++++++++++++++++++++++++++++++++");

    }

    public static void main(String[] args) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("com.pc.reflect.FancyToy");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        clazz = FancyToy.class;//使用字面量

        printInfo(clazz);

        for(Class<?> cc : clazz.getInterfaces()) {//获取接口
            printInfo(cc);
        }

        Class<?> up = clazz.getSuperclass();//获取父类
        Object obj = null;

        try {
            obj = up.newInstance();//创建对象
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        printInfo(obj.getClass());


    }
}
