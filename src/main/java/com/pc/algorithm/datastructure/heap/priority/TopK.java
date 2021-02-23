package com.pc.algorithm.datastructure.heap.priority;

import com.pc.algorithm.datastructure.heap.BinaryHeap;
import com.pc.algorithm.sort.QuickSort;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 *
 *
 * topK 问题
 *
 * 1.二叉堆
 * 构建最大堆和最小堆，然后循环交换堆顶
 *
 *
 * 2.利用快排
 *
 *
 *
 *
 *
 *
 *
 *
 * @author pengchao
 * @date 10:56 2021-02-23
 */
public class TopK {


    public static void main(String[] args) {
//        System.out.println(Arrays.toString(getLeastNumbersWithQuick(new int[]{6, 8, 4, 1, 3, 2, 7, 5}, 3)));



//        System.out.println(findKthLargest(new int[]{3,2,1,5,6,4},2));
        System.out.println(findKthLargestWithQuick(new int[]{2,1},1));

    }


    /**
     * 剑指 Offer 40. 最小的k个数
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        int len = arr.length;
        //构建最小堆
        for(int i=(len-2)/2;i>=0;i--) {
            BinaryHeap.downAdjust(arr,i,len);
        }

        // 2 1 1 0
        int[] res = new int[k];

        //循环删除堆顶
        for(int i=0;i<k;i++) {
            res[i] = arr[0];
            arr[0] = arr[len-1-i];
            arr[len-1-i] = res[i];
            BinaryHeap.downAdjust(arr,0,len-1-i);
        }

        return res;

    }

    /**
     * 215. 数组中的第K个最大元素
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        //构建最大堆
        for(int i = (len-2)/2;i>=0;i-- ) {
            BinaryHeap.downAdjustBig(nums,i,len);
        }

        int[] res = new int[k];
        for(int i=0;i<k;i++) {
            res[i] = nums[0];
            nums[0] = nums[len-1-i];
            nums[len-1-i] = res[i];
            BinaryHeap.downAdjustBig(nums,0,len-1-i);
        }

        return res[k-1];
    }


    /**
     * 快排思想
     *
     * 切割，直到找到k，然后copy数组0-k段
     *
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbersWithQuick(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 最后一个参数表示我们要找的是下标为k-1的数
        return quickSearch(arr, 0, arr.length - 1, k - 1);
    }

    private static int[] quickSearch(int[] nums, int lo, int hi, int k) {
        // 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
        int j = QuickSort.partitionUseIndexSwap(nums, lo, hi);
        if (j == k) {
            return Arrays.copyOf(nums, j + 1);
        }
        // 否则根据下标j与k的大小关系来决定继续切分左段还是右段。
        return j > k? quickSearch(nums, lo, j - 1, k): quickSearch(nums, j + 1, hi, k);
    }





    public static int findKthLargestWithQuick(int[] nums, int k) {

        return quickSort(nums,0,nums.length-1,k);
    }



    public static int quickSort(int[] arr,int start,int end,int K) {
        //注意求最大，要把这里改成倒序排序
        int p = QuickSort.partitionUseIndexSwap(arr,start,end);
        if(p+1==K) {
            return arr[p];
        }

        return p+1>K ? quickSort(arr,start,p-1,K) : quickSort(arr,p+1,end,K);
    }











}
