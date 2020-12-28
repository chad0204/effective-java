package com.pc.algorithm.dp;

import java.util.HashMap;
import java.util.Map;

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
 *  F(1) = 1
 *  F(2) = 2
 *  F(x) = F(x-1) + F(x-2) (x>=3)
 *
 *  最优子结构：F(x-1)和F(x-2)是F(x)的最优子结构
 *  边界：F(1)和F(2)是边界
 *  状态转移公式：F(x) = F(x-1) + F(x-2)
 *
 */
public class Climb {


    public static void main(String[] args) {

        System.out.println("走法数："+getClimbingWays(44));
        System.out.println("走法数："+getClimbingWaysUseMemento(44,new HashMap<>()));
        System.out.println("走法数："+getClimbingWaysNoRecur(44));
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
    public static int getClimbingWays(int n) {
        if(n<1) {
            return 0;
        }
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 2;
        }
        return getClimbingWays(n-1) + getClimbingWays(n-2);
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
    public static int getClimbingWaysUseMemento(int n, Map<Integer,Integer> map) {
        if(n<1) {
            return 0;
        }
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 2;
        }

        if(map.containsKey(n)) {
            return map.get(n);
        } else {
            int num = getClimbingWays(n-1) + getClimbingWays(n-2);
            map.put(n,num);
            return num;
        }
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
    public static int getClimbingWaysNoRecur(int n) {
        if(n<1) {
            return 0;
        }
        if(n==1) {
            return 1;
        }
        if(n==2) {
            return 2;
        }

        //用于临时保存前两个状态
        int a = 1;
        int b = 2;

        int temp = 0;

        for(int i=3; i<=n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;

    }



}

