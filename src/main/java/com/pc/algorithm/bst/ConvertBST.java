package com.pc.algorithm.bst;

import com.pc.algorithm.datastructure.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author pengchao
 * @date 20:40 2021-01-14
 */
public class ConvertBST {

    public static void main(String[] args) {

        TreeNode treeNode = convertBST(TreeNode.buildBinarySearchTree());

        inTraversal(TreeNode.buildBinarySearchTree());

        System.out.println(treeNode);

    }

    /**
     * 解法一
     * @param root
     * @return
     */
    public static TreeNode convertBST(TreeNode root) {
        List<TreeNode<Integer>> res = new ArrayList<>();
        inTraversal(root,res);

        int sum = 0;
        for(int i=res.size()-1;i>=0;i--) {
            sum += res.get(i).val;
            res.get(i).val = sum;
        }

        return root;

    }

    public static void inTraversal(TreeNode root,List<TreeNode<Integer>> res) {
        if(root==null) {
            return;
        }

        inTraversal(root.left,res);
        res.add(root);

        inTraversal(root.right,res);

    }


    /**
     * 解法二
     *  先右后左 中序遍历
     */
    static int sum=0;
    public static TreeNode inTraversal(TreeNode<Integer> root) {
        if(root==null) {
            return null;
        }

//        if(root!=null) {
            inTraversal(root.right);

            sum+= root.val;
            root.val = sum;

            System.out.println("root="+root+",sum="+sum);

            inTraversal(root.left);
//        }

        return root;
    }


}
