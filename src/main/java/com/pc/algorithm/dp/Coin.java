package com.pc.algorithm.dp;

import java.util.Arrays;

/**
 * 几种面值的硬币,凑出金额amount,最少需要几枚
 *
 *
 *  amount = 0, n = 0
 *  amount < 0, n = -1
 *
 *  f(amount) = min{f(amount-c1) + 1,f(amount-c2) + 1,f(amount-c3) + 1,...}
 *
 * @author pengchao
 * @date 10:24 2020-12-24
 */
public class Coin {


    public static void main(String[] args) {

        int[] coins = {1,2,5};

        System.out.println(coinChange(coins, 6));
        System.out.println(dp2(coins, 6));


    }



    public static int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        //保存，f(1) f(2)...f(amount)和f(amount)的结果
        int[] member = new int[amount];

        return dp(coins, amount, member);
    }


    /**
     * 自顶向下
     * @param coins 硬币面值
     * @param amount 金额
     * @paran member 备忘录，暂存amount种结果
     * @return
     */
    public static int dp(int[] coins,int amount,int[] member) {
        if(amount==0) {
            return 0;
        }
        if(amount<0) {
            return -1;
        }
//        if(member[amount-1] != 0) {
//            return member[amount-1];
//        }

        //求最小值 ，初始化一个最大值
        int min = Integer.MAX_VALUE;
        //选面值
        for(int coin : coins) {
            int res = dp(coins,amount-coin,member);
            if(res==-1) {
                continue;//分配不了的状态不考虑
            }
            //res>=0 ，表示能分配,==0正好分完，>0表示分完还有剩余的钱，继续循环
            if(res >=0 && res < min) {
                min = res+1;
            }
        }
//        member[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;

        return min;
//        return  member[amount - 1];
    }

    /**
     * 自下而上  f(i) = f(i-coin) + 1
     * @param coins
     * @param amount
     * @return
     */
    public static int dp2(int[] coins,int amount) {

        //dp 数组的定义：当目标金额为 i 时，至少需要 dp[i] 枚硬币凑出(都是1元面值)。（初始化为amount + 1 就相当于初始化为正无穷，便于后续取最小值）
        int max = amount + 1;
        //从0到amount,总共需要amount + 1个位置
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);

        dp[0] = 0;
        // 外层 for 循环在遍历所有状态f(1) f(2) f(3)...的所有取值，⚠️f(0)已知
        for (int i = 1; i <= amount; i++) {
            // 内层 for 循环在求所有选择的最小值
            for (int coin : coins) {
                //面值比余额还大，跳过
                if(coin > i) {
                    continue;
                }
                dp[i] = Math.min(dp[i],dp[i-coin]+1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }



}