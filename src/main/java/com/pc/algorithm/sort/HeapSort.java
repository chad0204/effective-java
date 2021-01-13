package com.pc.algorithm.sort;


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
        BinaryHeap.heapSort(array);



    }

}


/**
 * 二叉堆
 *  父节点大于等于或小于等于左右子节点的完全二叉树
 *
 * 二叉堆可以用数组实现而不是链表
 *  因为
 *  若 parent = N
 *  则   left  = 2*N+1, right = 2*N+2
 *
 *  若 left_child = n
 *  则    right_child = n+1,parent = (n-1)/2
 *
 *
 *  最大的堆的堆顶一定是最大值，最大堆的堆尾一定是最小值
 *  最小的堆的堆顶一定是最小值，最大堆的堆尾一定是最大值
 *
 *
 */
class BinaryHeap {


    /**
     *
     * 插入堆尾
     *   插入到堆的最后一个位置，然后与父节点比较交换，上浮
     *
     *
     * 插入=上浮
     *
     *
     * @param array
     */
    public static void upAdjust(int[] array) {
        int childIndex = array.length-1;//left_child
        int parentIndex = (childIndex-1)/2;

        //保存插入的叶子节点值，用于最后的赋值
        int temp = array[childIndex];

        // 找不到父节点或者父节点比temp小结束
        // 满足条件，然后父节点向下移动，当前父节点变成子节点，再向上找上一个父节点
        // childIndex可以看做一个指针，连续交换时，父节点变成新的孩子节点
        while(childIndex>0 && temp < array[parentIndex]) {
            //无需真正交换，赋值即可
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (childIndex-1)/2;//上一个父节点
        }

        array[childIndex] = temp;

    }


    /**
     *
     * 删除堆顶
     *   删除堆的第一个点(顶点)，然后将最后一个节点补到堆顶上，然后再与子节点比较交换，下沉
     *
     * 删除=下沉
     *
     *
     *  tips:由于最大堆的堆顶一定是最大值，删除元素，也就是删除堆顶，经过调整，堆顶就变成第二大元素，所以不停的删除堆顶，堆顶就倒序变小。
     *  反之最小堆，不停的删除堆顶，堆顶值不断变大，就是正序。
     *
     */
    public static void downAdjust(int[] array, int parentIndex, int length) {
        //保存父节点，用于最后的赋值
        int temp = array[parentIndex];//顶点，最后一个顶点替补上来的
        int childIndex = parentIndex*2+1;//left_child

        //parentIndex是指针,连续交换时，孩子节点变成新的父节点
        while(childIndex<length) {
            //如果有右孩子,且右孩子小于左孩子的值，则定位到右孩子
            if(childIndex+1 < length && array[childIndex+1] < array[childIndex]) {
                childIndex++;
            }

            //如果父节点的值小于等于任何一个孩子的值，直接跳出，说明不用向下覆盖了
            if(temp<=array[childIndex]) {
                break;
            }
            //无需真正交换，赋值即可
            array[parentIndex] = array[childIndex];//父节点虽然被覆盖了，但是按照最小二叉堆的规则，父节点肯定比子节点大，继续覆盖
            parentIndex = childIndex;
            childIndex = parentIndex*2+1;
        }

        array[parentIndex] = temp;
    }


    /**
     *
     * 删除堆顶
     *  最大堆
     * @param array
     * @param parentIndex
     * @param length
     */
    public static void downAdjustBig(int[] array, int parentIndex, int length) {
        //保存父节点，用于最后的赋值
        int temp = array[parentIndex];//顶点，最后一个顶点替补上来的
        int childIndex = parentIndex*2+1;//left_child

        //parentIndex是指针,连续交换时，孩子节点变成新的父节点
        while(childIndex<length) {
            //如果有右孩子,且右孩子大于左孩子的值，则定位到右孩子
            if(childIndex+1 < length && array[childIndex+1] > array[childIndex]) {
                childIndex++;
            }

            //如果父节点的值大于等于任何一个孩子的值，直接跳出，说明下沉结束，不是每次都下沉到最底
            if(temp>=array[childIndex]) {
                break;
            }
            //无需真正交换，赋值即可
            array[parentIndex] = array[childIndex];//父节点虽然被覆盖了，但是按照最小二叉堆的规则，父节点肯定比子节点大，继续覆盖
            parentIndex = childIndex;
            childIndex = parentIndex*2+1;
        }

        array[parentIndex] = temp;
    }

    /**
     * 构建堆
     *   将无序二叉树调整为二叉堆
     *   本质是所有非叶子节点依次下沉
     *
     *
     */
    public static void build(int[] array) {
        //从最后一个非叶子节点开始，依次下沉调整
        //如length=9，最后一个非叶子节点last_p = (length-2)/2 = 3，last_p-- 依次向上找到其余非叶子节点也就是 index=3、2、1
        for(int i=(array.length-2)/2; i>=0; i--) {
            downAdjust(array,i,array.length);
        }
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