package com.pc.algorithm.datastructure.tree;


import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉搜索树
 *
 *     二叉搜索树的性能取决与插入顺序。
 *
 *     最好的情况是完全平衡，logN
 *     最坏的情况是按照顺序插入，会变成链表结构。
 *
 *     二叉搜索树和快速排序是双胞胎，二叉树的根节点就是快排的切分点，左侧的"键"都比它小，右侧的"键"都比它大（键指的是序号，0123456。。）
 *
 * 二叉树
 *
 *     在二叉树的第i层上最多有2^(i-1) 个节点
 *     若二叉树按照从上到下从左到右依次编号，则若某节点编号为k，则其左右子树根节点编号分别为2k和2k+1
 *
 *     二叉树分类：满二叉树，完全二叉树
 *        高度为h，由2^h-1个节点构成的二叉树称为满二叉树。
 *        高度为h，1～h-1层都达到最大节点数，h层可以不满，但是节点必须都在该层最左侧。完全二叉树
 *
 *     在完全二叉树中，具有n个节点的完全二叉树的深度为[log2n]+1，其中[log2n]+1是向下取整。满二叉树的深度为k=log2(n+1)
 *
 *
 *     满二叉树是一种节点数最多的完全二叉树，二叉堆是一种节点的父节点值比左右节点的值都大或小的完全二叉树
 *
 *     1+2+4+8+...+n = 2^n - 1
 *
 *
 * 二叉查找树实现
 *
 * @author dongxie
 * @date 14:07 2020-04-30
 */
public class BinarySearchTree<K extends Comparable<K>,V> {

    private Node root;//根节点

    private class Node {

        private K key;//键
        private V value;//值
        private Node left,right;//指向子树的左右链接
        private int N;//以该节点为根的子节点数

        public Node(K key, V value, Node left, Node right, int n) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            N = n;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key+""+value;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x!=null ? x.N : 0;
    }

    public V get(K key) {
        return get(root, key);
    }

    /**
     * 在以x为根结点的子树中查找并返回key所对应的值;
     * @param node
     * @param key
     * @return
     */
    private V get(Node node, K key) {
        //如果找不到则返回null
        if(node ==null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp<0) {
            return get(node.left,key);//查找的比根节点小，走左子树
        } else if(cmp>0) {
            return get(node.right,key);
        } else {
            return node.value;
        }
    }

    public void put(K key, V value) {
        //从根节点开始查找key的节点，存在则更新，不存在则创建
        root = put(root,key,value);
    }

    /**
     * 插入元素，存在即更新
     * @param parent
     * @param key
     * @param value
     */
    private Node put(Node parent, K key, V value) {
        //如果key存在与以parent为根节点的子树中则更新节点值为value，
        //否则将key和value封装成新的节点插入子树中
        if(parent ==null) {
            return new Node(key,value,null,null,1);
        }

        int cmp = key.compareTo(parent.key);
        if(cmp<0) {
            parent.left = put(parent.left,key,value);
        } else if(cmp>0) {
            parent.right = put(parent.right,key,value);
        } else {
            //存在更新
            parent.value = value;
        }

        parent.N = size(parent.left)+size(parent.right)+1;
        return parent;

    }


    public K min() {
        Node node;
        return (node=min(root)) !=null ? node.key : null;
    }

    /**
     * 最小键（最大键max同理）
     * @param node
     * @return
     */
    private Node min(Node node) {
        if(node==null) {
            return null;
        }
        if(node.left==null) {
            return node;
        }
        return min(node.left);
    }

    public K floor(K key) {
        Node x = floor(root, key);
        return x!=null ? x.key : null;
    }

    /**
     * 向下取整,小于等于key的最大键 （向上取整ceil同理）
     * @param node 节点
     * @param key 键
     * @return Node
     */
    private Node floor(Node node, K key) {
        if(node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp==0) {
            return node;
        }
        else if(cmp<0) {
            return floor(node.left,key);
        }
        Node t = floor(node.right, key);//键比当前节点大
        if(t!=null) {
            return t;
        } else {
            return node;//找不到就范围最后的叶子节点
        }
    }


    public K select(int index) {
        Node node = select(root,index);
        return node!=null ? node.key : null;
    }

    /**
     * 查找排名为index的节点。如果左子树数量为t,t>index,那么就在左边找，否则就在右边找
     * @param node
     * @param index
     * @return
     */
    private Node select(Node node, int index) {
        if(node==null) {
            return null;
        }
        int t = size(node.left);
        if(t>index) {
            return select(node.left,index);//index在左边
        } else if(t<index) {
            return select(node.right,index-t-1);//左子树不够，那么找右子树的第index-t-1位
        } else {
            return node;
        }
    }


    public int rank(K key) {
        return rank(key,root);
    }

    /**
     * key的排名
     * @param key
     * @param node
     * @return
     */
    private int rank(K key, Node node) {
        if(node ==null) {
            return 0;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            return rank(key, node.left);//递归计算左子树的排名
        else if(cmp > 0)
            return 1 + size(node.left) + rank(key, node.right);//左子树+根节点+右子树的排名
        else
            return size(node.left);//就是左子树大小
    }

    /**
     * 删除节点x
     *   1.找到节点x的右子树的最小节点，用这个节点替换x
     *
     */
    private int delete(Node node) {
        return 0;
    }




    public void traversal() {
        System.out.println("前序");
        preTraversal(root);
        System.out.println("\n中序");
        inTraversal(root);
        System.out.println("\n后序");
        postTraversal(root);
        System.out.println("\n层序");
        levelTraversal(root);
        System.out.println();
    }

    /**
     *  前序遍历
     */
    private void preTraversal(Node node) {
        if(node == null){
            return;
        }
        System.out.print(node.key+""+node.value+"  ");
        preTraversal(node.left);
        preTraversal(node.right);
    }


    /**
     * 中序遍历
     */
    private void inTraversal(Node node){
        if(node == null){
            return;
        }
        inTraversal(node.left);
        System.out.print(node.key+""+node.value+"  ");
        inTraversal(node.right);
    }

    /**
     * 后序遍历
     */
    public void postTraversal(Node node){
        if(node == null){
            return;
        }
        postTraversal(node.left);
        postTraversal(node.right);
        System.out.print(node.key+""+node.value+"  ");
    }

    /**
     * 层序遍历
     */
    public void levelTraversal(Node node){
        if (node == null)
            return;
        Queue<Node> queue = new LinkedBlockingQueue<>();
        Node curr;
        queue.add(node);
        while (!queue.isEmpty()) {
            curr = queue.remove();
            System.out.println(curr.value);
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }



    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                '}';
    }


    public static void main(String[] args) {
        BinarySearchTree<Integer,String> bst = new BinarySearchTree<>();

        bst.put(4,"D");
        bst.put(2,"B");
        bst.put(6,"F");
        bst.put(7,"G");
        bst.put(1,"A");
        bst.put(3,"C");
        bst.put(5,"E");
        bst.put(10,"I");
        bst.put(9,"H");

        bst.put(8,"V");


        bst.traversal();



    }


}
