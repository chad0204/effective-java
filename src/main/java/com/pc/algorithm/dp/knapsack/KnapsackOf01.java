package com.pc.algorithm.dp.knapsack;


import com.pc.something.Arr;
import java.util.Arrays;

/**
 *
 *
 * 01背包问题
 *
 * i-1表示物品编号 w[i-1] 重量 v[i-1] 价值
 * j-1表示背包承载重量
 *
 *
 *  子问题
 *  装不下的话，就是这个商品不装，取值还是装到上一个商品的最大价值
 *  dp[i][j] = dp[i-1][j]
 *
 *  装得下,取下面两种情况的最大值
 *  1.装，当前商品的价值+"背包减去当前商品重量后的重量后" 装到的商品的最大价值
 *  v[i-1] + dp[i-1][j-w[i-1]]
 *  2.不装，装到上一个商品的最大价值
 *  dp[i-1][j]
 *
 *  base case
 *  背包容量为0，0
 *  没有商品，0
 *
 *  w val   dp  0  1  2  3  4  5  6  7
 *
 *          0   0  0  0  0  0  0  0  0
 *
 *  1  1    1   0  1  1  1  1  1  1  1
 *
 *  4  3    2   0  1  1  4  4  5  5  5
 *
 *  5  4    3   0  1  1  4  5  6  6  9
 *
 *  7  5    4   0  1  1  4  5  7  8  9
 *
 *
 *
 *
 *
 * 「力扣」上的 0-1 背包问题：
 *
 * 「力扣」第 416 题：分割等和子集（中等）；
 * 「力扣」第 474 题：一和零（中等）；
 * 「力扣」第 494 题：目标和（中等）；
 * 「力扣」第 879 题：盈利计划（困难）；
 * 「力扣」第 1049 题：最后一块石头的重量 II（中等）；
 *
 *
 * 「力扣」上的 完全背包问题：
 * 「力扣」第 322 题：零钱兑换（中等）；
 * 「力扣」第 518 题：零钱兑换 II（中等）；
 * 「力扣」第 1449 题：数位成本和为目标值的最大数字（困难）。
 *
 *
 *

 *
 * @author pengchao
 * @date 21:46 2021-02-10
 */
public class KnapsackOf01 {


    public static void main(String[] args) {



    }



    /**
     *
     *  牛客网
     *  vw[i][0] 体积
     *  vw[i][1] 重量
     *
     * @param V
     * @param n
     * @param vw
     * @return
     */
    public int knapsack (int V, int n, int[][] vw) {
        // write code here
        int[][] dp = new int[n+1][V+1];

        for(int i=1;i<=n;i++) {

            for(int j=1;j<=V;j++) {
//                //没商品
//                if(i==0) {
//                    dp[i][j] = 0;
//                    continue;
//                }
//                //没有体积
//                if(j==0) {
//                    dp[i][j] = 0;
//                    continue;
//                }

                //这里vw用i-1，因为商品数量从1开始的
                if(j-vw[i-1][0] < 0) {
                    //装不了
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(vw[i-1][1]+dp[i-1][j-vw[i-1][0]],dp[i-1][j]);
                }
            }
        }
        return dp[n][V];

    }

