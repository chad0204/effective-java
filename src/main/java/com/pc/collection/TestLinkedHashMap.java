package com.pc.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU
 *
 * @author dongxie
 * @date 10:58 2020-01-09
 */
public class TestLinkedHashMap {

    private String name;

    private static final int MAX_SIZE = 10;

    //这里初始是一个anonymous，作为缓存
    private static final Map<String, TestLinkedHashMap> cache = new LinkedHashMap<String, TestLinkedHashMap>() {
        protected boolean removeEldestEntry(Map.Entry<String, TestLinkedHashMap> var1) {
            return this.size() > MAX_SIZE;
        }
    };


    private TestLinkedHashMap(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        for (int i = 0 ; i < 15; i++) {
            cache.put(i+"",new TestLinkedHashMap(i+""));
        }

        System.out.println(cache);


    }
}
