package com.pc.algorithm.datastructure.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap+双向链表
 *
 *
 *
 * put , 更新 ->移动到队尾, 新增->链入队尾 ，判断容量 删除队头
 *
 * get , 移动到队尾
 *
 * remove ，删除队头
 *
 *
 * 删除节点
 * 移动节点
 * 链入队尾
 *
 *
 *
 * @author pengchao
 * @date 19:38 2021-01-20
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(1);

        lru.set(2,1);

        System.out.println(lru.get(2));


    }


    /**
     * 牛客网 方法签名
     *
     * [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
     *
     * opt = 1,为set操作，后面两个数为set(x,y)
     * opt = 2,为get操作，后面两个数为get(x)，并返回一个结果
     *
     * @param operators
     * @param k
     * @return
     */
    public int[] LRU (int[][] operators, int k) {

        this.capacity = k;
        this.size = 0;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;

        //计算返回多少个结果，也就是计算多少个get操作
        int len = 0;
        for (int[] operator : operators) {
            if (operator[0] == 2) {
                len++;
            }
        }

        int[] res = new int[len];
        for(int i = 0, j = 0; i < operators.length; i++) {
            if(operators[i][0] == 1) {
                set(operators[i][1], operators[i][2]);
            } else {
                res[j++] = get(operators[i][1]);
            }
        }
        return res;
    }





    private Map<Integer,Node> cache = new HashMap<>();
    private int capacity;//容量
    private int size;//大小
    private Node head;//头
    private Node tail;//尾


    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    class Node {
        int value;
        int key;
        Node prev;
        Node next;

        public Node() {
        }

        public Node(int key, int value) {
            this.value = value;
            this.key = key;
        }


    }


    /**
     * 用过之后要移动到队尾
     * @param key
     * @return
     */
    public int get(int key) {
        Node node = cache.get(key);
        if(node==null) {
            return -1;
        }
        //移动到队尾
        moveToLast(node);
        return node.value;
    }

    /**
     * 插入之后，要判断容量并删除队头
     * @param key
     * @param value
     */
    public void set(int key, int value) {
        Node node = cache.get(key);
        if(node != null) {
            //已存在，更新，移动到队尾
            node.value = value;
            moveToLast(node);
        } else {
            //加入队尾
            Node newNode = new Node(key,value);
            addToLast(newNode);
            cache.put(key,newNode);
            size++;
            //判断容量，删除队头
            if(size>capacity) {
                Node head = removeHead();
                cache.remove(head.key);
                size--;
            }
        }
    }


    /**
     * 移动到队尾
     *  +----+              +----+              +----+
     *  |head|  -- next--> ｜prev｜ -- next-->  ｜tail｜
     *  |    |  <-- prev-- ｜    ｜ <-- prev--  ｜    ｜
     *  +----+             +----+               +----+
     */
    private void moveToLast(Node node) {
        //原位置删除
        removeNode(node);
        //添加到队尾
        addToLast(node);
    }

    /**
     * 添加到队尾
     */
    private void addToLast(Node node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }


    /**
     * 删除队头
     */
    private Node removeHead() {
        Node del = head.next;
        removeNode(del);
        return del;
    }



    /**
     * 删除节点
     *  +----+              +----+              +----+
     *  |    |  -- next--> ｜    ｜ -- next-->  ｜    ｜
     *  |    |  <-- prev-- ｜    ｜ <-- prev--  ｜    ｜
     *  +----+             +----+               +----+
     *
     *
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}
