package com.pc.algorithm.dp;

/**
 * 几种面值的硬币,凑出金额amount,最少需要几枚
 *
 *
 *  amount = 0, n = 0
 *  amount < 0, n = -1
 *
 *
 *
 * @author pengchao
 * @date 10:24 2020-12-24
 */
public class Coin {


    public static void main(String[] args) {

        int[] coins = {1,2,5};

        System.out.println(coinChange(coins, 21));


    }



    public static int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }

        //保存，f(1) f(2)...f(amount)和f(amount)的结果

        //f(amount) = min{f(amount-c1) + 1,f(amount-c2) + 1,f(amount-c3) + 1,...}

        int[] member = new int[amount];

        return dp(coins, amount, member);
    }


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
                continue;//分配不了
            }
            //res>=0 ，表示能分配,==0正好分完，>0表示？
            if(res >=0 && res < min) {
                min = res+1;
            }
        }
//        member[amount - 1] = (min == Integer.MAX_VALUE) ? -1 : min;

        return min;
//        return  member[amount - 1];
    }




}
