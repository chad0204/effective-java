package com.pc.algorithm.datastructure.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146. LRU 缓存机制
 *
 * @author pengchao
 * @date 18:54 2021-01-20
 */
public class LRUCacheForLHM extends LinkedHashMap<Integer,Integer>{

    public static void main(String[] args) {

        LRUCacheForLHM cache = new LRUCacheForLHM(10);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);

        //改变顺序
        cache.get(1);
        cache.get(3);


    }



    public int capacity;

    public LRUCacheForLHM(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    /**
     * 钩子函数，在lhm执行完put后如果长度超过这个值，则会删除头节点
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return this.size()>capacity;
    }


    /**
     * get方法之后，会将节点移动到队尾
     * @param key
     * @return
     */
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    /**
     * put方法，将节点插入队尾，然后通过钩子函数删除队头
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        super.put(key, value);
    }

}
