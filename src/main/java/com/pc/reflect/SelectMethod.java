package com.pc.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过参数过滤方法
 *
 * @author pengchao
 * @date 16:00 2019-09-03
 */

class MethodSelector implements InvocationHandler {

    private Object proxied;

    public MethodSelector(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //可以使用方法名，参数等选择需要增强的方法
        if(method.getName().equals("interesting")) {
            System.out.println("Proxy detected the interesting method");
        }
        return method.invoke(proxied,args);
    }
}


interface SomeMethods {
    void boring1();
    void boring2();
    void interesting();
}

class Implemention implements SomeMethods {

    @Override
    public void boring1() {
        System.out.println("boring1");
    }

    @Override
    public void boring2() {
        System.out.println("boring2");
    }

    @Override
    public void interesting() {
        System.out.println("interesting");
    }
}

public class SelectMethod {
    public static void main(String[] args) {
        SomeMethods proxy = (SomeMethods) Proxy.newProxyInstance(SomeMethods.class.getClassLoader(),
                new Class[]{SomeMethods.class},new MethodSelector(new Implemention()));
        proxy.boring1();
        proxy.boring2();
        proxy.interesting();

    }

}
