package com.pc.algorithm.datastructure.lru;

import java.util.LinkedHashMap;

/**
 *
 *
 * @author pengchao
 * @date 18:54 2021-01-20
 */
public class Test {

    public static void main(String[] args) {

        LRUCacheForLHM cache = new LRUCacheForLHM(10);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);

        cache.get(4);

        System.out.println(cache);




    }


}

