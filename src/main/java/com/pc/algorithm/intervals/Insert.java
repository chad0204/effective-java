package com.pc.algorithm.intervals;

import com.pc.something.Arr;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 57. 插入区间
 *
 * @author pengchao
 * @date 10:36 2021-01-27
 */
public class Insert {

    public static void main(String[] args) {
        int[][] ints = new int[][]{{1,3},{6,9}};
        int[] add = new int[]{2,5};

        System.out.println(insert(ints,add));

    }


    /**
     * 先将区间加到组中，重新排序，然后在进行区间合并
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        intervals = Arrays.copyOf(intervals, intervals.length+1);

        intervals[intervals.length-1] = newInterval;

        Arrays.sort(intervals,(a,b)->{
            return a[0]==b[0] ? (b[1]-a[1]) : (a[0]-b[0]);
        });

        List<int[]> res = new ArrayList<>();

        int left = intervals[0][0];
        int right = intervals[0][1];

        for(int i=1;i<intervals.length;i++) {
            int[] interval = intervals[i];
            //相交，更新
            if(right >= interval[0] && right <= interval[1]) {
                right = interval[1];
            }
            //分离，添加
            else if(right< interval[0]) {
                res.add(new int[]{left,right});
                left = interval[0];
                right = interval[1];
            }
            //覆盖，不变
        }
        res.add(new int[]{left,right});

        return res.toArray(new int[res.size()][]);
    }

    /**
     * 划定边界
     *
     * 1.左边
     * 2.右边
     * 3.重合
     *
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert2(int[][] intervals, int[] newInterval) {

        List<int[]> res = new ArrayList<>();

        int left = newInterval[0];
        int right = newInterval[1];

        for(int i=0;i<intervals.length;i++) {
            int[] intvl = intervals[i];
            //左边的
            if(intvl[1] < left) {
                res.add(intvl);
                continue;
            }
            //右边的
            if(intvl[0] > right) {
                res.add(intvl);
                continue;
            }
            //重叠
            left = Math.min(left,intvl[0]);
            right = Math.max(right,intvl[1]);

        }
        res.add(new int[]{left,right});

        int[][] array = res.toArray(new int[res.size()][]);
        Arrays.sort(array,(a,b)->{
            return a[0]-b[0];
        });

        return array;
    }


}
