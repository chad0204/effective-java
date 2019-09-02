package com.pc.something;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {




    }

    public static Set union(Set set1,Set set2) {
        Set result = new HashSet(set1);
        result.addAll(set2);
        return result;
    }


    public static <E> Set union1(Set<E> set1,Set<E> set2) {
        Set<E> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }


}

