package com.pc.algorithm.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @author pengchao
 * @date 16:46 2021-01-11
 */
public class Merge {


    public static void main(String[] args) {
        int[][] aa = new int[][]{{15,18},{1,3},{2,6},{8,10}};
        int[][] bb = new int[][]{{1,4},{2,3}};
        System.out.println(Arrays.toString(merge(aa)));
        System.out.println(Arrays.toString(merge(bb)));
    }



    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals,(a,b)-> a[0]!=b[0] ? (a[0]-b[0]) : (b[1]-a[1]));

        List<int[]> res = new ArrayList<>();

        int left = intervals[0][0];
        int right = intervals[0][1];
        for(int i=1;i<intervals.length;i++) {
            int[] intvl = intervals[i];
            //相交
            if(right >= intvl[0] && right <= intvl[1]) {
                right = intvl[1];
            }
            //分离
            else if(right<intvl[0]) {
                res.add(new int[]{left,right});
                left = intvl[0];
                right = intvl[1];
            }
            //覆盖不用更新

        }
        res.add(new int[]{left,right});

        return res.toArray(new int[res.size()][]);
    }
}
