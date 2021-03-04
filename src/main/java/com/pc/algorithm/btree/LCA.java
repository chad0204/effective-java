package com.pc.algorithm.btree;

import com.pc.algorithm.datastructure.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 *
 *
 *
 * @author pengchao
 * @date 10:27 2021-01-19
 */
public class LCA {


    public static void main(String[] args) {

        TreeNode root = TreeNode.buildNum();
        lowestCommonAncestor(root,new TreeNode(5),new TreeNode(6));

    }


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) { // 超过叶子节点，或者root为p、q中的一个直接返回
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q); // 返回左侧的p\q节点
        TreeNode right = lowestCommonAncestor(root.right,p,q); // 返回右侧的p\q节点
        if (left == null) {  // 都在右侧
            return right;
        }
        if (right == null) { // 都在左侧
            return left;
        }
        return root; // 在左右两侧
    }
}
