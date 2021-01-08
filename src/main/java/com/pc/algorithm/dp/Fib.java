package com.pc.algorithm.dp;

import java.util.HashMap;

/**
 * 斐波那契数列
 *
 * 0 1 1 2 3 5 8 13 21。。。
 * f(0) - 0
 * f(1) = 1
 * f(2) = 1
 * f(3) = f(1)+f(2)
 *
 * f(n) = f(n-1)+f(f-2)
 *
 *
 * 动态转移方程
 * f(n) = f(n-1)+f(f-2)
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

        //八种结果
        int[] member = new int[n];

        System.out.println(fib(n));


    }

    /**
     * 自顶向下
     * @param n
     * @param map
     * @return
     */
    public static int fib(int n,HashMap<Integer,Integer> map) {
        if(n==1 || n==2) {
            return 1;
        }

        if(map.containsKey(n)) {
            return map.get(n);
        } else {
            int fib = fib(n-1,map) + fib(n-2,map);
            map.put(n,fib);
            return fib;
        }
    }

    /**
     * 自下而上 dp table
     * @param n
     * @return
     */
    public static int fib(int n) {

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
     * 使用变量优化备忘录，压缩状态值
     * @param n
     * @return
     */
    public static int dp(int n) {
        if(n==1 || n==2) {
            return 1;
        }

        int a = 1;
        int b = 1;
        int temp = 0;

        for(int i=3;i<=n;i++) {
            temp = a+b;
            a = b;
            b = temp;
        }
        return temp;

    }





}
