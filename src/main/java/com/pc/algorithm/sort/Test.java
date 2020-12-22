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

        int[] arr;

        arr = new int[]{7,3,6,9,2,1,4};

//        arr = new int[1000];
//        Random random = new Random(47);
//        for(int i =0;i<1000;i++) {
//            arr[i] = random.nextInt(1000);
//        }



        heapSort(arr);
        System.out.println(Arrays.toString(arr));




    }


    /*
     * O(n^2)  O(1)
     */
    public static void bubbleSort(int[] array) {
        boolean needSwap;
        //n-1次冒泡
        for(int i=0; i<array.length-1; i++) {
            needSwap = false;
            for(int j=0;j<array.length-1-i;j++) {
                if(array[j]>array[j+1]) {
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                    needSwap = true;
                }
            }
            //如果一次冒泡都没发生交换，说明已经顺序了
            if(!needSwap) {
                break;
            }
        }
    }

    /*
     *
     *  分区，选基准值，右边找出第一个比基准值小的元素，左边找出第一个比基准值小的元素，交换，最终重合点和基准值交换。
     *  递归
     *
     *  O(n*logN) O(logN)
     */
    public static void quickSort(int[] array,int startIndex,int endIndex) {

        if(startIndex>=endIndex) {
            return;
        }
        int pivotIndex  = partition(array,startIndex,endIndex);
        quickSort(array,startIndex,pivotIndex-1);
        quickSort(array,pivotIndex+1,endIndex);


        //栈




    }

    private static int partition(int[] array, int startIndex, int endIndex) {

        int left = startIndex;
        int right = endIndex;
        int pivot = array[startIndex];

        while (left<right) {
            //从右边开始
            while (left<right && array[right] >= pivot) {
                right--;
            }
            while (left<right && array[left] <= pivot) {
                left++;
            }

            //交换左右
            if(left<right) {
                int temp = array[right];
                array[right] = array[left];
                array[left] = temp;
            }
        }

        //将重合点和基准值交换
        array[startIndex] = array[left];
        array[left] = pivot;

        return left;
    }


    /*
     *
     * 分
     *
     * 合
     *
     * n*logN  n+logN =  n
     *
     */
    public static void mergeSort(int[] array,int startIndex, int endIndex) {
        if(startIndex<endIndex) {
            int mid = (startIndex+endIndex)/2;
            mergeSort(array,startIndex,mid);
            mergeSort(array,mid+1,endIndex);

            merge(array,startIndex,mid,endIndex);
        }
    }

    private static void merge(int[] array, int startIndex, int mid, int endIndex) {

        int[] tempArray = new int[endIndex-startIndex+1];
        int p = 0;
        int p1 = startIndex;
        int p2 = mid+1;


        while (p1<= mid && p2<=endIndex) {
            if(array[p1] < array[p2]) {
                tempArray[p] = array[p1];
                p1++;
            } else {
                tempArray[p] = array[p2];
                p2++;
            }
            p++;

//            if(p1>mid || p2>endIndex) {
//                break;
//            }
        }

        while (p1<=mid) {
            tempArray[p] = array[p1];
            p++;
            p1++;
        }

        while (p2<=endIndex) {
            tempArray[p] = array[p2];
            p++;
            p2++;
        }

        for(int i=0; i< tempArray.length;i++) {
            array[startIndex+i] = tempArray[i];
        }

    }


    /*
     *
     * 最大堆
     *
     * 下沉
     *
     * O(N*logN)  = (N/2 + N )* logN
     */
    public static void heapSort(int[] array) {

        //构建最大堆，循环下沉所有非叶子节点
        for(int i=(array.length-2)/2; i>=0 ;i--) {
            downAdjust(array,i,array.length);
        }

        //循环删除堆顶
        for(int i=0; i<array.length;i++ ) {
            int temp = array[array.length-1-i];
            array[array.length-1-i] = array[0];
            array[0] = temp;
            downAdjust(array,0,array.length-1-i);
        }

    }

    /**
     *
     *
     *
     * @param array
     * @param parentIndex 待下沉的的顶点
     * @param length
     */
    public static void downAdjust(int[] array, int parentIndex,int length) {

        int childIndex = parentIndex*2+1;
        int temp = array[parentIndex];

        while (childIndex<length) {
            if(childIndex+1<length && array[childIndex] < array[childIndex+1]) {
                childIndex++;
            }

            if(array[childIndex] > temp) {
                //覆盖
                array[parentIndex] = array[childIndex];
                parentIndex  = childIndex;
                childIndex = parentIndex*2+1;
            } else {
                break;
            }
        }
        //最小叶子赋值
        if(array[parentIndex] > temp) {
            array[parentIndex] = temp;
        }

    }




}
