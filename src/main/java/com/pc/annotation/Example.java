package com.pc.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Example {

    @ExceptionTest(ArithmeticException.class)
    public void m1() {
        int i = 0;
        i = i / i;
    }

    @ExceptionTest(ArithmeticException.class)
    public void m2() {
        int[] i = new int[0];
        int a = i[1];
    }

    @ExceptionTest(ArithmeticException.class)
    public void m3() {

    }


    public static void main(String[] args) {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Example.class;
        for (Method method : testClass.getDeclaredMethods()) {
            if(method.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                try {
                    method.invoke(new Example(),null);
                    System.out.println("failed no exception:"+method.getName());
                } catch (InvocationTargetException e) {
                    Throwable throwable = e.getCause();

                    Class<? extends Throwable> exeType =
                            method.getAnnotation(ExceptionTest.class).value();
                    if(exeType.isInstance(throwable)) {
                        passed++;
                    } else {
                        System.out.println("failed "+method.getName()
                                +",exeType "+exeType.getName()+",throwable "+throwable);
                    }

                } catch (Exception e) {
                    System.out.println(method.getName()+":"+e.getMessage());
                }
            }
        }

        System.out.println("pass: "+passed);
        System.out.println("failed: "+(tests-passed));

        System.out.println("");

    }



}
