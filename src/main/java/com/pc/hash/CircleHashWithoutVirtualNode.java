package com.pc.hash;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 *
 *  带虚拟节点的一致性hash
 *  当服务节点过少时，可能会出现分配不均匀的情况，使用hash
 * @author pengchao
 * @since 11:01 2019-11-18
 */
public class CircleHashWithoutVirtualNode {

    private static final Logger logger = LoggerFactory.getLogger(CircleHash.class);

    //待加入hash环的服务器列表
    private static String[] servers = { "192.168.0.0:111", "192.168.0.1:111","192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111" };


    //真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
    private static List<String> realNodes = new LinkedList<String>();

    //虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<Integer, String>();

    //虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点,虚拟节点越多，每个真实节点分配的资源越接近
    private static final int VIRTUAL_NODES = 10000;

    //统计各节点获取的访问量
    private static Map<String, List<String>> countMap = new HashMap<>();

    static{
        //先把原始的服务器添加到真实结点列表中
        for(int i=0; i<servers.length; i++) {
            realNodes.add(servers[i]);
            countMap.put(servers[i],new LinkedList<>());
        }

        //再添加虚拟节点，遍历LinkedList使用foreach循环效率会比较高
        for (String str : realNodes){
            for(int i=0; i<VIRTUAL_NODES; i++){
                String virtualNodeName = str + "&&VN" + String.valueOf(i);
                int hash = hash(virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
        System.out.println();
    }


    //得到应当路由到的结点
    private static String getServer(String key){
        //得到该key的hash值
        int hash = hash(key);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode;
        if(subMap.isEmpty()){
            //如果没有比该key的hash值大的，则从第一个node开始
            Integer i = virtualNodes.firstKey();
            //返回对应的服务器
            virtualNode = virtualNodes.get(i);
        }else{
            //第一个Key就是顺时针过去离node最近的那个结点
            Integer i = subMap.firstKey();
            //返回对应的服务器
            virtualNode = subMap.get(i);
        }
        //virtualNode虚拟节点名称要截取一下
        if(virtualNode!=null && !virtualNode.isEmpty()){
            String server = virtualNode.substring(0, virtualNode.indexOf("&&"));
            if(countMap.containsKey(server)) {
                countMap.get(server).add(key);
            }
            return server;
        }
        return null;
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
