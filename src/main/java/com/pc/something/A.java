package com.pc.something;

class A implements MyInterface {


    @Override
    public void f() {
        print();
    }

    public static void main(String[] args) {
        A a = new A();
        a.f();
    }


}



