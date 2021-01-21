package com.pc.algorithm.sort;


import com.pc.algorithm.datastructure.heap.BinaryHeap;
import java.util.Arrays;

/**
 *
 * 堆排序
 *
 *
 *  时间复杂度：(N/2 + N )* logN = O(N*logN)
 *  空间复杂度：O(1)
 *
 */
public class HeapSort {


    public static void main(String[] args) {

        /*
         * 1.二叉堆原理
         */

        //（前提已经是堆）插入的0放在最后，开始上浮
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};



        BinaryHeap.upAdjust(array);


        //（前提已经是堆）删除0之后，5补位到顶点
        array = new int[]{5,1, 2, 6, 3, 7, 8, 9, 10};
        BinaryHeap.downAdjust(array,0,array.length);


        //构建
        array = new int[] {7,1,3,10,5,2,8,9,6};
        BinaryHeap.build(array);


        array = new int[]{10,5,2,6,7,3,8,9};
        BinaryHeap.downAdjust(array,0,array.length);

        System.out.println(Arrays.toString(array));



        /*
         * 2.堆排序
         */
        array = new int[] {7,1,4,3,10,5,2,8,9,6,0};
        heapSort(array);



    }


    public static void heapSort(int[] array) {

        //构建最大堆
        for(int i=(array.length-2)/2; i>=0; i--) {
            BinaryHeap.downAdjustBig(array,i,array.length);
        }

        //循环替换堆顶元素
        /*这里-i表示移动到堆尾的不用调整，-1是因为一开始就移动了一个元素到堆尾，堆大小从length-1开始*/
        for(int i =0;i<array.length;i++) {
            //头尾替换
            int temp = array[0];
            array[0] = array[array.length-1-i];
            array[array.length-1-i] = temp;
            //调整堆，替换到堆尾的不用参与调整,每次parentIndex都是0,变的只有长度
            BinaryHeap.downAdjustBig(array,0,array.length-1-i);
        }
    }

}
