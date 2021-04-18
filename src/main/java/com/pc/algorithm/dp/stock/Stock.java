package com.pc.algorithm.dp.stock;

/**
 *
 * 以 188. 买卖股票的最佳时机 IV 为例
 *
 * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
 *
 * 买卖是交替执行的，先买后卖
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
 * K为交易数，买卖为一次，卖k不变，买k-1
 * s为rest情况下持有股票和不持有股票的状态
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
 * 结果
 * dp[n-1][k][0] ，n从0开始遍历
 *
 *
 * @author pengchao
 * @date 17:30 2021-01-07
 */
public class Stock {


    public static void main(String[] args) {
//        System.out.println(maxProfit_1(new int[]{7,1,5,3,6,4}));

//        System.out.println(maxProfit_infinity(new int[]{7,1,5,3,6,4}));

//        System.out.println(maxProfit_2(new int[]{3,3,5,0,0,3,1,4}));
//        System.out.println(maxProfit_2(new int[]{1,2,3,4,5}));
//        System.out.println(maxProfit_2(new int[]{7,6,4,3,1}));

//        System.out.println(maxProfit_k(2,new int[]{2,4,1}));
//        System.out.println(maxProfit_k(2,new int[]{3,2,6,5,0,3}));


        System.out.println(maxProfit_cool(new int[]{1,2,3,0,2}));




    }


    /**
     *  只能交易一次
     *
     *  最终结果是dp[n-1][k][0]
     *  dp[-1][k][0] = dp[i][0][0] = 0
     *  dp[-1][k][1] = dp[i][0][1] = -infinity
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
    public static int maxProfit_1(int[] prices) {

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
     *  可以无限交易，k = k+1 = k-1 = +infinity ，剔除k
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
    public static int maxProfit_infinity(int[] prices) {
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

    //不用数组的解法
    public static int maxProfit_infinity_1(int[] prices) {
        if(prices.length==0) {
            return 0;
        }
        int n = prices.length;

        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;


        for(int i=1;i<=n;i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1,temp -prices[i]);
        }

        return dp_i_0;
    }

    /**
     *  两次交易
     *
     *  结果dp[n-1][K][0]
     *  dp[-1][k][0] = 0          天数还没开始，未持有，没买
     *  dp[-1][k][1] = -infinity  天数还没开始，持有，不可能
     *  dp[i][0][0] = 0           交易0笔，未持有
     *  dp[i][0][] =  -infinity   交易0笔，持有 不可能
     *
     *
     *
     *  dp[i][k][0] = max(dp[i-1][k][0],dp[i-1][k][1]+prices[i])
     *  dp[i][k][1] = max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i])
     *
     *  base case
     *  dp[-1][k][0] = 0;
     *  dp[-1][k][1] = -infinity;
     *
     *  dp[i][0][0] = 0;
     *  dp[i][0][1] = -infinity;
     *
     *
     *  当k=0
     *  dp[i][k][0] = 0;
     *  dp[i][k][1] = Integer.MIN_VALUE;
     *
     *  当i=0
     *  dp[0][k][0] = max(dp[-1][k][0],dp[-1][k][1]+prices[0]) = max(0,-infinity) = 0
     *  dp[0][k][1] = max(dp[-1][k][1],dp[-1][k-1][0]-prices[0])
     *              = max(-infinity,dp[-1][k-1][0]-prices[0])
     *              = dp[-1][k-1][0]-prices[0] = -prices[0];
     *
     *
     * @author pengchao
     * @date 17:30 2021-01-07
     */
    public static int maxProfit_2(int[] prices) {
        int n = prices.length;
        int K = 2;
        int[][][] dp = new int[n][K+1][2];

        //先遍历天数还是先遍历交易数都一样
        for(int k=1;k<=K;k++) {

            for(int i=0;i<n;i++) {

                if(i==0) {
                    dp[i][k][0] = 0;
                    System.out.println("dp["+i+"]["+k+"][0]:"+dp[i][k][0]);
                    dp[i][k][1] = -prices[i];
                    System.out.println("dp["+i+"]["+k+"][0]:"+dp[i][k][0]);
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]);
                System.out.println("dp["+i+"]["+k+"][0]:"+dp[i][k][0]);
                dp[i][k][1] = Math.max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]);
                System.out.println("dp["+i+"]["+k+"][0]:"+dp[i][k][0]);

            }
        }

        return dp[n-1][K][0];
    }


    /**
     *
     * 188. 买卖股票的最佳时机 IV
     *
     * 状态转移方程
     * dp[i][k][0] = max(dp[i-1][k][0],dp[i-1][k][1]+prices[i])
     * dp[i][k][1] = max(dp[i-1][k][0],dp[i-1][k-1][1]-prices[i])
     *
     * base case
     * dp[-1][k][0] = 0
     * dp[-1][k][1] = -infinity
     * dp[i][0][0] = 0
     * dp[i][0][1] = -infinity
     *
     *
     * dp[0][j][0] = max(dp[-1][j][0],dp[-1][j][1]+prices[i]) = max(0,-) = 0
     * dp[0][j][1] = max(dp[-1][j][1],dp[-1][j-1][0]-prices[i]) = max() = -prices[i]
     *
     *
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit_k(int k, int[] prices) {

        if(prices.length==0) {
            return 0;
        }

        int n = prices.length;

        int[][][] dp = new int[n][k+1][2];

        for(int i=0;i<n;i++) {
            for(int j=1;j<=k;j++) {
                if(i==0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
            }

        }

        return dp[n-1][k][0];
    }


    /**
     *
     * 状态转移方程
     *  卖完冻结一天才能买
     *  k无限
     *
     *  dp[i][k][0] = max(dp[i-1][k][0],dp[i-1][k][1]+prices[i])  i天未持有 =  前一天未持有，前一天持有，第i天卖了
     *  dp[i][k][1] = max(dp[i-1][k][1],dp[i-2][k-1][0]-prices[i]) i天持有 =  前一天持有 ，前一天没持有，第i天买的
     *  注意：第i天买，由于不能只能买卖交替，那么卖之后肯定一直没持有，如果i-1天卖的，那么今天不能买，只能是i-2天卖的
     *
     *  k= +infinity,简化
     *  dp[i][0] = max(dp[i-1][0],dp[i-1][1]+prices[i])
     *  dp[i][1] = max(dp[i-1][1],dp[i-2][0]-prices[i])
     *
     *  base case
     *  dp[-1][0] = 0
     *  dp[-1][1] = -infinity
     *  dp[-2][0] = 0
     *
     *  if i==0
     *   dp[i][0] =dp[0][0] = max(dp[-1][0],dp[-1][1]+prices[0]) = 0
     *   dp[i][1] =dp[0][1] = max(dp[-1][1],dp[-2][0]-prices[0]) = -prices[0]
     *
     *  if i==1
     *   dp[i][0] = max(dp[0][0],dp[0][1]+prices[1]) = max(0,-prices[0]+prices[1])
     *   dp[i][1] = max(dp[0][1],dp[-1][0]-prices[1]) = max(-prices[0],-prices[1])
     *
     *
     *
     * @param prices
     * @return
     */
    public static int maxProfit_cool(int[] prices) {
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
            if(i==1) {
                dp[i][0] = Math.max(0,prices[i]-prices[i-1]);
                dp[i][1] = Math.max(-prices[i-1],-prices[i]);
                continue;
            }

            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-2][0]-prices[i]);
        }
        return dp[n-1][0];
    }

}
