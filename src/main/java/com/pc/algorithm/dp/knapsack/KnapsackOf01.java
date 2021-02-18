package com.pc.algorithm.dp.knapsack;

/**
 * 01背包问题
 *
 * i表示物品编号 w[i] 重量 v[i] 价值
 * j表示背包承载重量
 *
 * dp[i][j] = max(dp[i-1][j-w[i-1]]+v[i],dp[i-1][j])
 *
 *
 * i表示物品的编号 v[i] 体积 w[i] 重量
 * j表示背包的体积
 *
 * dp[i][j] = max(dp[i-1][j-v[i-1]]+w[i],dp[i-1][j])
 *
 *
 *
 * @author pengchao
 * @date 21:46 2021-02-10
 */
public class KnapsackOf01 {

    /**
     * vw[i][0] 体积
     * vw[i][1] 重量
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
}
