package com.pc.book.chapter7;

import java.util.*;

/**
 *  匿名类中的this指的是匿名类对象，lambda指的是外围类实例
 *
 * @author dongxie
 * @date 10:01 2020-01-09
 */
public class Test {

    private Long id;

    public static void main(String[] args) {
        Test test = new Test();
        test.f();


        List<String> list = Arrays.asList("aa","bb","cc","dd");


        list.forEach(e->{
            if(e.equals("cc")) {
                return;//相当于break
            }
            System.out.println(e);
        });







    }


    public void f() {
        List<String> list = Arrays.asList("a","b","c");

        list.forEach(e->{
            System.out.println(this.toString());//这里的this指的是外围类的实例（Test实例），如果是匿名类，this指的是匿名类实例。
        });

        Anonymous inner = new Anonymous() {

            private String name;

            @Override
            public void test() {
                System.out.println(this.toString());//这里的this是匿名类实例
            }
            @Override
            public String toString() {
                return "$classname{" +
                        "name='" + name + '\'' +
                        '}';
            }
        };
        inner.test();





    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                '}';
    }
}

interface Anonymous {
    void test();
}
