package com.pc;

import java.util.ArrayList;
import java.util.List;

public class FilledList<T> {


    private Class<T> type;
    public FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int nElements) {
        List<T> result = new ArrayList<>();

        try {
            for(int i=0;i<nElements;i++) {
                T t = type.newInstance();
                result.add(t);
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        FilledList<CountedInteger> f1 = new FilledList<>(CountedInteger.class);
        System.out.println(f1.create(15));
    }

}

class CountedInteger {
    private static long counter;
    private final long id = counter++;


    public String toString() {
        return Long.toString(id);
    }
}
