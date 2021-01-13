package com.pc.algorithm.btree;


/**
 * TODO
 *
 * @author pengchao
 * @date 17:22 2021-01-13
 */
public class Test {

    public static void main(String[] args) {



    }

    public Node connect(Node root) {
        if(root==null) {
            return root;
        }
        connect(root.left,root.right);
        return root;
    }

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



    //层序遍历
    // public void levelTraverse(Node root) {
    //     if(root ==null) {
    //         return;
    //     }

    //     Queue<Node> queue = new LinkedList<>();
    //     queue.add(root);
    //     Node curr;
    //     while(!queue.isEmpty()) {
    //         List<Node> temp = new ArrayList<>();
    //         int size = queue.size();
    //         for(int i=size;i>0;i--) {
    //             curr = queue.remove();
    //             temp.add(curr);
    //             if(curr.left!=null) {
    //                 queue.add(curr.left);
    //             }
    //             if(curr.right!=null) {
    //                 queue.add(curr.right);
    //             }
    //         }

    //         for(int i=0;i<temp.size();i++) {
    //             if(i+1<temp.size()) {
    //                 temp.get(i).next = temp.get(i+1);
    //             }
    //         }

    //     }

    // }



}
