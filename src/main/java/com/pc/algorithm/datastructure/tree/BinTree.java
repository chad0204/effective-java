package com.pc.algorithm.datastructure.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树
 *
 *      二叉树是无序的
 *      给出一个数组，是无法知道构建的二叉树是啥样的，但是给出一个前序(后序、中序)遍历，可以推导原结构
 *
 * @author pengchao
 * @date 14:27 2020-12-23
 */
public class BinTree {


    Random random = new Random(47);

    private TreeNode root;

    public class TreeNode {

        String val;
        TreeNode left;
        TreeNode right;
        TreeNode(String x){
            val = x;
        }

        public TreeNode(String val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return ""+val;
        }
    }


    /**
     * 构建完成后，还是一条链表
     * @param nodeList
     * @return
     */
    public TreeNode build(LinkedList<String> nodeList) {
        TreeNode node = null;
        if(nodeList == null || nodeList.isEmpty()){
            return null;
        }
        String v = nodeList.removeFirst();

        if(v!=null){
            node = new TreeNode(v);
            if(root==null) {
                root = node;
            }
            node.left = build(nodeList);
            node.right = build(nodeList);
        }
        return node;
    }


    public TreeNode build() {
        //创建二叉树
        TreeNode root = new TreeNode("A");	//根节点A
        root.left = new TreeNode("B");	//A的左子树
        root.right = new TreeNode("C");	//A的右子树
        root.left.left = new TreeNode("D");	//B的左子树
        root.left.left.right = new TreeNode("G");	//D的右子树
        root.right.left = new TreeNode("E");	//C的左子树
        root.right.right = new TreeNode("F");	//C的右子树
        return root;
    }






    public static void main(String[] args) {

        BinTree tree = new BinTree();

        TreeNode root = tree.build();

        levelTraversal(root);

        System.out.println();


    }

    /**
     * 层序遍历
     * @param t
     */
    public static void levelTraversal(TreeNode t) {
        if (t == null)
            return;
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        TreeNode curr;
        queue.add(t);
        while (!queue.isEmpty()) {
            curr = queue.remove();
            System.out.println(curr.val);
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }
}
