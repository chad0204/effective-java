package com.pc.algorithm.sort;

import java.util.*;

/**
 * 快速排序
 *
 *  时间复杂度：
 *      递归的层数为logN（最差为N）
 *      每一层的运算量为N
 *      N*logN
 *
 *  空间复杂度：
 *      递归产生的栈空间复杂度,也就是递归层数
 *      logN ～ N
 *
 *
 * @author pengchao
 * @date 17:49 2020-04-28
 */
public class QuickSort {

    public static void main(String[] args) {

//        int[] arr = {4,1,6,5,3,2,8,7};
        int[] arr = {8,7,6,5,4,3,2,1};

//        int[] arr = new int[1000];
//
//        Random random = new Random(47);
//        for(int i =0;i<1000;i++) {
//            arr[i] = random.nextInt(1000);
//        }

        //排序前
        System.out.println(Arrays.toString(arr));

        //使用栈排序
//        System.out.println(Arrays.toString(quickSortWithStack(arr,0,arr.length-1)));

        //使用递归排序
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    /**
     *
     * 分治思想
     *
     *
     * 最坏的情况是，正好是个逆序数组，基准值pivot取第一个，正好是最大值，相当于每次只是找到了pivot的位置（这时有点类似冒泡）
     *
     *   8 7654321
     *   7 654321 8
     *   6 54321 78
     *   5 4321 678
     *   ...
     *
     *  解决办法是每次分治都随机找一个基准值（当然也有可能 '每次' 分治后选中的基准值都是最大值，但概率极低）
     *
     *
     */
    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 递归结束条件：startIndex大等于endIndex的时候
        if (startIndex >= endIndex) {
            return;
        }
        // 得到基准元素位置
        int pivotIndex = partitionUseIndexSwap(arr, startIndex, endIndex);

        // 用分治法递归数列的两部分
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);

        //结束后返回上一个递归
    }


    /**
     * 交换法
     *
     * 选左1为基准值
     * 从右边开始，一直向左寻找，直到有比基准值小的元素，right指针停下，切换到左边
     * 从左边开始，一直向右寻找，直到有比基准值大的元素，left指针停下，交换left和right
     * 直到left和right重合，然后将基准值和重合点交换，此时已经找到基准值的该在的位置，基准值索引左边皆小，右边皆大
     * 按照基准值位置分别递归左右两边
     *
     *
     * 相等的时候也要往前走走，不然left就停在基准值下面不走了，所以要arr[left] >= pivot
     * 左基右找
     *
     *
     */
    public static int partitionUseIndexSwap(int[] arr, int startIndex, int endIndex) {

        int pivot = arr[startIndex];//选第一个元素为基准值

        int left = startIndex;
        int right = endIndex;


        while (left < right) {

            //右边开始,找到小于pivot的第一个值
            while (left<right) {
                if(arr[right] >= pivot) {//相等的时候也要往前走走
                    right--;
                }  else {
                    break;
                }
            }


            //左边开始，找到大于pivot的第一个值
            while (left<right) {
                if(arr[left] <= pivot) {
                    left++;
                } else {
                    break;
                }
            }

            //这时left停在大于pivot的位置，right停在小于pivot的位置，且left<right，交换
            if(left<right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }


        //最后将基准值赋值到left和right重合点
        arr[startIndex] = arr[left];
        arr[left] = pivot;


        return left;
    }


    /**
     * 使用栈来替代递归
     *
     */
    public static int[] quickSortWithStack(int[] arr, int startIndex, int endIndex) {
        // 用一个集合栈来代替递归的函数栈
        Stack<Map<String, Integer>> quickSortStack = new Stack<>();
        // 整个数列的起止下标，以哈希的形式入栈
        Map<String,Integer> rootParam = new HashMap<>();
        rootParam.put("startIndex", startIndex);
        rootParam.put("endIndex", endIndex);
        quickSortStack.push(rootParam);
        // 循环结束条件：栈为空时结束
        while (!quickSortStack.isEmpty()) {
            // 栈顶元素出栈，得到起止下标
            Map<String, Integer> param = quickSortStack.pop();
            // 得到基准元素位置
            int pivotIndex = partitionUseIndexSwap(arr, param.get("startIndex"), param.get("endIndex"));
            // 根据基准元素分成两部分, 把每一部分的起止下标入栈
            if(param.get("startIndex") < pivotIndex -1){
                Map<String, Integer> leftParam = new HashMap<>();
                leftParam.put("startIndex", param.get("startIndex"));
                leftParam.put("endIndex", pivotIndex -1);
                quickSortStack.push(leftParam);
            }
            if(pivotIndex + 1 < param.get("endIndex")){
                Map<String, Integer> rightParam = new HashMap<>();
                rightParam.put("startIndex", pivotIndex + 1);
                rightParam.put("endIndex", param.get("endIndex"));
                quickSortStack.push(rightParam);
            }
        }

        return arr;
    }



    /**
     * 挖坑法
     *
     * 选第一个左右基准值
     * 从右边开始找比基准值小的元素（直到找到），找到后将其赋值到左边也就是index处，然后left右移，此时右边就留了个坑，将index指向右边的坑
     * 然从左边开始找比基准值大的元素（直到找到），找到后将其赋值到右边也就是index处，然后right左移，此时左边就留了一个坑，将ndex指向左边的坑。
     *
     *
     */
    private static int partitionUseFillIndex(int[] arr, int startIndex, int endIndex) {
        // 取第一个位置的元素作为基准元素
        int pivot = arr[startIndex];

        int left = startIndex;
        int right = endIndex;
        // 坑的位置，初始等于pivot的位置
        int index = startIndex;
        //大循环在左右指针重合或者交错时结束
        while ( right >= left ){
            //right指针从右向左进行移动，直到找到比基准值小的值，index切到right，left右移
            while ( right >= left ) {
                if (arr[right] < pivot) {
                    //将right的值赋给left,切换index,从右边开始比较
                    arr[left] = arr[right];
                    index = right;
                    left++;
                    System.out.println(Arrays.toString(arr));
                    break;
                }
                right--;
            }
            //left指针从左向右进行移动，直到找到比基准值大的值，index切到left，right左移
            while ( right >= left ) {
                if (arr[left] > pivot) {
                    arr[right] = arr[left];
                    index = left;
                    right--;
                    System.out.println(Arrays.toString(arr));
                    break;
                }
                left++;
            }

        }
        arr[index] = pivot;

        return index;
    }






}


