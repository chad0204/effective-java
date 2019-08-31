package com.pc.enumpackage;


import java.util.Random;

enum CartoonCharacter implements Generator<CartoonCharacter> {
    LUFEI,SUOLONG,SANZHI,NAMEI;

    private Random rand = new Random(47);

    @Override
    public CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}

public class EnumImplementation {

    public static void main(String[] args) {
        CartoonCharacter cartoonCharacter = CartoonCharacter.LUFEI;
        for (int i=0; i<10; i++) {
            printNext(cartoonCharacter);
        }


    }

    private static <T> void printNext(Generator<T> cartoonCharacter) {
        System.out.println(cartoonCharacter.next()+",");
    }



}
