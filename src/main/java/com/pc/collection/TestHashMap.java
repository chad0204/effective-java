package com.pc.collection;

import java.util.HashMap;

/**
 *
 *
 * @author dongxie
 * @date 16:09 2020-03-16
 */
public class TestHashMap {

    public static void main(String[] args) {

        //问：给定初始容量为16，负载因子为10，第一次扩容时机是不是当size > threshold（160）时
        //答：不是，因为早就出现转树条件，但因为数组长度不够64，所以已经size=160之前已经扩容两次。后面才开始按照++size > threshold比较结果扩容

        //按理说数组长度=16，当size>16*10时才会扩容，但由于hash冲突剧烈链表长度已经超过8要开始转红黑树(size=89)，但是由于数组长度不够64，所以在转之前进行扩容到32。
        // 扩容之后元素被稀释又可以继续增加节点，当出现大于8的链表后(size=99)，又开始扩容到64。稀释后又继续添加元素，添加到（size=244）,出现链表大于8，此时数组长度已
        // 经是64，所以转红黑树。后面扩容的触发条件是比较size和阈值的大小。


        HashMap<String,Integer> map = new HashMap<>(16, 10f);//loadFactor决定扩容时机，

        //size 桶数组  阈值
        // 1    16     160//初始扩容
        // 89   32     320//转树失败扩容
        // 99   64     640//转树失败扩容
        // 641  128   1280//size>threshold
        // 1281  256   2560//size>threshold
        //...


        map.put("0",0);
        for (int i =1;i<10000;i++) {
            map.put(i+"",i);
        }
        map.put("aa",1);
        map.put("bb",1);


        System.out.println(map);

    }
}
