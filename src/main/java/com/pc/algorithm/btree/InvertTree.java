package com.pc.algorithm.btree;

import com.pc.algorithm.datastructure.TreeNode;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 226. 翻转二叉树
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * @author pengchao
 * @date 17:56 2021-01-13
 */
public class InvertTree {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildString();
        levelTraverse(root);

        System.out.println();


    }

    /**
     * 层序遍历
     * @param root
     */
    public static void levelTraverse(TreeNode root) {
        if(root==null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode curr;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=size;i>0;i--) {
                curr = queue.remove();
                if(curr.left!=null) {
                    queue.add(curr.left);
                }
                if(curr.right!=null) {
                    queue.add(curr.right);
                }

                TreeNode temp = curr.left;
                curr.left = curr.right;
                curr.right = temp;
            }
        }
    }

    /**
     * 递归
     */
    public static void preTraverse(TreeNode root) {
        if(root==null) {
            return;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        preTraverse(root.left);
        preTraverse(root.right);


    }
}
