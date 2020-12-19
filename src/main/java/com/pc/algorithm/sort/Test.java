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


        int[] arr = new int[1000];

        Random random = new Random(47);
        for(int i =0;i<1000;i++) {
            arr[i] = random.nextInt(1000);
        }

        System.out.println(Arrays.toString(arr));

//        quickSort(arr,0,arr.length-1);
//        bubbleSort(arr);

        BinaryHeap.heapSort(arr);

        System.out.println(Arrays.toString(arr));





    }



    public static void quickSort(int[] arr,int startIndex,int endIndex) {

        if(startIndex>=endIndex) {
            return;
        }

        int pivotIndex = partition(arr,startIndex,endIndex);

        quickSort(arr,startIndex,pivotIndex-1);
        quickSort(arr,pivotIndex+1,endIndex);

    }


    public static void bubbleSort(int[] arr) {

        boolean needSwap;

        for(int i=0; i< arr.length-1; i++) {
            needSwap  = false;
            for(int j=0; j< arr.length-1-i; j++) {
                if(arr[j]>arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    needSwap = true;
                }
            }
            if(!needSwap) {
                break;
            }
        }

    }




    public static int partition(int[] arr,int startIndex,int endIndex) {

        int left = startIndex;
        int right = endIndex;
        int pivot = arr[startIndex];

        while(left<right) {

            while(left<right && arr[right] >= pivot) {
                right--;
            }

            while(left<right && arr[left] <= pivot) {
                left++;
            }

            if(left<right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }


        arr[startIndex] = arr[left];
        arr[left] = pivot;

        return left;
    }


    public static void mergeSort(int[] arr, int start, int end) {
        if(end>start) {
            int mid = (start+end)/2;
            //分
            mergeSort(arr,start,mid);
            mergeSort(arr,mid+1,end);
            //分到不能分之后开始合，一层一层向上
            merge(arr,start,mid,end);
        }
    }

    public static void merge(int[] arr, int start,int mid, int end) {
        int[] tempArray = new int[end-start+1];
        int p1 = start;//左边数组
        int p2 = mid+1;//右边数组

        int p = 0;

        while(p1<=mid && p2<=end) {
            if(arr[p1] < arr[p2]) {
                tempArray[p] = arr[p1];
                p1++;
            } else {
                tempArray[p] = arr[p2];
                p2++;
            }
            p++;
        }

        while(p1<=mid) {
            tempArray[p] = arr[p1];
            p++;
            p1++;
        }

        while(p2<=end) {
            tempArray[p] = arr[p2];
            p++;
            p2++;
        }

        for(int i=0; i< tempArray.length; i++) {
            arr[i+start] = tempArray[i];
        }

    }


}
