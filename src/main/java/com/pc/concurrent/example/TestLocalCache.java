package com.pc.concurrent.example;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author pengchao
 * @date 17:05 2020-06-11
 */
public class TestLocalCache {

    private static LocalCache<Long,UserInfo> cache = new LocalCache<>(new Function<Long, UserInfo>() {
        @Override
        public UserInfo apply(Long routeId) {
            //数据库查询
            UserInfo userInfo = new UserInfo(routeId,routeId+"aa");
            return userInfo;
        }
    });

    public static void main(String[] args) {

        for(int i =0;i<100;i++) {
            UserInfo userInfo = cache.getCache((long) i);
            System.out.println(userInfo.toString());
        }

        //等30分钟更新
        UserInfo userInfo = cache.getCache(1L);


        System.out.println();



    }

}

class LocalCache<K,V> {

    private Map<K,CacheNode<V>> cache = new LinkedHashMap<>();

    private Function<K,V> function;

    public LocalCache(Function<K, V> function) {
        this.function = function;
    }

    public boolean putCache(K k, V v) {
        CacheNode<V> node = new CacheNode<>();
        node.setV(v);
        node.setLocalDateTime(LocalDateTime.now());
        cache.put(k,node);
        return true;
    }

    public V getCache(K k) {
        CacheNode<V> node = cache.get(k);
        if(node==null || node.getLocalDateTime().minusMinutes(30).isBefore(LocalDateTime.now())) {
            cache.remove(k);
            V v = function.apply(k);
            this.putCache(k,function.apply(k));
            return v;
        }
        return node.getV();
    }
}

class CacheNode<V> {
    private LocalDateTime localDateTime;
    private V v;

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public V getV() {
        return v;
    }

    public void setV(V v) {
        this.v = v;
    }

}

class UserInfo {
    private Long id;

    private String userName;

    public UserInfo(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}

