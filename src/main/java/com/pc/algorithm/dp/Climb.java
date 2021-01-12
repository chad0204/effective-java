package com.pc.algorithm.dp;


/**
 * 有一座高度是10级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法
 *
 *
 *  11111111111
 *  22222
 *  1212121
 *  1111222
 *  ...
 *
 *  F(0) = 1
 *  F(1) = 1
 *  F(x) = F(x-1) + F(x-2) (x>=3)
 *
 *  最优子结构：F(x-1)和F(x-2)是F(x)的最优子结构
 *  边界：F(1)和F(2)是边界
 *  状态转移公式：F(x) = F(x-1) + F(x-2)
 *
 */
public class Climb {


    public static void main(String[] args) {

        System.out.println("走法数："+dfs(11));
        System.out.println("走法数："+dfs(11,new int[11+1]));
        System.out.println("走法数："+dp(11));
        System.out.println("走法数："+_dp(11));
    }


    /**
     * 递归解法,有重复计算
     *
     * 时间复杂度：2^(n-1)
     * 空间复杂度：logN
     *
     *
     * @param n
     * @return
     */
    public static int dfs(int n) {
        if(n==0) {
//            System.out.println("dfs["+n+"]="+1);
            return 1;
        }
        if(n==1) {
//            System.out.println("dfs["+n+"]="+1);
            return 1;
        }

        int res = dfs(n-1) + dfs(n-2);
//        System.out.println("dfs["+n+"]="+res);

        return res;
    }


    /**
     * 备忘录,用空间换时间
     *
     * map里面保存了所有子状态
     *
     * 时间复杂度：O(n)
     * 空间复杂度：logN+N
     *
     * @param n
     * @return
     */
    public static int dfs(int n, int[] memo) {

        if(n==0) {
            return 1;
        }
        if(n==1) {
            return 1;
        }

        if(memo[n] != 0) {
            return memo[n];
        }
        memo[n] = dfs(n-1,memo) + dfs(n-2,memo);
        return memo[n];

    }

    /**
     *
     *  动态规划
     *
     * 非递归实现
     * 备忘录的map里面保存了所有子状态,但是f(n)只依赖f(n-1)和f(n-2)两种状态,我们可不可以不用保存所有的子状态
     *
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return
     */
    public static int dp(int n) {

        int[] dp = new int[n+1];

        for(int i =0;i<=n;i++) {
            if(i==0 || i==1) {
                dp[i] = 1;
                continue;
            }
            dp[i] = (dp[i-1]+dp[i-2])%1000000007;
        }
        return dp[n];

    }

    public static int _dp(int n) {

        if(n==0 || n==1) {
            return 1;
        }

        int a = 1;
        int b = 1;

        int res = 0;

        for(int i =2;i<=n;i++) {
            res = a+b;
            a = b;
            b= res;
        }
        return res;

    }



}

