package com.pc.concurrent.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author dongxie
 * @date 09:27 2020-03-27
 */
public class TestChm {

    public static void main(String[] args) {
        ConcurrentHashMap<String,Integer> map1 = new ConcurrentHashMap<>(16,0.9f);

        //chm不能调整密度，永远都是size达到sizeCtl=n-(n >>> 2)开始扩容,，不像hashmap是比较阈值=n*loadFactor来扩容(n是数组长度)
        /*
         *
         *  chm不能调整密度，hashMap可以调整密度
         *
         *  chm初始化时，会将sizeCtl计算为2的幂，然后在第一次put元素时，初始化长度为sizeCtl的数组，并将sizeCtl计算为数组长度的3/4，
         *  后面只要size>sizeCtl,进行扩容，数组长度和sizeCtl都翻倍。
         *
         *  hm初始化时，会将threshold计算为2的幂，然后在第一次put元素时，初始化长度为threshold的数组，并将threshold计算为数组长度*loadFactor，
         *  后面只要size>threshold,进行扩容，数组长度和sizeCtl都翻倍。
         *
         *
         */
        PConcurrentHashMap<String,Integer> map = new PConcurrentHashMap<>();

//        map.putAll(new PConcurrentHashMap<>());



        for(int i=10;i<100;i++) {
            map.put(i+"",i);
        }




//        for(int j=0;j<100;j++) {

            new Thread(() -> {
                for(int i=0;i<10000;i++) {
                    if(i==15) {
                        System.out.println();
                    }
                    if(i==100) {
                        System.out.println();
                    }
                    map.put(i+"",i);
                }
            }).start();

//        }



        System.out.println();

        /**
         *  table.length  collection.size
         *  16              12
         *  32              24
         *  64              48
         *
         */

        int n=16;
       int sizeCtl = (n << 1) - (n >>> 1);
       int size = n*16;
        System.out.println(sizeCtl);



    }
}
