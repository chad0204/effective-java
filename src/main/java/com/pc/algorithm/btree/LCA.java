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
        lowestCommonAncestor(root,new TreeNode(6),new TreeNode(9));

    }


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null) {
            return null;
        }

        //如果一个节点本身是另一个节点的祖先，那么这个节点就是LCA
        if (root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        System.out.println("root:"+root+",left:"+left+",right:"+right);

        // 情况 1 如果p和q都在以root为根的树中，且分别在两侧，那么left和right一定分别是p和q（从 base case 看出来的）
        if (left != null && right != null) {
            //可以确定root是祖先，怎么证明他是最近呢？因为后序遍历，从下到上
            return root;
        }
        // 情况 2 如果p和q都不在以root为根的树中，直接返回null
        if (left == null && right == null) {
            return null;
        }
        // 情况 3 如果p和q只有一个存在于root为根的树中，返回子树
        return left == null ? right : left;
    }
}
