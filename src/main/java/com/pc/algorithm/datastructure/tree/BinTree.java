package com.pc.algorithm.datastructure.tree;

import com.pc.algorithm.datastructure.TreeNode;
import java.util.*;

/**
 * 二叉树
 *
 * @author pengchao
 * @date 14:27 2020-12-23
 */
public class BinTree {

    private TreeNode root;


    /**
     * 构建完成后，还是一条链表
     * @param nodeList
     * @return
     */
    public TreeNode<String> build(LinkedList<String> nodeList) {
        TreeNode node = null;
        if(nodeList == null || nodeList.isEmpty()){
            return null;
        }
        String v = nodeList.removeFirst();

        if(v!=null){
            node = new TreeNode<>(v);
            if(root==null) {
                root = node;
            }
            node.left = build(nodeList);
            node.right = build(nodeList);
        }
        return node;
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
            System.out.println(curr.val);
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }


    /**
     * 分层打印
     * @param root
     */
    public static void levelTraversal1(TreeNode root) {

        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curr;
        queue.add(root);//addLast


        while (!queue.isEmpty()) {
            //记录每一层
            List<TreeNode> temp = new ArrayList<>();
            for(int i=queue.size();i>0;i--) {
//            for(int i=0;i<queue.size();i++) {//不行，这样造成条件是变化的
                curr = queue.remove();//removeFast
                temp.add(curr);
                if (curr.left != null)
                    queue.add(curr.left);

                if (curr.right != null)
                    queue.add(curr.right);
            }
            System.out.println(temp);
        }
    }


    /**
     * 之字形分层打印
     * @param root
     */
    public static void levelTraversal2(TreeNode root) {

        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode curr;
        queue.add(root);//addLast

        int level = 0;
        while (!queue.isEmpty()) {
            //记录每一层
            LinkedList<TreeNode> temp = new LinkedList<>();
            for(int i=queue.size();i>0;i--) {
                curr = queue.remove();//removeFast
                if(level%2==0) {
                    temp.addLast(curr);
                } else {
                    temp.addFirst(curr);
                }
                if (curr.left != null)
                    queue.add(curr.left);

                if (curr.right != null)
                    queue.add(curr.right);
            }
            level++;
            System.out.println(temp);
        }
    }



    public static void main(String[] args) {

//        BinTree tree = new BinTree();
        TreeNode root = TreeNode.buildString();

        preTraversal(root);
        System.out.println();
        inTraversal(root);
        System.out.println();
        postTraversal(root);

        System.out.println();

        levelTraversal1(root);

        System.out.println();

    }


}
