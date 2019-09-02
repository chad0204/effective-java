package com.pc.something;


import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

enum Explore {
    HERE,THRER
}

public class Reflection {

    public static Set<String> analyze(Class<?> enumClass) {
        System.out.println("-------Analyzing "+ enumClass + " -----------");
        System.out.println("Interfaces");
        for (Type t : enumClass.getGenericInterfaces()) {
            System.out.println(t);
        }

        System.out.println("Base: "+enumClass.getSuperclass());

        System.out.println("Methods: ");

        Set<String> methods = new TreeSet<>();
        for (Method method :enumClass.getMethods()) {
            methods.add(method.getName());

            if(method.getName().equals("values")) {
                System.out.println(method.getReturnType());
                System.out.println(method.getGenericReturnType());
            }

        }
        System.out.println(methods);

        return methods;

    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);

        Set<String> enumMethods = analyze(Enum.class);


        System.out.println(exploreMethods.containsAll(enumMethods));

        System.out.println("=======");

        exploreMethods.removeAll(enumMethods);

        System.out.println(exploreMethods);




    }
}
