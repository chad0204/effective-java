package com.pc.reflect.demo;

import com.pc.reflect.A;
import com.pc.reflect.HiddenC;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HiddenImplementation {
    public static void main(String[] args) throws Exception {
        A a = HiddenC.makeA();
        a.f();
        System.out.println(a.getClass().getName());
        
//        C c = (C) a;//类是包级无法访问

        //但是可以通过反射，啥访问级别的都可以访问到
        callHiddenMethod(a,"g");//包级
        callHiddenMethod(a,"m");//子类级
        callHiddenMethod(a,"n");//私有级


    }


    static void callHiddenMethod(Object a,String methodName) throws Exception {
        Method g = a.getClass().getDeclaredMethod(methodName);//获取的申明的所有方法.getMethod获取的是公共方法
        g.setAccessible(true);
        g.invoke(a);
    }
}