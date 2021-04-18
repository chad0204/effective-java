package com.pc.concurrent.collection;

import java.util.Comparator;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 测试跳表
 *
 * @author pengchao
 * @date 15:05 2021-03-17
 */
public class TestSkipList {


    public static void main(String[] args) {

        ConcurrentSkipListMap<String,Integer> map = new ConcurrentSkipListMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });
        map.put("a",1);


        map.get("a");

    }
}
