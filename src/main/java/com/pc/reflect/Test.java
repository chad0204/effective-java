package com.pc.reflect;

public class Test {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(SweetShop.class==Class.forName("com.pc.reflect.SweetShop"));

        System.out.println(boolean.class==Boolean.TYPE);
        System.out.println(boolean.class==Boolean.class);


        Class<?> clazz = Integer.TYPE;

        Class<?> clazz1 = Integer.class;

        Class<?> clazz2 = int.class;

        System.out.println();





    }


}
