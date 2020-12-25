package com.pc.algorithm.datastructure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * 二叉树
 *
 *      二叉树是无序的
 *      给出一个数组，是无法知道构建的二叉树是啥样的，但是给出一个前序(后序、中序)遍历，可以推导原结构
 *
 * @author pengchao
 * @date 14:27 2020-12-23
 */
public class BinTree<V> {


    Random random = new Random(47);

    private TreeNode root;

    public class TreeNode {

        V val;
        TreeNode left;
        TreeNode right;
        TreeNode(V x){
            val = x;
        }

        public TreeNode(V val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 构建完成后，还是一条链表
     * @param nodeList
     * @return
     */
    public TreeNode build(LinkedList<V> nodeList) {
        TreeNode node = null;
        if(nodeList == null || nodeList.isEmpty()){
            return null;
        }
        V v = nodeList.removeFirst();

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



    public static void main(String[] args) {


        BinTree<String> binTree = new BinTree<>();




        LinkedList<String> list = new LinkedList<>(Arrays.asList("A","B","C","D","E","F","G","H","I"));










    }
}
