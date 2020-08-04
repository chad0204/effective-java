package com.pc.inherit;

/**
 * TODO
 *
 * @author pengchao
 * @date 10:50 2020-08-03
 */
public class Test {

    public static void main(String[] args) {

        GrandFather person = new SonA();

        ((SonA) person).homework();
        System.out.println(person.say());


        GrandFather person1 = new Father();

        System.out.println(person1.say());




    }
}
