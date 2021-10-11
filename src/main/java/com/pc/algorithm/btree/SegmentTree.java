package com.pc.algorithm.btree;

/**
 * @author pengchao
 * @description: TODO 类描述
 * @date 2021-09-27 10:45 上午
 */
public class SegmentTree {

    int[] segmentTree;
    int n;

    public SegmentTree(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            segmentTree = new int[n * 2];
            //把原数组拷贝到新数组的后半部分
            System.arraycopy(nums, 0, segmentTree, 0 + n, n);
            //计算区域和
            for (int i = n - 1; i > 0; i--) {
                segmentTree[i] = segmentTree[i * 2] + segmentTree[i * 2 + 1];
            }
        }
    }


    public static void main(String[] args) {

        int[] array = new int[]{18, 17, 13, 19, 15, 11, 20, 12, 33, 25};


        SegmentTree segmentTree = new SegmentTree(array);


        System.out.println();
    }
}
