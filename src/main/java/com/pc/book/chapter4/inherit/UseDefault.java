package com.pc.book.chapter4.inherit;

/**
 * UseDefault实现了带有默认方法的接口A和B
 * A和B具有相同的缺省方法defaultMethod
 *
 * @author dongxie
 * @date 14:16 2020-01-06
 */
public class UseDefault implements InterFaceWithDefaultA,InterFaceWithDefaultB {
    @Override
    public void methodA() {
        defaultA();//子类直接调用接口的默认方法
    }

    @Override
    public void methodB() {
        defaultB();//子类直接调用接口的默认方法
    }


    //如果实现的接口包含相同的默认方法，实现类必须重写
    @Override
    public void defaultMethod() {
        System.out.println("impl defaultMethod");
    }

    public static void main(String[] args) {
        UseDefault useDefault = new UseDefault();
        useDefault.defaultA();
        useDefault.defaultB();
        useDefault.defaultMethod();
    }
}
