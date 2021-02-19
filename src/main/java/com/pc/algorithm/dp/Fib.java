package com.pc.algorithm.dp;

import java.util.HashMap;

/**
 * 509 斐波那契数列
 *
 * 0 1 1 2 3 5 8 13 21。。。
 *
 * 动态转移方程
 * f(n) = f(n-1)+f(n-2)
 * base case
 * f(0) = 0
 * f(1) = 1
 *
 *
 * 找出第n个斐波那契数列
 *
 * @author pengchao
 * @date 21:19 2020-12-23
 */
public class Fib {


    public static void main(String[] args) {

        int n = 8;

        System.out.println(dfs(n,new int[n+1]));
        System.out.println(dp(n));
        System.out.println(_dp(n));


    }

    /**
     * 自顶向下
     *
     * @param n
     * @param memo
     * @return
     */
    public static int dfs(int n,int[] memo) {
        if(n==0) {
            return 0;
        }
        if(n==1) {
            return 1;
        }

        if(memo[n]!=0) {
            return memo[n];
        }

        memo[n] = dfs(n-1,memo) + dfs(n-2,memo);
        return memo[n];
    }

    /**
     * 自下而上 dp table
     * @param n
     * @return
     */
    public static int dp(int n) {

        int[] dp = new int[n+1];

        for(int i=0;i<=n;i++) {
            if(i==0) {
                dp[i] = 0;
                continue;
            }
            if(i==1) {
                dp[i] = 1;
                continue;
            }
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }




    /**
     * 使用变量优化dp数组，压缩状态值
     * @param n
     * @return
     */
    public static int _dp(int n) {
        if(n== 0) {
            return 0;
        }
        if(n== 1) {
            return 1;
        }

        int a = 0;
        int b = 1;
        int temp = 0;

        for(int i=2;i<=n;i++) {
            temp = a+b;
            a = b;
            b = temp;
        }
        return temp;

    }





}
