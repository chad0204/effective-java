package com.pc.something;

import java.util.concurrent.TimeUnit;

class A implements MyInterface {


    @Override
    public void f() {
        print();
    }

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        a.f();

        TimeUnit.SECONDS.sleep(1000);

    }


}



