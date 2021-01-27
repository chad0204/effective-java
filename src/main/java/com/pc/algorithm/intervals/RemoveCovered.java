package com.pc.algorithm.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1288. 删除被覆盖区间
 *
 *
 *   1 2 3 4 5 6 7 8 9
 *   1-----4
 *       3-----6
 *     2 ----------8
 *
 *
 *
 *   先排序
 *
 *   三种情况
 *
 *   覆盖
 *   相交，合并
 *   隔离，
 *
 *
 * @author pengchao
 * @date 15:20 2021-01-11
 */
public class RemoveCovered {


    public static void main(String[] args) {
        //[[1,4],[3,6],[2,8]]



        int[][] intervals = new int[][]{{1,2},{1,4},{3,4}};

        System.out.println(removeCoveredIntervals(intervals));

    }


    public static int removeCoveredIntervals(int[][] intervals) {
        // 按照起点升序排列，起点相同时降序排列
        Arrays.sort(intervals, (interval1, interval2) -> {
            return interval1[0] == interval2[0] ? (interval1[0] - interval2[0]) : (interval2[1] - interval1[1]);
        });

//        Arrays.sort(intervals,(a,b)-> a[0]!=b[0] ? (a[0]-b[0]) : (b[1]-a[1]));

        // 记录合并区间的起点和终点
        int left = intervals[0][0];
        int right = intervals[0][1];

        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];
            // 情况一，找到覆盖区间
            if (left <= intv[0] && right >= intv[1]) {
                res++;
            }
            // 情况二，找到相交区间，合并
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }
            // 情况三，完全不相交，更新起点和终点
            //上一个不可能被覆盖了，更新
            if (right < intv[0]) {
                left = intv[0];
                right = intv[1];
            }
        }

        return intervals.length - res;
    }

}
