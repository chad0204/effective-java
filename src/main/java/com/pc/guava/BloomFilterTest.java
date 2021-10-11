package com.pc.guava;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

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



    private static final BloomFilter<String> dealIdBloomFilter = BloomFilter.create(new Funnel<String>() {

        private static final long serialVersionUID = 1L;

        @Override
        public void funnel(String arg0, PrimitiveSink arg1) {

            arg1.putString(arg0, Charsets.UTF_8);
        }

    }, 1024*1024*32,0.5d);//expectedInsertions：位数组长度, fpp：误判率



    public static void main(String[] args) {


        for(int i=0;i<100;i++) {
            int l = 1 << i;
            System.out.println("位置:"+ l);
        }

        for(long i=0;i<100;i++) {
            long l = 1L << i;
            System.out.println("位置:"+ l);
        }






    }

    /**
     *
     * 1.通过value除64计算下标index，得到value属于数组中的long,words[index]
     * 2.通过1L << value，计算2的value次方的二进制位中1的位置
     * 3.通过words[index] 与 2中的结果进行 累计或运算，将所有1的位置叠加。
     *
     * 如果index大于当前数组长度，需要扩容
     * 1L << value ,
     *
     */
    public static void cal(int bitIndex) {
        // 将value 转成 2的value次方 。 超过64结果循环(如果是int 32,byte是8)
        // 1 2 4 8 16 32 ... 2^62 2^63 1 2 4 8 ...
        long[] arrLong = new long[2];

        for(int i=0;i<100;i++) {
            long l = 1L << i;
            System.out.println("位置:"+ l);
        }


        /**
         *
         *  0000 0000 |
         *  0000 0001 |
         *  0000 0010 |
         *  0000 0100 |
         *  0000 1000 |
         *  =
         *  0000 1111
         *
         *
         *
         */
        long word = 0;
        for(long i=0;i<100;i++) {
            System.out.println(word |= i);
        }





        long[] words = new long[2];


        //计算bitIndex位置long数组中的第一个long
        int wordIndex_ = bitIndex >> 6; //( bitIndex / 2^6)
        int wordIndex = bitIndex/ (int)Math.pow(2,6);

        if (wordIndex != wordIndex_) {
            System.err.println("????");
        }

        //计算出bitIndex的位置 转成二进制后1的位置
        /*
         *  000001
         *  000010
         *  000100
         *  001000
         */
        long l = 1L << bitIndex;


        //累计或运算，将1合并起来
        words[1] |= (1L << bitIndex);

        /*
         * words[0] 存(准确的说应该是标记)0～63
         * words[1] 存64～127
         * ...
         *
         * 比如words[0] = 36
         * 比如words[0] = 0000 ... 0010 0100
         * 表示2^2 和 2^5有值 ，表示2和5存在
         *
         *
         * 如果存65
         * 那么需要在words[1]中映射
         * 1L << 64 = 1
         * 1L << 65 = 2
         *
         * words[0] = 0000 ... 0010 0100 = 36
         * words[1] = 0000 ... 0000 0010 = 2
         *
         *
         */
        BitSet set = new BitSet();
        set.set(2);
        set.set(5);
        set.set(64);

        System.out.println();

        // 一个long有64bit，也就能存64个数




    }


    public static void testBitSet() {
        //创建一个能存64位即2^64次方个元素的bitSet
        BitSet bitSet = new BitSet(1);

        //放入10亿元素
        for (int i=0;i<1000000000;i++) {
            bitSet.set(i, true);
        }
        System.out.println(bitSet.get(9));

        for ( int i = 0; i < bitSet.size(); i++ ){
            System.out.println(bitSet.get(i));//true表示有，false表示无
        }

        //hashMap存储的直接是int类型的值，同时还有个负载因子的问题，导致内存占用率高
        HashMap<Integer,Boolean> hashMap = new HashMap<>();
        for (int i=0;i<1000000000;i++) {
            hashMap.put(i, true);//OutOfMemoryError
        }
        System.out.println(hashMap.get(9));
    }

    public static void testBloom() {
        //bloom过滤器就是bit数组+多个hash算法，使用bit数组提高空间利用率，使用hash函数提高查询速度。适用于查询数据是否在集合中，缺点是有一定的误识别率和删除困难


        for (int i=0;i<10000000;i++) {
            dealIdBloomFilter.put("aa"+i);
        }

        System.out.println("BloomFilter:"+dealIdBloomFilter.mightContain("aa1"));


        List<Integer> errorList = new ArrayList<Integer>(1000);
        //故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = 10000000 + 10000; i < 10000000 + 20000; i++) {
            if (dealIdBloomFilter.mightContain("aa"+i)) {
                errorList.add(i);
            }
        }

        System.out.println("错误个数:"+errorList.size());
        System.out.println("错误率:"+errorList.size()/10000d);
    }
}
