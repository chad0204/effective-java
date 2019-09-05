package com.pc.reflect;

/**
 * 向下转型
 *
 * @author pengchao
 * @date 17:32 2019-09-03
 */
public interface A {
    void f();
}

class B implements A {

    @Override
    public void f() {
        System.out.println("f");
    }

    public void g() {
        System.out.println("g");
    }

    public static void main(String[] args) {
        A a = new B();

        a.f();
//        a.g();
        ((B) a).g();
    }
}


