package com.pc;

public interface MyInterface {

    void f();


//    default boolean equals(Object o) {
//        return true;
//    }
//
//    default int hashCode() {
//        return 1;
//    }

    default void print() {
        System.out.println("I'm default method");
    }



}
