package com.pc.algorithm.btree;

import com.pc.algorithm.datastructure.TreeNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 114. 二叉树展开为链表
 *
 * @author pengchao
 * @date 19:49 2021-01-13
 */
public class Flatten {

    public static void main(String[] args) {
        TreeNode root = TreeNode.buildNum();
        postTraverse(root);

        flatten(root);


        System.out.println(root);

    }


    public static void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        //前序遍历的顺序存储到list，并未改变原树结构
        for(int i=0;i<list.size();i++) {
            if(i+1>=list.size()) {
                break;
            }
            TreeNode curr = list.get(i);
            curr.left =null;
            curr.right = list.get(i+1);
        }
    }

    public static void preorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }


    /**
     * 后序遍历解法 看不懂
     * @param root
     */
    public static void postTraverse(TreeNode root) {
        if(root == null) {
            return;
        }

        postTraverse(root.left);
        postTraverse(root.right);

        // 1、左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 2、将左子树作为右子树
        root.left = null;
        root.right = left;

        // 3、将原先的右子树接到当前右子树的末端
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        p.right = right;

    }
}
