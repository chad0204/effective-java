package com.pc.algorithm.dp;


import java.util.Arrays;

/**
 *
 * 金额amount
 * 面值[c1,c2,c3,c4]
 *
 * f(0) = 0
 * f(n) = -1, n<0
 * f(amount) = min{f(amount-ci)+1}
 *
 *
 *
 * @author pengchao
 * @date 11:23 2020-12-28
 */
public class Test {

    public static void main(String[] args) {

        int[] coins = {1,2,5};
        System.out.println(coinChange(coins,0));
        System.out.println(dp(coins,6));
    }

    /**
     * 自上而下
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if(amount<=0) {
            return 0;
        }

        //用于保存所有状态
        int[] member = new int[amount];

        return dp(coins,amount,member);
    }


    public static int dp(int[] coins,int amount,int[] member) {

        if(amount==0) {
            return 0;
        }

        if(amount<0) {
            return -1;
        }

        if(member[amount-1] != 0) {
            return member[amount-1];
        }

        int min = Integer.MAX_VALUE;

        //遍历所有面值，res=0表示刚好分完，res==-1表示分不了，res>0可以继续分，没多分一次，res+1，然后取最小的分法
        for(int coin :coins) {
            int res = dp(coins,amount-coin,member);
            if(res==-1) {
                continue;
            }
            min = res < min ? res+1 : min ;
            member[amount-1] = min;
        }

        min = min == Integer.MAX_VALUE ? -1 : min;

        return min;
    }


    /**
     * 自下而上，自状态无限，所以用dp table
     * @param coins
     * @param amount
     * @return
     */
    public static int dp(int[] coins,int amount) {
        //定义一个dp table，保存所有状态,且初始每个状态的值都为amount+1
        int max = amount + 1;
        int[] dpTable = new int[amount+1];//因为要保存f(0),所以长度为amount+1
        Arrays.fill(dpTable,max);

        //f(0) = 0
        dpTable[0] = 0;

        for(int i=1;i<=amount;i++) {
            for(int coin : coins) {
                //面值比余额还大，跳过
                if(coin > i) {
                    continue;
                }
                dpTable[i] = Math.min(dpTable[i],dpTable[i-coin]+1);
            }
        }

        return dpTable[amount] == max ? -1 : dpTable[amount];
    }


}
