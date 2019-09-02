package com.pc.reflect;


import java.util.Random;

class Initable {
    static final int staticFinal = 47;
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable");
    }
}

class Initable2 {


    static int  staticNonFinal = 147;
    static int staticFianl = ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {
    static int  staticNonFinal = 74;
    static int staticFinal = ClassInitialization.rand.nextInt(1000);
    static {
        System.out.println("Initializing Initable3");
    }
}


public class ClassInitialization {
    static  Random rand = new Random(47);

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> initable = Initable.class;//类字面量创建Class对象的引用时不会初始化类
        System.out.println("After creating Initable ref");

        System.out.println(Initable.staticFinal);//使用类的常量不会初始化
        System.out.println("-------------------");

        System.out.println(Initable.staticFinal2);//使用可变的常量会初始化
        System.out.println("-------------------");

        System.out.println(Initable2.staticNonFinal);//使用静态变量会初始化
        System.out.println("-------------------");

        Class<?> initable3 = Class.forName("com.pc.reflect.Initable3");//使用Class.forName方法会初始化
        System.out.println("After creating Initable3 ref");

        System.out.println(Initable3.staticNonFinal);//已经初始化过




    }



}
