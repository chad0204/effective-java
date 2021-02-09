package com.pc.algorithm.dp.subseq;

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
        lengthOfLIS(new int[]{10,9,2,5,3,7,101,18});
    }


    /**
     *
     * f(i) = max{f(j)} + 1, 0<=j<i, num[i]>num[j]
     *
     * i是当前数组下标，j是之前某一个比i小的数组下标
     *
     * 得出dp数组的值，然后取出最小值
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        //结果不是dp[len]，所以不用dp数组长度不用+1
        int[] dp = new int[nums.length];

        for(int i=0;i<nums.length;i++) {
            if(i==0) {
                dp[i] = 1;
                continue;
            }

            dp[i] = 1;//最小长度是1
            for(int j=0;j<i;j++) {
                if(nums[i]>nums[j]) {
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


    public static int lengthOfLIS_II(int[] nums) {

        int[][] dp = new int[nums.length][nums.length];



        return 0;
    }
}
