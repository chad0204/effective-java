package com.pc.book.chapter4.inherit;

/**
 * 带有default的接口A
 *
 * @author dongxie
 * @date 14:14 2020-01-06
 */
public interface InterFaceWithDefaultB {

    void methodB();

    default void defaultMethod() {
        System.out.println("b-defaultMethod");
    }

    default void defaultB() {
        System.out.println("defaultB");
    }

    //不能为Object方法提供缺省方法
//    default int hashCode() {
//        return 1;
//    }

}
