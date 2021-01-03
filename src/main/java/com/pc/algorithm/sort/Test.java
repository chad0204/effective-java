package com.pc.algorithm.sort;


import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author dongxie
 * @date 17:40 2020-04-28
 */
public class Test {

    public static void main(String[] args) {
//        int[] arr = {4,1,6,5,3,2,8,7};

//
        Random random = new Random(47);
        int[] arr = new int[1000];
        for(int i =0;i<1000;i++) {
            arr[i] = random.nextInt(1000);
        }
        heapSort(arr);
        System.out.println("merge_sort:"+Arrays.toString(arr));

    }

    /**
     *
     * 冒泡
     *   n-1趟冒泡
     *   每次冒泡比较n-1-i次
     *   冒泡标志
     *
     *   O(n^2)
     *   O(1)
     * @param array
     */
    public static void bubbleSort(int[] array) {

        boolean needSwap;

        for(int i=0;i<array.length-1;i++) {
            needSwap = false;
            for(int j = 0; j< array.length-1-i;j++) {
                if(array[j]>array[j+1]) {
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                    needSwap = true;
                }
            }
            if(!needSwap) {
                break;
            }
        }
    }

    /**
     * 快速排序
     *     分区：
     *     选中左基准值
     *     右边找到比基准值小的，左边找到比基准值大的
     *     交换左右
     *     左右重合，基准值和重合点交换
     *
     *     O(nlogn)
     *     O(logn)
     *
     * @param array
     */
    public static void quickSort(int[] array,int startIndex,int endIndex) {
        if(startIndex>=endIndex) {
            return;
        }

        int pivotIndex = partition(array,startIndex,endIndex);

        quickSort(array,startIndex,pivotIndex-1);
        quickSort(array,pivotIndex+1,endIndex);

    }

    private static int partition(int[] array, int startIndex, int endIndex) {
        int left = startIndex;
        int right = endIndex;
        int pivot = array[startIndex];

        //循环结束条件是重合
        while (left<right) {
            //找到比基准值大小的
            while (left<right) {
                if(array[right]>=pivot) {
                    right--;
                } else {
                    break;
                }
            }
            while (left<right) {
                if(array[left]<=pivot) {
                    left++;
                } else {
                    break;
                }
            }

            //左右交换
            if(left<right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }

        //基准值和重合点交换
        array[startIndex] = array[left];
        array[left] = pivot;
        return left;
    }


    /**
     *
     * 归并排序
     *      头尾相加/2找到mid
     *
     *      合：
     *      构造一个临时数组
     *      左边第一个和右边第一个比较，较大的放入临时数组
     *      检查左右数组剩余，将剩余逐个放入临时数组
     *
     *
     *   O(nlogn)
     *   O(n)
     *
     *
     * @param array
     * @param start
     * @param end
     */
    public static void mergeSort(int[] array,int start,int end) {
        if(end<=start) {
            return;
        }
        int mid = (end + start)/2;//获取中间值
        mergeSort(array,start,mid);
        mergeSort(array,mid+1,end);

        merge(array,start,mid,end);

    }

    private static void merge(int[] array, int start, int mid, int end) {
        int[] tempArray = new int[end-start+1];
        int p = 0;//临时数组下标

        int left = start;
        int right = mid+1;

        while (left<=mid && right<=end) {//条件判断
            if(array[left]<array[right]) {
                tempArray[p++] = array[left++];
            } else {
                tempArray[p++] = array[right++];
            }
        }

        while (left<=mid) {
            tempArray[p++] = array[left++];
        }
        while (right<=end) {
            tempArray[p++] = array[right++];
        }

        for(int i=0;i<tempArray.length;i++) {
            array[start+i] = tempArray[i];
        }
    }


    /**
     * 构建最大堆
     * 从最后一个非叶子节点开始下沉。
     * 最后一个节点
     * last_p_index = (length-2)/2
     *
     *
     *
     * O((n+n/2)logn)
     * O(1)
     *
     * @param array
     */
    public static void heapSort(int[] array) {

        //构建最大堆
        int lastParent = (array.length-2)/2;
        for(int i= lastParent;i>=0;i--) {
            downAdjust(array,i,array.length);
        }

        //堆顶依次转移到堆尾，移动，调整（调整时不需要考虑已经移动的，办法是设置长度）
        for(int i=0;i<array.length;i++) {
            int temp = array[0];
            array[0] = array[array.length-1-i];
            array[array.length-1-i] = temp;
            downAdjust(array,0,array.length-1-i);
        }

    }

    public static void downAdjust(int[] array,int parentIndex,int length) {

        int temp = array[parentIndex];
        int childIndex = parentIndex*2+1;

        while (childIndex<length) {
            if(childIndex+1 < length && array[childIndex+1] > array[childIndex]) {
                childIndex++;
            }

            if(temp>array[childIndex]) {
                break;
            }

            //子覆盖父
            array[parentIndex] = array[childIndex];
            //新的父节点
            parentIndex = childIndex;
            //新的子节点
            childIndex = parentIndex*2+1;
        }
        array[parentIndex] = temp;

    }


}
