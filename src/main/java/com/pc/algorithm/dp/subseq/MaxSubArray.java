package com.pc.algorithm.dp.subseq;

/**
 * 53. 最大子序和
 *
 *  f(i) = max{f(i-1)+nums[i],nums[i]}
 *
 *  这题的特殊之处，求出的动态规划结果，并不是最后一个值是结果，只能得出不同的状态对应的结果，需要取出最大值。
 *
 * @author pengchao
 * @date 10:50 2021-02-02
 */
public class MaxSubArray {


    public static void main(String[] args) {
        System.out.println(maxSubArrayDp(new int[]{-2,-1,3,-1,-2,-3,3,-4,-5}));
    }


    /**
     * 递归
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {

        int[] mem = new int[nums.length];
        maxSubArray(nums,nums.length-1,mem);

        return maxValue(mem);
    }

    public static int maxSubArray(int[] nums,int len,int[] mem) {
        if(len==0) {
            mem[len]=nums[len];
            return nums[len];
        }

        if(mem[len]!=0) {
            return mem[len];
        }

        mem[len] = Math.max(maxSubArray(nums,len-1,mem)+nums[len],nums[len]);
        return  mem[len];
    }


    /**
     * 自下而上 动态规划
     * @param nums
     * @return
     */
    public static int maxSubArrayDp(int[] nums) {
        //结果不是dp[len]，所以不用dp数组长度不用+1
        int[] dp = new int[nums.length];

        for(int i=0;i<nums.length;i++) {
            if(i==0) {
                dp[0] = nums[0];
                continue;
            }
            //nums[i]为正数，加入到之前的连续子序列中，nums[i]为负数，另起炉灶创建新的连续子序列
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
        }


        return maxValue(dp);
    }

    private static int maxValue(int[] dp) {
        int res = Integer.MIN_VALUE;
        for(int i=0;i<dp.length;i++) {
            res = Math.max(res,dp[i]);
        }
        return res;
    }


}
