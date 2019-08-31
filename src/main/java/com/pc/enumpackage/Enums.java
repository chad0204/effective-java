package com.pc.enumpackage;

import java.util.Random;

public class Enums {

    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<?> c) {
        return (T) random(c.getEnumConstants());
    }

    private static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
