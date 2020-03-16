package com.pc.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU
 *
 * @author dongxie
 * @date 10:58 2020-01-09
 */
public class TestLRU {

    private String name;

    private static final int MAX_SIZE = 10;

    //这里初始是一个anonymous，作为缓存
    private static final Map<String, TestLRU> cache = new LinkedHashMap<String, TestLRU>() {
        protected boolean removeEldestEntry(Map.Entry<String, TestLRU> var1) {
            return this.size() > MAX_SIZE;
        }
    };


    private TestLRU(String name) {
        this.name = name;
    }

    public static void main(String[] args) {

        for (int i = 0 ; i < 15; i++) {
            cache.put(i+"",new TestLRU(i+""));
        }

        System.out.println(cache);


    }
}
