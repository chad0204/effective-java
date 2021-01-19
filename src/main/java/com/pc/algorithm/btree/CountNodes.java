package com.pc.algorithm.btree;


import com.pc.algorithm.datastructure.TreeNode;

/**
 * 222. 完全二叉树的节点个数
 *
 * @author pengchao
 * @date 14:39 2021-01-19
 */
public class CountNodes {


    public static void main(String[] args) {

        System.out.println(countNodesForBtree(TreeNode.buildNum()));

        System.out.println(countNodesForBtree(TreeNode.buildPerfectBtree()));

        System.out.println(countNodesForBtree(TreeNode.buildPerfectBtree()));
    }


    /**
     * 计算一颗二叉树的节点数
     * @param root
     * @return
     */
    public static int countNodesForBtree(TreeNode root) {
        if(root==null) {
            return 0;
        }
        return 1+countNodesForBtree(root.left)+countNodesForBtree(root.right);
    }

    /**
     * 计算一棵满二叉树的节点数
     *
     *
     * 完全二叉树，除最后一层，其他层都是满的，且最后一层要靠右
     * 满二叉树是一种特殊的完全二叉树，每一层的节点都是满的，当高度为h时，节点数为2^h-1
     *
     */
    public static int countNodesForPerfectBtree(TreeNode root) {
        if(root==null) {
            return 0;
        }

        int h = 0;
        while (root!=null) {
            root = root.left;
            h++;
        }

        //2^h -1
        return (int) Math.pow(2,h)-1;
    }


    /**
     *
     *  计算一棵完全二叉树
     *            1
     *       /        \
     *      2          3
     *    /   \       / \
     *   4    5     6    7
     *  / \  / \   / \
     * 8  9 10 11 12 13
     *
     *
     *  一棵完全二叉树的两棵子树，至少有一棵是满二叉树
     *
     *  可以看到以2 为root和以6为root的子树是满二叉树
     *
     *
     */
    public static int countNodesForCompeleteBtree(TreeNode root) {
        if(root==null) {
            return 0;
        }

        TreeNode l = root,r = root;
        int hl = 0; int hr = 0;
        while (l!=null) {
            l = l.left;
            hl++;
        }

        while (r!=null) {
            r = r.right;
            hr++;
        }

        if(hl==hr) {
            return (int) Math.pow(2,hl)-1;
        }

        return 1+countNodesForCompeleteBtree(root.left)+countNodesForCompeleteBtree(root.right);
    }


}
