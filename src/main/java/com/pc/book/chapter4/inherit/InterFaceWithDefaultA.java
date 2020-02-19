package com.pc.book.chapter4.inherit;

/**
 * 带有default的接口A
 *
 * @author dongxie
 * @date 14:13 2020-01-06
 */
public interface InterFaceWithDefaultA {

     void methodA();

     default void defaultMethod() {
         System.out.println("a-defaultMethod");
     }

    default void defaultA() {
        System.out.println("defaultA");
    }

}
