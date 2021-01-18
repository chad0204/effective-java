package com.pc.algorithm.bst;

import com.pc.algorithm.datastructure.TreeNode;

/**
 *
 *
 * @author pengchao
 * @date 14:54 2021-01-16
 */
public class BST {


    public static void main(String[] args) {

        TreeNode root = TreeNode.buildBinarySearchTree();

        root = deleteNode(root,6);

        System.out.println();

    }

    /**
     * 删除任意节点
     * @param root
     * @param key
     * @return
     */
    public static TreeNode deleteNode(TreeNode<Integer> root, int key) {
        if(root==null) {
            return null;
        }

        if(key == root.val) {
            //如果只有一个子节点，那么另一个子节点接替
            if(root.left==null) {
                return root.right;
            }
            if(root.right==null) {
                return root.left;
            }
            //左右子节点都存在，采用右边最小值接替自己的方式
            //1.替换左右指针
//            TreeNode curr = root;
//            root = getMin(root.right);
//            root.right = deleteMin(curr.right);
//            root.left = curr.left;
            //2.更新值
            TreeNode<Integer> minRight = getMin(root.right);
            root.val = minRight.val;
            root.right = deleteNode(root.right,minRight.val);

            //tips:还可以使用左边最大值替换法

        } else if(key < root.val) {
            root.left = deleteNode(root.left,key);
        } else if(key > root.val) {
            root.right = deleteNode(root.right,key);
        }
        return root;
    }


    public static TreeNode getMin(TreeNode root) {
        if(root == null) {
            return null;
        }
        if(root.left!=null) {
            return getMin(root.left);
        } else {
            return root;
        }
    }

    public static TreeNode deleteMin(TreeNode root) {
        if(root == null) {
            return null;
        }
        //没有左子树
        if(root.left==null) {
            return root.right;
        }
        root.left = deleteMin(root.left);
        return root;
    }

    /**
     * 验证合法性
     */
    public static boolean isValidBST(TreeNode root) {

        return isValidBST(root,null,null);
        //左边的一定比root小，右边的一定比root大

    }

    public static boolean isValidBST(TreeNode<Integer> root,TreeNode<Integer> min ,TreeNode<Integer> max) {
        if(root==null) {
            return true;
        }

        if(min!=null && root.val <= min.val) {
            return false;
        }

        if(max!=null &&  root.val >= max.val) {
            return false;
        }

        return isValidBST(root.left,min,root)
                && isValidBST(root.right,root,max);

    }


}
