package com.pc.something;

public class Demo {

    private String name;


    public Demo() {
        System.out.println("dubbo contract");
    }


    public void f() {

    }

    static {
        System.out.println("static block");
    }


    static class StaticInner {
        public StaticInner() {
            System.out.println("StaticInner contract");
        }
    }

     class Inner {
        private String name;

        public Inner() {
            System.out.println("Inner contract");
        }

        public void f1() {
            f();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
