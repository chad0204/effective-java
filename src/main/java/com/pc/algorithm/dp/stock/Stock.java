package com.pc.algorithm.dp.stock;

/**
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 *
 *
 * n为天数
 * K为交易数
 * s为rest情况下持有股票和不持有股票
 * for(0<=i<n) {
 *     for(0<=k<K) {
 *         for(s :0,1) {
 *             dp[i][k][s] = max(buy,sell,rest)
 *         }
 *     }
 * }
 *
 *
 * 动态转移方程：
 *
 *  我们要求的最终结果就是dp[n - 1][K][0] 天数用完，交易次数用完，[0]表示不持有股票
 *
 *  第i天没有持有股票
 *  dp[i][k][0] = max(dp[i-1][k][0],dp[i-1][k][1]+price[i])
 *                max(昨天就没持有今天rest继续持有, 昨天持有今天sell)
 *
 *  第i天持有股票
 *  dp[i][k][1] = max(dp[i-1][k][1],dp[i-1][k-1][0]-price[i])
 *                max(昨天就持有今天rest继续持有, 昨天没持有今天buy)
 *
 *
 * base case:
 *
 * dp[-1][k][0] = 0
 * 解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
 * dp[-1][k][1] = -infinity
 * 解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
 * dp[i][0][0] = 0
 * 解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
 * dp[i][0][1] = -infinity
 * 解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
 *
 *
 *
 *
 * 状态转移方程：
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 *
 * base case：
 * dp[-1][k][0] = dp[i][0][0] = 0
 * dp[-1][k][1] = dp[i][0][1] = -infinity
 *
 *
 *
 *
 *
 * @author pengchao
 * @date 17:30 2021-01-07
 */
public class Stock {


    public static void main(String[] args) {
//        System.out.println(maxProfit(new int[]{7,1,5,3,6,4}));
        System.out.println(maxProfit1(new int[]{7,1,5,3,6,4}));
    }


    /**
     *  只能交易一次
     *
     *  最终结果是dp[n-1][k][0]
     *
     *  只能交易一次，k=1,简化发现与k无关，可以剔除k
     *  dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     *  dp[i][1][1] = max(dp[i-1][1][1],dp[i-1][0][0]-prices[i]) = max(dp[i-1][1][1],-prices[i])
     *
     *  剔除k
     *  dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     *  dp[i][1] = max(dp[i-1][1],-prices[i])
     *
     *
     *  base case:
     *  已知
     *  dp[-1][0] = 0
     *  dp[-1][1] = -infinity
     *  当 i=0时
     *  dp[0][0] = max(dp[-1][0],dp[-1][1]+prices[i]) = max(0,-infinity) = 0
     *  dp[0][1] = max(dp[-1][1],-prices[i]) = -prices[i]
     *
     *
     *
     * @author pengchao
     * @date 17:30 2021-01-07
     */
    public static int maxProfit(int[] prices) {

        if(prices.length==0) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            //当i =0 时，不合法，特殊处理
            if (i -1 == -1) {
                dp[i][0] = 0;
                System.out.println("dp["+i+"][0]:"+dp[i][0]);
                // 解释：
                //   dp[i][0]
                // = max(dp[-1][0], dp[-1][1] + prices[i])
                // = max(0, -infinity + prices[i]) = 0
                dp[i][1] = -prices[i];
                System.out.println("dp["+i+"][1]:"+dp[i][1]);
                //解释：
                //   dp[i][1]
                // = max(dp[-1][1], dp[-1][0] - prices[i])
                // = max(-infinity, 0 - prices[i])
                // = -prices[i]
                continue;
            }

            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            System.out.println("dp["+i+"][0]:"+dp[i][0]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
            System.out.println("dp["+i+"][1]:"+dp[i][1]);
        }
        return dp[n - 1][0];
    }


    /**
     *  可以无限交易
     *
     *  最终结果是dp[n-1][k][0]
     *
     *  可以无限交易，k = k+1 = k-1 ，剔除k
     *  dp[i][k][0] = max(dp[i-1][k][0],dp[i-1][k][1]+price[i])
     *  dp[i][k][1] = max(dp[i-1][k][1],dp[i-1][k-1][0]-price[i])
     *
     *
     *
     *  剔除k
     *  dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     *  dp[i][1] = max(dp[i-1][1],dp[i-1][0]-prices[i])
     *
     *
     *  base case:
     *  已知
     *  dp[-1][0] = 0 未开始
     *  dp[-1][1] = -infinity 不可能
     *  当 i=0时
     *  dp[0][0] = max(dp[-1][0],dp[-1][1]+prices[0]) = max(0,-infinity) = 0
     *  dp[0][1] = max(dp[-1][1],dp[-1][0]-prices[0]) = max(-infinity,-prices[0]) = -prices[0] = -7
     *
     *  dp[1][0] = max(dp[0][0],dp[0][1]+prices[1]) = max(0,-infinity) = 0
     *  dp[1][1] = max(dp[0][1],dp[0][0]-prices[1]) = max(-7,-1) = -1
     *  ...
     *
     *
     * @author pengchao
     * @date 17:30 2021-01-07
     */
    public static int maxProfit1(int[] prices) {
        if(prices.length==0) {
            return 0;
        }

        int n = prices.length;
        int[][] dp = new int[n][2];

        for(int i=0;i<n;i++) {
            if(i==0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }

        return dp[n-1][0];
    }


}
