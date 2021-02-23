package com.pc.algorithm.bfs;

import com.pc.algorithm.datastructure.TreeNode;
import java.util.*;

/**
 * 二叉树
 *
 * @author pengchao
 * @date 14:27 2020-12-23
 */
public class BinTree {


    public static void main(String[] args) {

        System.out.println(minDepth(TreeNode.buildNum()));


        System.out.println(levelOrder(TreeNode.buildNum()));


    }

    /**
     * 给定一个二叉树，找出其最小深度。
     *
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。也就是最小层数
     *
     * 说明：叶子节点是指没有子节点的节点。
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {

        LinkedList<TreeNode> queue = new LinkedList<>();
        if(root==null) {
            return 0;
        }

        queue.addLast(root);

        int res = 1;

        while (!queue.isEmpty()) {
            for(int i= queue.size();i>0;i--) {
                TreeNode curr = queue.removeFirst();

                if(curr.left!=null) {
                    queue.addLast(curr.left);
                }
                if(curr.right!=null) {
                    queue.addLast(curr.right);
                }

                if(curr.left==null && curr.right==null) {
                    return res;
                }
            }
            res++;
        }

        return res;
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



    public static ArrayList<ArrayList<Integer>> levelOrder (TreeNode<Integer> root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root==null) {
            return res;
        }

        LinkedList<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while(!queue.isEmpty()) {
            ArrayList<Integer> level = new ArrayList<>();
            int size = queue.size();
            for(int i=0;i<size;i++) {
                TreeNode<Integer> node = queue.remove();
                queue.peek();
                level.add(node.val);
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }

        return res;
    }

}
