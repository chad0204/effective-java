package com.pc.algorithm.datastructure;


import java.util.LinkedList;
import java.util.Queue;

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


    /**
     *         A
     *       /   \
     *      B     C
     *    /     / \
     *   D     E   F
     *   \         \
     *    G         H
     *
     */
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


    /**
     *         1
     *       /  \
     *      2    3
     *    /     / \
     *   4     5   6
     *   \        / \
     *    7      8   9
     *
     */
    public static TreeNode buildNum() {
        //创建二叉树
        TreeNode<Integer> root = new TreeNode<>(1);	//根节点A
        root.left = new TreeNode<>(2);	//A的左子树
        root.right = new TreeNode<>(3);	//A的右子树
        root.left.left = new TreeNode<>(4);	//B的左子树
        root.right.left = new TreeNode<>(5);	//C的左子树
        root.right.right = new TreeNode<>(6);	//C的右子树
        root.left.left.right = new TreeNode<>(7);	//D的右子树
        root.right.right.left = new TreeNode<>(8);
        root.right.right.right = new TreeNode<>(9);
        return root;
    }


    public static void preTraversal(TreeNode root) {
        if(root==null) {
            return;
        }
        System.out.print(root.val);
        preTraversal(root.left);
        preTraversal(root.right);

    }

    public static void midTraversal(TreeNode root) {
        if(root==null) {
            return;
        }
        midTraversal(root.left);
        System.out.print(root.val);
        midTraversal(root.right);

    }

    public static void postTraversal(TreeNode root) {
        if(root==null) {
            return;
        }
        postTraversal(root.left);
        postTraversal(root.right);
        System.out.print(root.val);

    }

    /**
     * 层序遍历
     * @param root
     */
    public static void levelTraversal(TreeNode root) {

        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curr;
        queue.add(root);//addLast
        while (!queue.isEmpty()) {
            curr = queue.remove();//removeFast
            System.out.print(curr.val);
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

    /**
     * 层序遍历-分层
     * @param root
     */
    public static void levelTraverse_(TreeNode root) {
        if(root==null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i=size;i>0;i--) {
                TreeNode node = queue.remove();
                System.out.print(node);
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
        }
    }


    @Override
    public String toString() {
        return ""+val;
    }
}
