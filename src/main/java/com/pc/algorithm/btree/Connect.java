package com.pc.algorithm.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 *
 * @author pengchao
 * @date 19:32 2021-01-13
 */
public class Connect {

    public Node connect(Node root) {
        if(root==null) {
            return root;
        }
        connect(root.left,root.right);
        return root;
    }

    /**
     * 递归
     * @param node1
     * @param node2
     */
    public void connect(Node node1,Node node2) {
        if(node1==null || node2==null) {
            return;
        }
        node1.next = node2;

        connect(node1.left,node1.right);
        connect(node2.left,node2.right);
        //连接跨越
        connect(node1.right,node2.left);

    }


    /**
     * 层序遍历
     */
     public void levelTraverse(Node root) {
         if(root ==null) {
             return;
         }

         Queue<Node> queue = new LinkedList<>();
         queue.add(root);
         Node curr;
         while(!queue.isEmpty()) {
             List<Node> temp = new ArrayList<>();
             int size = queue.size();
             for(int i=size;i>0;i--) {
                 curr = queue.remove();
                 temp.add(curr);
                 if(curr.left!=null) {
                     queue.add(curr.left);
                 }
                 if(curr.right!=null) {
                     queue.add(curr.right);
                 }
             }
             //将同一层的节点连接
             for(int i=0;i<temp.size();i++) {
                 if(i+1<temp.size()) {
                     temp.get(i).next = temp.get(i+1);
                 }
             }

         }

     }


}

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
