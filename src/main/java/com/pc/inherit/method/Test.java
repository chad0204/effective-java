package com.pc.inherit.method;

/**
 * 如果子类没有该方法，则向上调用父类。
 * 如果父类没有该方法，则无法调用。
 * 如果都有，则实例化哪个类就调用该类的方法
 *
 *
 *
 *
 *
 * @author pengchao
 * @date 10:50 2020-08-03
 */
public class Test {

    public static void main(String[] args) {

        GrandFather person = new SonA();



        ((SonA) person).buildTcRequest();

        ((SonA) person).homework();
        System.out.println(person.say());


        GrandFather person1 = new Father();

        //调用的是子类的方法
        System.out.println("--"+person1.say());




        //初始化父类，会不会调用子类方法
        Father father = new Father();
        System.out.println(father.testF2S());//答案是不会



    }
}
