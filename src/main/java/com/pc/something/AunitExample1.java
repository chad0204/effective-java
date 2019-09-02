package com.pc.something;


import org.junit.Test;

public class AunitExample1 {

    public String methodOne() {
        return "This is methodOne";
    }

    public int methodTwo() {
        System.out.println("This is methodTwo");
        return 2;
    }

    @Test
    public void methodOneTest() {
        System.out.println("1111");
//        return methodOne().equals("This is methodOne");
    }

    @Test
    public void m2() {
//        return methodTwo()==2;
    }

    @Test
    public void m3() {
//        return true;
    }

    @Test
    public void failureTest() {
        System.out.println(11111);
    }

    public void anotherDisappointment() {

    }





}
