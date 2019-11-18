package com.pc.hash;

import org.jetbrains.annotations.Contract;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *  HashMap的hash算法
 * @author pengchao
 * @since 21:36 2019-11-15
 */
public class Test {

    public static void main(String[] args) {
        int n = 16;

        Set<Integer> hashes = new HashSet<>();
        for (int i=0;i<1000;i++) {
            Integer value = (n - 1) & hash(""+i);
            hashes.add(value);
        }

        System.out.println(hashes.size());//多少个值

        Set<Integer> deliveries = new HashSet<>();
        for (int i=0;i<1000;i++) {
            Integer value = delivery(""+i,n);
            deliveries.add(value);
        }

        System.out.println(deliveries.size());

    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private static int delivery(Object key, Integer n) {
        return Math.abs(key.hashCode()%n);
    }


    //

}
