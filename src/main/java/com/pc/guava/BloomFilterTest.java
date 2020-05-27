package com.pc.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import java.util.BitSet;

/**
 * 布隆过滤器
 *
 *
 *   bit只有1和0两个值
 *
 *   byte=1字节=8bit = 11111111=2^8=-128~127
 *   int =4字节=32bit = 32个1 = 2^32
 *
 *
 *
 *   大数据去重和过滤
 *
 *
 * @author pengchao
 * @date 18:16 2020-05-26
 */
public class BloomFilterTest {

    private int a;


    private static final BloomFilter<String> dealIdBloomFilter = BloomFilter.create(new Funnel<String>() {

        private static final long serialVersionUID = 1L;

        @Override
        public void funnel(String arg0, PrimitiveSink arg1) {

            arg1.putString(arg0, Charsets.UTF_8);
        }

    }, 1024*1024*32,0.1d);//expectedInsertions：位数组长度, fpp：误判率



    public static void main(String[] args) {

        //底层是long数组，最少是64位
        BitSet set1 = new BitSet(1);
        System.out.println(set1.size());
        BitSet set2 = new BitSet(20);
        System.out.println(set2.size());
        BitSet set3 = new BitSet(65);
        System.out.println(set3.size());


        //创建一个能存64位即2^64次方个元素的bitSet
        BitSet bitSet = new BitSet(1);


        //放入10亿元素
//        for (int i=0;i<1000000000;i++) {
//            bitSet.set(i, true);
//        }
//        System.out.println(bitSet.get(9));
//
//        for ( int i = 0; i < bitSet.size(); i++ ){
//            System.out.println(bitSet.get(i));//true表示有，false表示无
//        }

        //hashMap存储的直接是int类型的值，同时还有个负载因子的问题，导致内存占用率高
//        HashMap<Integer,Boolean> hashMap = new HashMap<>();
//        for (int i=0;i<1000000000;i++) {
//            hashMap.put(i, true);//OutOfMemoryError
//        }
//        System.out.println(hashMap.get(9));


        //bloom过滤器就是bit数组+多个hash算法，使用bit数组提高空间利用率，使用hash函数提高查询速度。适用于查询数据是否在集合中，缺点是有一定的误识别率和删除困难


        for (int i=0;i<10000000;i++) {
            dealIdBloomFilter.put("aa"+1);
        }

        System.out.println("BloomFilter:"+dealIdBloomFilter.mightContain("aa1"));


    }
}
