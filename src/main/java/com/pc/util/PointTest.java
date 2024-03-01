package com.pc.util;

import org.openjdk.jol.vm.VM;

/**
 *
 * @author pengchao
 * @since 2022/9/30 15:11
 */
public class PointTest {

    public static void main(String[] args) {
        //值传递
        int i = 100;
        changeInt(i);
        System.out.println(i);

        //值传递
        String str = "original";
        changeStr(str);
        System.out.println(str);

        //----------------自定义类型-------------------

        //引用传递
        Person p = new Person();
        p.name = "original";
        System.out.println("p address: " + VM.current().addressOf(p) + ", p value:" + p.hashCode());
        changeName(p);
        System.out.println(p.name);


        //传递的引用也是值, 指向新的引用则不会变化当前引用
        Person p2 = new Person();
        p2.name = "original";
        changeNameV2(p2);
        System.out.println(p2.name);

    }

    private static void changeInt(int i) {
        i = 999;
    }

    private static void changeStr(String str) {
        str = "changed";
    }

    private static void changeName(Person person) {
        System.out.println("p address: " + VM.current().addressOf(person) + ", p value:" + person.hashCode());
        person.name = "changed";
    }

    private static void changeNameV2(Person person) {
        Person p = new Person();
        p.name = "changed";
        person = p;
        person.name = p.name + "2333";
    }



}

class Person {
    String name;
}
