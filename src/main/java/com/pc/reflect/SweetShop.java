package com.pc.reflect;


class Candy {
    static {
        System.out.println("Loading Candy");
    }
}

class Gum {
    static {
        System.out.println("Loading Gum");
    }
}

class Cookie {
    static {
        System.out.println("Loading Cookie");
    }
}

/**
 * 证明类只在第一次被使用时才加载到JVM中
 */

public class SweetShop {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        new Candy();
        System.out.println("---After new Candy--");

//        try {
//            Class.forName("com.pc.reflect.Gum");
//        } catch (ClassNotFoundException e) {
//            System.out.println("couldn't found Gum ");
//        }

        Class<?> clazz = Gum.class;//使用字面量不会初始化Gum
//        Gum gum = (Gum) clazz.newInstance();//第一次使用时才会

        System.out.println("---After reflect class Gum");

        new Cookie();

        System.out.println("---After new Cookie");

    }

}