    /**
     *
     *
     * 416. 分割等和子集
     *
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 注意:
     *
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * 示例 1:
     *
     * 输入: [1, 5, 11, 5]
     *
     * 输出: true
     *
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     *
     *
     * 转化成01问题，将nums求和除于2，判断数组中是否存在某些数的和正好等于该值。
     *
     *
     * 01背包问题考虑的是选取的物品的总重量不超过 背包承重
     * 该题考虑的是，选取的物品总重量 刚好等于 背包的承重
     *
     *
     * dp[i][j]就是从i个数中取，背包重量为j，能否装满
     *
     *
     *  子问题
     *  装不下，就是这个商品不装，装到上一个商品的最大价值
     *  dp[i][j] = dp[i-1][j]
     *
     *  装得下,下面两种情况一种成立，则为true
     *  1.装，如果在 i-1个商品中就已经存在一部分商品，满足他们的和等于 j-nums[i-1]
     *    dp[i-1][j-nums[i-1]]
     *  2.不装，如果在 i-1个商品中就已经存在一部分商品，满足他们的和等于 j
     *    dp[i-1][j]
     *
     *
     *
     *
     *  base case
     *  背包容量为0,肯定能装满 true
     *  没有商品，肯定装不满 false
     *
     *  if(nums[i-1]>j) {
     *      //装不下还是之前的结果
     *      dp[i][j] = dp[i-1][j];
     *  } else {
     *      //装和不装任意取
     *      dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
     *  }
     *
     *
     *
     *            dp  0  1  2  3  4  5  6  7  8     j
     *
     *  nums      0   t  f  f  f  f  f  f  f  f
     *
     * nums[0]1   1   t  t  f  f  f  f  f  f  f
     *
     * nums[1]5   2   t  t  f  f  f  t  t  f  f
     *
     * nums[2]11  3   t  t  f  f  f  t  t  f  f
     *
     * nums[3]5   4   t  t  f  f  f  t  t  f  f
     *
     *            i
     *
     *
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i=0;i<nums.length;i++) {
            sum = sum+ nums[i];
        }
        if(sum%2==1) {
            return false;
        }
        sum = sum/2;

        boolean[][] dp = new boolean[nums.length+1][sum+1];


        for(int i=0;i<=nums.length;i++) {

            for(int j=0;j<=sum;j++) {
                if(i==0) {
                    dp[i][j] = false;
                    continue;
                }
                if(j==0) {
                    dp[i][j] = true;
                    continue;
                }

                if(nums[i-1]>j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                }

            }
        }

        return dp[nums.length][sum];

    }


    /**
     * 1049. 最后一块石头的重量 II
     *
     *
     * 石头的总和/2 = sum
     * 将石头分成两堆，一一对撞，当其中一堆最接近sum的时候，对撞的结果最小
     *
     * 所以问题转成，背包承重sum，若干石头，如何使装的石头最重
     *
     *             dp 0  1  2  3  4  5  6  7  8  9
     *             0
     * stones[0]   1
     * stones[1]   2
     * stones[2]   3
     * stones[3]   4
     * stones[4]   5
     *
     *
     * dp[i][j]就是背包重量为i，从j个石头中取，能装的最大重量
     *
     * @param stones
     * @return
     */
    public static int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for(int w : stones) {
            sum = sum+w;
        }



        int halfSum = sum/2;

        int[][] dp = new int[halfSum+1][stones.length+1];

        for(int i=0;i<=halfSum;i++) {
            for(int j=0;j<=stones.length;j++) {
                if(i==0) {
                    dp[i][j] = 0;
                    continue;
                }
                if(j==0) {
                    dp[i][j] = 0;
                    continue;
                }

                if(stones[j-1]>i) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-stones[j-1]][j-1]+stones[j-1]);
                }
            }
        }

        //int otherSum = sum - dp[halfSum][stones.length]就是另一边的总和，otherSum - halfSum
        return sum - dp[halfSum][stones.length]-dp[halfSum][stones.length];


    }


    /**
     * 474. 一和零
     *
     *
     * dp[i][j][k]表示，当取到第i个元素，背包限制为j,k时,有几个元素符合
     *
     * 不装还是之前的dp[i-1][j][k]
     *
     * 装就是之前的个数+1,d[i-1][j-zero][k-one]+1
     *
     * dp[i][j][k] = Math.max(dp[i-1][j][k],d[i-1][j-zero][k-one]+1);
     *
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm(String[] strs, int m, int n) {

        int[][][] dp = new int[strs.length+1][m+1][n+1];

        for(int i=1;i<=strs.length;i++) {
            String curr = strs[i-1];
            int zero = countZero(curr);
            int one = curr.length()-zero;
            for(int j=0;j<=m;j++) {


                for(int k=0;k<=n;k++) {

                    if(zero>j || one>k) {
                        dp[i][j][k] = dp[i-1][j][k];
                    } else {
                        //求子集数量，成立就+1
                        dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-zero][k-one]+1);
                    }

                }
            }

        }
        return dp[strs.length][m][n];
    }

    public static int countZero(String str) {
        int count = 0;
        for(char c : str.toCharArray()) {
            if(c == '0') {
                count++;
            }
        }
        return count;
    }


    /**
     * 494. 目标和
     *
     * dp[ i ][ j ]表示从数组nums中 0 - i 的元素进行加减可以得到 j 的方法数量
     *
     *
     *
     * dp[i][j] = dp[i-1][j-nums[i-1]] + dp[i-1][j+nums[i-1]]
     *
     *
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {

        int sum = 0;
        for(int i=0;i<nums.length;i++) {
            sum+=nums[i];
        }
        //绝对值比数组和还大，无法得到
        if(Math.abs(S)>Math.abs(sum)) {
            return 0;
        }




        return 0;

    }

}
