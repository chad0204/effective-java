package com.pc.algorithm.btree;

import com.pc.algorithm.datastructure.TreeNode;

/**
 * 654. 最大二叉树
 *
 * 给定一个数组 构建最大二叉树
 *
 * 每次遍历前 找到最大值 构建root
 *
 * @author pengchao
 * @date 17:22 2021-01-13
 */
public class ConstructMaximumBinaryTree {

    public static void main(String[] args) {

//        TreeNode root = constructMaximumBinaryTree(new int[]{3,2,1,6,0,5});
        TreeNode root = constructMaximumBinaryTree(new int[]{3,2,1,6,8,1,3,5,4,0,5});
        System.out.println(root);
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {

        return build(nums,0,nums.length-1);
    }

    private static TreeNode build(int[] nums, int start, int end) {

        if(start>end) {
            return null;
        }

        int max = Integer.MIN_VALUE;
        int maxIndex=start;
        for(int i=start;i<=end;i++) {
            if(nums[i]>max) {
                max = nums[i];
                maxIndex=i;
            }
        }

        TreeNode<Integer> root = new TreeNode<>(max);

        root.left = build(nums,start,maxIndex-1);
        root.right = build(nums,maxIndex+1,end);

        return root;
    }

}
