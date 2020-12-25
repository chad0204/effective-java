package com.pc.algorithm.dp;

import java.util.HashMap;

/**
 *
 * # A
 * dp(n-1,a_num+1,copy)
 *
 * #C_A、C_C 选中和复制必然是联合使用
 * dp(n-2,a_num,a_num)
 *
 * #C_V
 * dp(n-1,a_num+copy,copy)
 *
 * 状态转移方程
 * f(n) = max(dp(n-1,a_num+1,copy),dp(n-2,a_num,a_num),dp(n-1,a_num+copy,copy))
 *
 *
 * n = 0 结束
 *
 *
 *
 *
 * @author pengchao
 * @date 20:20 2020-12-23
 */
public class KeyBoard {

    public static void main(String[] args) {


        System.out.println(dp(30,0,0,new HashMap<>()));


    }



    /**
     *
     * @param n 剩余按键次数
     * @param a_num  屏幕上的A个数
     * @param copy 剪切板中的A的个数
     * @return
     */
    public static int dp(int n, int a_num, int copy, HashMap<Integer,Integer> map) {

        if(n<=0) {
            return a_num;//返回a_num
        }

        //A
        int a = dp(n-1,a_num+1,copy,map);
        //C_A、C_C
        int c_a_c = dp(n-2,a_num,a_num,map);
        //C_V
        int c_v = dp(n-1,a_num+copy,copy,map);

        return max(a,c_a_c,c_v);
    }


    public static int max(int a, int b, int c) {
        int max = a;
        if(b>max) {
            max = b;
        }
        if(c>max) {
            max = c;
        }
        return max;
    }




}
