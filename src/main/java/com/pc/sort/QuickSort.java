package com.pc.sort;

/**
 * 快速排序
 *
 *
 *
 * @author dongxie
 * @date 17:49 2020-04-28
 */
public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {2,5,3,1,6,4};

        quickSort(arr,0,5);


        System.out.println(arr);


    }

    /**
     *
     * 假如最好的情况是一个有序序列 1 3 5 7 9 ,mid = 1
     * i = 0    arr[i] = 1
     * j = 4    arr[j] = 9
     * 而且在这里，如果先从左边开始寻找的话，一直往右寻找大于1的数，直到i变成4还没有找到就停止了；但是下面的语句就会把9赋值在1上了
     * 如果先从右边开始寻找的话，一直往左寻找小于1的数，直到j变成0还没有找到然后停止，此时i和j都是0，所以就是把自身交换一下并不影响顺序。
     * 这也是为什么强调如果选择数组左边第一个数作为基准值的时候，得先从右边开始查找数。
     *
     * SnmpMibNode.QuickSort可以从前面开始寻找
     *
     */
    public static void quickSort(int[] arr,int low,int high){
        int i,j,mid,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //mid就是基准位
        mid = arr[low];//可以 mid = arr[ ( low + high ) / 2 ]

        while (i<j) {
            //先看右边，依次往左递减
            while (mid<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (mid>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = mid;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }

}


