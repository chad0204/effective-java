package com.pc.algorithm.datastructure;


/**
 * 二叉树的节点
 *
 * @author pengchao
 * @date 14:40 2021-01-11
 */
public class TreeNode<T> {

    public T val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(T x){
        val = x;
    }

    public TreeNode(T val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return ""+val;
    }
}
