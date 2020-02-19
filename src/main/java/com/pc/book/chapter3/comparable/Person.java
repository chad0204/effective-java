package com.pc.book.chapter3.comparable;

import java.util.Comparator;

/**
 *
 * 两种实现值比较的方式
 * @author dongxie
 * @date 16:20 2019-12-30
 */
public class Person implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return 0;
    }

    public static void main(String[] args) {


        Comparator<Person> comparator = (o1, o2) -> 0;

    }

}
