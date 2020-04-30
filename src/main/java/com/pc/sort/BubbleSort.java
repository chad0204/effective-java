package com.pc.sort;

/**
 * 冒泡排序
 *
 *   如果有n个元素，那么就有n-1个相邻
 *
 *   比较相邻元素大小并交换位置，每次比较之后向前移动一位，总共n-1次。
 *
 *   一次上述操作，可以将最大元素移到最后，总共执行n-1次上述操作，即可完成排序。
 *
 *
 *
 *   性质：1、时间复杂度：O(n) ~ O(n^2)
 *        2、空间复杂度：O(1)
 *
 *
 *
 *
 *
 * @author dongxie
 * @date 17:16 2020-04-28
 */
public class BubbleSort {

    public static void main(String[] args) {
        //冒泡排序算法
        int[] numbers=new int[]{1,5,8,2,3,9,4};
        int i,j;
        for(i=0;i<numbers.length-1;i++) {
            for(j=0;j<numbers.length-1-i;j++) {
                if(numbers[j]>numbers[j+1]) {
                    int temp=numbers[j];
                    numbers[j]=numbers[j+1];
                    numbers[j+1]=temp;
                }
            }
        }
        System.out.println("从小到大排序后的结果是:");
        for(i=0;i<numbers.length;i++)
            System.out.print(numbers[i]+" ");


    }
}
