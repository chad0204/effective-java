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


    public static void main(String[] args) {

        TreeNode root = buildNum();

        preTraversal(root);
        System.out.println();
        inTraversal(root);
        System.out.println();
        postTraversal(root);
        System.out.println();
        levelTraversal(root);


    }

    public T val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(){
    }

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

    /**
     *
     *  满二叉树
     *            1
     *       /        \
     *      2          3
     *    /   \       / \
     *   4    5     6    7
     *  / \  / \   / \   / \
     * 8  9 10 11 12 13 14 15
     *
     *
     *  n = 2^h-1
     *
     */
    public static TreeNode buildPerfectBtree() {
        //创建二叉树
        TreeNode<Integer> root = new TreeNode<>(1);

        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(3);

        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);

        root.left.left.left = new TreeNode<>(8);
        root.left.left.right = new TreeNode<>(9);
        root.left.right.left = new TreeNode<>(10);
        root.left.right.right = new TreeNode<>(11);
        root.right.left.left = new TreeNode<>(12);
        root.right.left.right = new TreeNode<>(13);
        root.right.right.left = new TreeNode<>(14);
        root.right.right.right = new TreeNode<>(15);

        return root;
    }


    /**
     *
     *         1
     *        / \
     *       2   3
     *      /   / \
     *     4   2   4
     *        /
     *       4
     *
     */
    public static TreeNode buildNumDuplicate() {
        //创建二叉树
        TreeNode<Integer> root = new TreeNode<>(1);	//根节点A
        root.left = new TreeNode<>(2);	//A的左子树
        root.right = new TreeNode<>(3);	//A的右子树
        root.left.left = new TreeNode<>(4);	//B的左子树
        root.right.left = new TreeNode<>(2);	//C的左子树
        root.right.right = new TreeNode<>(4);	//C的右子树
        root.right.left.left = new TreeNode<>(4);
        return root;
    }



    /**
     *
     *
     *                 6
     *               /   \
     *              4     8
     *            /  \   / \
     *           2    5 7   9
     *          /\
     *         1  3
     */
    public static TreeNode buildBinarySearchTree() {
        //创建二叉树
        TreeNode<Integer> root = new TreeNode<>(6);

        root.left = new TreeNode<>(4);
        root.left.left = new TreeNode<>(2);
        root.left.left.right = new TreeNode<>(3);
        root.left.right = new TreeNode<>(5);
        root.left.left.left = new TreeNode<>(1);

        root.right = new TreeNode<>(8);
        root.right.left = new TreeNode<>(7);
        root.right.right = new TreeNode<>(9);
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

    public static void inTraversal(TreeNode root) {
        if(root==null) {
            return;
        }
        inTraversal(root.left);
        System.out.print(root.val);
        inTraversal(root.right);

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
