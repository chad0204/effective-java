package com.pc.hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 *
 * 一致性hash
 * @author pengchao
 * @since 09:34 2019-11-18
 */
public class CircleHash {

    private static final Logger logger = LoggerFactory.getLogger(CircleHash.class);

    //待加入hash环的服务器列表
    private static String[] servers = { "192.168.0.0:111", "192.168.0.1:111","192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111" };

    //key表示服务器的hash值，value表示服务器
    private static SortedMap<Integer,String> sortedMap = new TreeMap<>();

    //统计各节点获取的访问量
    private static Map<String, List<String>> countMap = new HashMap<>();

    //程序初始化，将所有的服务器放入sortedMap中
    static {
        for(int i=0; i<servers.length; i++) {
            int hash = hash(servers[i]);
            sortedMap.put(hash,servers[i]);
            countMap.put(servers[i],new LinkedList<>());
            logger.info("服务[{}]j加入集群,hash=[{}]",servers[i],hash(servers[i]));
        }
    }

    //得到相应应当路由到的节点
    private static String getServer(String key) {
        //得到大于等于该key的hash值的所有map
        SortedMap<Integer,String> subMap = sortedMap.tailMap(hash(key));
        String server;
        if(subMap.isEmpty()) {
            //如果没有比当前key的hash值大的节点，则从第一个node开始,返回节点服务器。
            server = sortedMap.get(sortedMap.firstKey());
        } else {
            //比当前key的hash大的第一个节点就是顺时针过去离node最近的那个节点,做为服务器返回。
            server = subMap.get(subMap.firstKey());
        }

        if(countMap.containsKey(server)) {
            countMap.get(server).add(key);
        }
        return server;
    }

    //使用FNV1_32_HASH算法
    private static int hash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    public static void main(String[] args) {
        for(int i=0;i<100000;i++) {
            logger.info("数据的hash值= [{}],被路由到节点: [{}]",hash(i+""),getServer(i+""));
        }

        System.out.println(countMap);
    }


}
