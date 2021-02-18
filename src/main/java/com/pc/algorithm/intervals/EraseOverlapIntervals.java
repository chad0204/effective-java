package com.pc.algorithm.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 435. 无重叠区间
 * 452. 用最少数量的箭引爆气球
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
     *
     *  统计不重叠的区间
     *  按照end排序，下一个区间的start大于等于end，表示不重叠，计数多少个不重叠，并更新上一个end
     *
     *  统计重叠的区间
     *  按照end排序，下一个区间的start小于end，表示重叠，计数重叠数，否则记录更新上一个end
     *
     *  注意：区间边界触碰，不算重叠
     *
     *
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
