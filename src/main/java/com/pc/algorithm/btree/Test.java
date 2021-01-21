package com.pc.algorithm.btree;


import com.pc.algorithm.datastructure.TreeNode;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author pengchao
 * @date 17:22 2021-01-13
 */
public class Test {


    public static void main(String[] args) {
        mergeTrees(TreeNode.buildNum(),TreeNode.buildNumDuplicate());
    }

    public static TreeNode mergeTrees(TreeNode<Integer> t1, TreeNode<Integer> t2) {


        if(t1==null) {
            return t2;
        }

        if(t2 == null) {
            return t1;
        }


        t1.val = t1.val+t2.val;
        t1.left = mergeTrees(t1.left,t2.left);
        t1.right = mergeTrees(t1.right,t2.right);

        return t1;
    }

    public static TreeNode mergeTreesBFS(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();


        while (t1!=null && t2!=null) {

        }

        return t1;
    }



}
