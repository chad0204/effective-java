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

    public static TreeNode buildString() {
        //创建二叉树
        TreeNode root = new TreeNode<>("A");	//根节点A
        root.left = new TreeNode<>("B");	//A的左子树
        root.right = new TreeNode<>("C");	//A的右子树
        root.left.left = new TreeNode<>("D");	//B的左子树
        root.right.left = new TreeNode<>("E");	//C的左子树
        root.right.right = new TreeNode<>("F");	//C的右子树
        root.left.left.right = new TreeNode<>("G");	//D的右子树
        root.right.right.right = new TreeNode<>("H");
        return root;
    }


    @Override
    public String toString() {
        return ""+val;
    }
}
