package com.pc.algorithm.sort;


import java.util.Arrays;

/**
 * 归并排序
 *
 *
 *     先将数组均分成最多只有两个元素的小组，然后比较互换位置。
 *     然后合并小组，合并时逐一比较两个数组元素。
 *
 *
 *  分割
 *  123
 *  12 3
 *
 *  1234
 *  12 34
 *
 *
 *  合并规则：
 *
 *  1  3  5  6    2  7  8  9
 *  p1            p2
 *
 *  p
 *  ___________________________
 *
 *  (1<2)
 *
 *  1  3  5  6    2  7  8  9
 *     p1         p2
 *  1
 *     p
 * ___________________________
 *
 *  (2<3)
 *
 *  1  3  5  6    2  7  8  9
 *     p1            p2
 *  1  2
 *        p
 * ___________________________
 *
 *  (3<7)
 *
 *  1  3  5  6    2  7  8  9
 *       p1         p2
 *  1  2  3
 *        p
 *
 * ___________________________
 *
 *  (5<7)
 *
 *  1  3  5  6    2  7  8  9
 *           p1     p2
 *  1  2  3  5
 *             p
 *  ___________________________
 *
 *  (6<7)
 *  1  3  5  6    2  7  8  9
 *                  p2
 *  1  2  3  5  6
 *                p
 * ___________________________
 * (p1已经走完)
 *  1  3  5  6    2  7  8  9
 *                   p2
 *
 *  1  2  3  5  6  7  8  9(copy)
 *
 *
 *
 * 时间复杂度：
 *   长度N
 *   折半的层数logN，总是logN
 *   每一层归并的运算量是N
 *   N*logN
 *
 * 空间复杂度：
 *  长度N
 *  压栈的空间为logN
 *  单次归并创建的最大数组为N(由于每次递归都会释放数组，所以不需要累加)
 *  N + logN = N
 *
 *
 * @author pengchao
 * @date 16:14 2020-12-15
 */
public class MergeSort {


    public static void main(String[] args) {

        int[] array = {1,3 ,5  ,6 ,   2 , 7 , 8 , 9};
        mergeSort(array,0,array.length-1);

        System.out.println(Arrays.toString(array));

    }

    public static void mergeSort(int[] array, int start, int end) {
        if(start < end) {//分到start=end时结束，也就是只剩一个元素
            //拆
            int mid = (start+end)/2;
            mergeSort(array,start,mid);
            mergeSort(array,mid+1,end);

            //合
            merge(array,start,mid,end);//最底层的merge是两个元素
        }
        //返回上一个递归
    }

    /**
     * 合并
     *
     * 创建一个临时数组和下标p
     * 待合并的两个数组，下标p1,p2
     * 逐个比较两个数组的元素，将小的值放入临时数组，并移动下标，直到任意一个数组走完
     * 分别检查左右数组是否有剩余值，有则依次放入临时数组
     * 最后将排好序的临时数组复制到原数组的相应位置
     *
     * @param array 原数组
     * @param start 当前需要合并的数组的起始索引（不一定为0）
     * @param mid 分割位置索引
     * @param end 数组尾部索引
     *
     */
    private static void merge(int[] array, int start, int mid, int end) {
        int[] tempArray = new int[end-start+1];//大小等于当前需要合并的两个数组之和
        int p1 = start;//左数组的下标
        int p2 = mid+1;//右数组的下标
        int p = 0;//临时数组的下标

        //比较两个小集合的元素，依次放入大集合。循环条件就是p1p2都没走完
        while(p1<=mid && p2 <= end) {
            if(array[p1] < array[p2]) {
                tempArray[p] = array[p1];
                p1++;
            } else {
                tempArray[p] = array[p2];
                p2++;
            }
            p++;
        }
        //如果左侧有剩余，依次放入集合尾部
        while (p1<=mid) {
            tempArray[p] = array[p1];
            p++;
            p1++;
        }
        //如果右侧有剩余，依次放入集合尾部
        while (p2<=end) {
            tempArray[p] = array[p2];
            p++;
            p2++;
        }
        //复制回原数组
        for(int i =0;i<tempArray.length;i++) {
            array[i+start] = tempArray[i];//start不一定是0，可能是大数组的右部分
        }

    }










}
