package com.pc.algorithm.bfs;

import java.util.*;

/**
 * 二叉树
 *
 * @author pengchao
 * @date 14:27 2020-12-23
 */
public class BinTree {

    private TreeNode root;

    public class TreeNode<T> {

        T val;
        TreeNode left;
        TreeNode right;
        TreeNode(T x){
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



    public TreeNode buildString() {
        //创建二叉树
        TreeNode<Integer> root = new TreeNode<>(1);	//根节点A
        root.left = new TreeNode<>(2);	//A的左子树
        root.right = new TreeNode<>(3);	//A的右子树
        root.left.left = new TreeNode<>(4);	//B的左子树
        root.right.left = new TreeNode<>(5);	//C的左子树
        root.right.right = new TreeNode<>(6);	//C的右子树
        root.left.left.right = new TreeNode<>(7);	//D的右子树
        root.right.right.right = new TreeNode<>(8);
        return root;
    }




    /**
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。也就是最小层数
     *
     * 说明：叶子节点是指没有子节点的节点。
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {

        LinkedList<TreeNode> queue = new LinkedList<>();
        if(root==null) {
            return 0;
        }

        queue.addLast(root);

        int res = 1;

        while (!queue.isEmpty()) {
            for(int i= queue.size();i>0;i--) {
                TreeNode curr = queue.removeFirst();

                if(curr.left!=null) {
                    queue.addLast(curr.left);
                }
                if(curr.right!=null) {
                    queue.addLast(curr.right);
                }

                if(curr.left==null && curr.right==null) {
                    return res;
                }
            }
            res++;
        }

        return res;
    }



    public static void main(String[] args) {

        BinTree tree = new BinTree();
        TreeNode root = tree.buildString();
        System.out.println(minDepth(root));





    }

}
