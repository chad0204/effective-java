package com.pc.reflect;

import com.pc.reflect.A;

class C implements A {

    @Override
    public void f() {
        System.out.println("f");
    }

    void g() {//包级
        System.out.println("g");
    }

    protected void m() {//子类级
        System.out.println("m");
    }

    private void n() {//私有
        System.out.println("n");
    }
}

public  class HiddenC {
    public static A makeA() {
        return new C();
    }
}


