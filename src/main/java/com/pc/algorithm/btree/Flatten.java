package com.pc.algorithm.btree;

import com.pc.algorithm.datastructure.TreeNode;

/**
 * 114. 二叉树展开为链表
 *
 * @author pengchao
 * @date 19:49 2021-01-13
 */
public class Flatten {

    public static void main(String[] args) {
        preTraverse(TreeNode.buildString());
    }


    public static void preTraverse(TreeNode root) {
        if(root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        root.right = left;
        root.left = null;


        preTraverse(left);

        preTraverse(right);

    }
}
