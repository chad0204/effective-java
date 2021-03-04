package com.pc.algorithm.math;

/**
 * 69. x 的平方根
 *
 * @author pengchao
 * @date 10:33 2021-03-04
 */
public class Sqrt {

    public static void main(String[] args) {
        mySqrt(1);
    }


    public static int mySqrt(int x) {
        if(x==0) {
            return 0;
        }

        long left = 1;
        long right = x/2;

        while(left<right) {
            //取右中位数
            long mid = left+(right-left+1)/2;
            long val = mid*mid;
            if(val>x) {
                //不取mid
                right = mid-1;
            } else {
                left = mid;
            }
        }



        return (int) left;
    }
}
