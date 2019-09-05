package com.pc.something;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author pengchao
 * @date 10:38 2019-09-04
 */
public class SetList {

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<>();
        List<Integer> list = new ArrayList<>();

        for(int i = -3; i<3; i++) {
            set.add(i);
            list.add(i);
        }

        for(int i = 0; i<3; i++) {
            set.remove(i);//只有remove(Object o)
//            list.remove(i);//remove(int index)
            list.remove(Integer.valueOf(i));//remove(Object o)
        }

        //      删掉的元素   剩下
        // 0    -3          -2 -1 0 1 2
        // 1    -1          -2 0 1 2
        // 2    1           -2 0 2

        System.out.println(set+" "+list);

    }
}
