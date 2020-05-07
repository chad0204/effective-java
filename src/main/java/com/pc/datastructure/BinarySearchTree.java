package com.pc.datastructure;


/**
 * 二叉树
 *
 *     二叉树的性能取决与插入顺序。
 *
 *     最好的情况是完全平衡，logN
 *     最坏的情况是按照顺序插入，会变成链表结构。
 *
 *     二叉树和快速排序是双胞胎，二叉树的根节点就是快排的切分点，左侧的键都比它小，右侧的键都比它大
 *
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
     * @param node
     * @param key
     * @param value
     */
    private Node put(Node node, K key, V value) {
        //如果key存在与以node为根节点的子树中则更新节点值为value，
        //否则将key和value封装成新的节点插入子树中
        if(node ==null) {
            return new Node(key,value,null,null,1);
        }

        int cmp = key.compareTo(node.key);
        if(cmp<0) {
            node.left = put(node.left,key,value);
        } else if(cmp>0) {
            node.right = put(node.right,key,value);
        } else {
            //存在更新
            node.value = value;
        }

        node.N = size(node.left)+size(node.right)+1;
        return node;

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

}
