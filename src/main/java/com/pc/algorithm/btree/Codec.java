package com.pc.algorithm.btree;

import com.pc.algorithm.datastructure.TreeNode;
import java.util.*;

/**
 * 297. 二叉树的序列化与反序列化
 *
 *  前序可以
 *  后序，反序列化时要从后面开始构造，removeLast
 *  中序，无法从中间构建root
 *  层序 ok
 *
 * @author pengchao
 * @date 16:43 2021-01-18
 */
public class Codec {


    public static void main(String[] args) {

        TreeNode treeNode = TreeNode.buildNum();

        TreeNode.inTraversal(treeNode);

        System.out.println();

        String str = serialize(treeNode);

        TreeNode.inTraversal(deserialize(str));

    }

//    private static String serialize(TreeNode root) {
//        List<String>  list = new ArrayList<>();
//        serializePreTraversal(root,list);
//
//        StringBuilder builder = new StringBuilder();
//        for(String str :list) {
//            builder.append(1).append(",");
//        }
//        return builder.toString();
//    }
//
//    private static void serializePreTraversal(TreeNode root,List<String>  list) {
//        if(root==null) {
//            list.add("#");
//            return;
//        }
//
//        list.add(""+root.val);
//        serializePreTraversal(root.left,list);
//        serializePreTraversal(root.right,list);
//    }
//
//    public static TreeNode deserialize(String data) {
//
//        Queue<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
//
//        return deserializePreTraversal(list);
//    }
//
//    private static TreeNode deserializePreTraversal(Queue<String>  list) {
//        if(list.size()==0) {
//            return null;
//        }
//
//        String val = list.remove();
//        if(val.equals("#")) {
//            return null;
//        }
//        TreeNode root = new TreeNode(Integer.valueOf(val));
//
//        root.left = deserializePreTraversal(list);
//        root.right = deserializePreTraversal(list);
//
//        return root;
//    }




    public static String serialize(TreeNode root) {
        StringBuilder data = new StringBuilder();
        return serialize(root,data).toString();

    }

    public static StringBuilder serialize(TreeNode root,StringBuilder data) {
        if(root==null) {
            return data.append("#").append(",");
        }
        data.append(root.val).append(",");
        serialize(root.left,data);
        serialize(root.right,data);

        return data;
    }



    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(queue);
    }

    public static TreeNode deserialize(Queue<String> queue) {
        if(queue.isEmpty()) {
            return null;
        }

        String val = queue.remove();
        if(val.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserialize(queue);
        node.right = deserialize(queue);

        return node;
    }





}
