package com.pc.algorithm.sort;

import java.util.Arrays;

/**
 *
 * 1.有n个元素，n-1个相邻，冒泡的意思是逐个比较相邻元素并交换
 *
 * 2.第i次冒泡，需要比较n-1-i个元素，才能冒好
 *
 * 3.通过标志记录一次冒泡完成后，是否发生比较来判断是否已经有序，可节约时间，实现O(n)
 *
 *
 *
 * 时间复杂度：
 *      共需要N-1次冒泡（最好情况为1次）
 *      每次冒泡需要比较N-1次
 *      O(n) ～ O(n^2)
 *
 * 空间复杂度：
 *      没有递归
 *      没有创建数组
 *      O(1)
 *
 *
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = {6,5,4,3,2,1};
//        int [] arr = {1,2,3,4,5,6};
        sort(arr);


        System.out.println("result:"+Arrays.toString(arr));

    }


    public static void sort(int[] array) {
        /*
            i:n-1趟冒泡
            j:一轮冒泡
         */
        boolean didSwap;//表示是否需要继续比较

        //用于记录多少次冒泡
        for(int i = 0; i < array.length -1; i++) {
            didSwap = false;
            //每次冒泡需要比较的个数（i表示已经冒泡完成的元素数）
            for(int j = 0;j < array.length-1-i; j++) {
                if(array[j]>array[j+1]) {
                    //交换j和j+1
                    int temp  = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                    didSwap = true;
                }
                System.out.println(Arrays.toString(array));
            }
            System.out.println("=====================");

            if(!didSwap) {
                //表示一次冒泡完成，都没有发生交换，那么不用再继续冒泡了
                break;
            }
        }
    }


}
