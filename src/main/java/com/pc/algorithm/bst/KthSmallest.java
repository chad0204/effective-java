package com.pc.algorithm.bst;

import com.pc.algorithm.datastructure.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 230. 二叉搜索树中第K小的元素
 *
 * @author pengchao
 * @date 20:02 2021-01-14
 */
public class KthSmallest {

    public static void main(String[] args) {
        System.out.println(kthSmallest(TreeNode.buildBinarySearchTree(),4));

        inTraversal(TreeNode.buildBinarySearchTree(),4);
    }

    /**
     * 解法一
     * @param root
     * @param k
     * @return
     */
    public static int kthSmallest(TreeNode root, int k) {
        List<TreeNode> res = new ArrayList<>();
        //经过中序遍历之后res中是升序排列的
        inTraversal(root,res);

        return (int) res.get(k-1).val;
    }

    private static void inTraversal(TreeNode root,List<TreeNode> res) {
        if(root==null) {
            return;
        }
        inTraversal(root.left,res);
        res.add(root);
        inTraversal(root.right,res);

    }


    /**
     * 解法二
     *  直接在中序遍历过程中处理
     */
    static int rank=0;
    static int res =0;
    private static void inTraversal(TreeNode root,int k) {
        if(root==null) {
            return;
        }
        inTraversal(root.left,k);
        rank++;
        if(k==rank) {
            res = (Integer) root.val;
            return;
        }
        inTraversal(root.right,k);

    }



}
