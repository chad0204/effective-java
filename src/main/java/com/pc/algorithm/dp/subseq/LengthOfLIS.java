package com.pc.algorithm.dp.subseq;

import java.util.Arrays;

/**
 * 300. 最长递增子序列
 *
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 *
 * @author pengchao
 * @date 14:52 2021-02-02
 */
public class LengthOfLIS {

    public static void main(String[] args) {
        lengthOfLISDp(new int[]{10,9,2,5,3,7,101,18});
    }


    /**
     *
     * f(i) = max{f(j)} + 1, 0<=j<i, num[i]>num[j]
     *
     * i是当前数组下标，j是之前某一个比i小的数组下标
     *
     * dp[i] 表示以 nums[i] '这个数结尾'的最长递增子序列的长度
     *
     * 得出dp数组的值，然后取出最小值
     *
     * @param nums
     * @return
     */
    public static int lengthOfLISDp(int[] nums) {
        //结果不是dp[len]，所以不用dp数组长度不用+1
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);

        for(int i=1;i<nums.length;i++) {

            //找出所有索引小于i,且值比nums[i]小的子序列长度，然后取最长的一个
            for(int j=0;j<i;j++) {
                if(nums[i]>nums[j]) {
                    //dp[i]初始为1，逐渐替换赋值为dp[j]+1
                    dp[i] = Math.max(dp[j]+1,dp[i]);
                }

            }
        }

        int res = 1;
        for (int i : dp) {
            res = Math.max(res, i);
        }

        return res;
    }
}