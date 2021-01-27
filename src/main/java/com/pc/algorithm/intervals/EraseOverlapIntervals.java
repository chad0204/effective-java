package com.pc.algorithm.intervals;

import java.util.Arrays;

/**
 * 435. 无重叠区间
 *
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * @author pengchao
 * @date 19:02 2021-01-26
 */
public class EraseOverlapIntervals {

    public static void main(String[] args) {

        int[][] a = new int[][]{{1,8},{3,4},{7,9},{9,10}};

        System.out.println(eraseOverlapIntervals(a));




    }


    public static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length ==0) {
            return 0;
        }
        return intervals.length - intervalSchedule(intervals);
    }


    /**
     * 不相交的区间的最多个数
     * @param intvs
     * @return
     */
    public static int intervalSchedule(int[][] intvs) {
        if(intvs.length==0) {
            return 0;
        }

        //按照end排序
        Arrays.sort(intvs,(o1,o2)->{
            return o1[1]-o2[1];
        });

        // 至少有一个区间不相交（只留一个区间，其他区间全部删掉的情况）
        int count = 1;

        int x_end = intvs[0][1];//第一个x的end
        //可以从x自己开始,反正自己的start不可能比end大
        for(int[] intv: intvs) {
            int start = intv[0];
            //只有start都比上一个end大的才算无重叠
            if(start>=x_end) {
                count++;
                x_end = intv[1];
            }
        }
        return count;
    }
}
