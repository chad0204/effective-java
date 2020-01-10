package com.pc.book.chapter9;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author dongxie
 * @date 16:25 2020-01-09
 */
public class Test {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("aa","bb","cc");

        for(int i = 0; i < list.size(); i++) {

        }

        for (Iterator<String> i = list.iterator(); i.hasNext();) {
            String str = i.next();
            System.out.println(str);
        }

        for (String str : list) {
            System.out.println(str);
        }



    }
}
