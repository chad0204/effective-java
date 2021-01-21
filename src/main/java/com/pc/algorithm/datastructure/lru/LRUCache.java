package com.pc.algorithm.datastructure.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap+双向链表
 *
 * @author pengchao
 * @date 19:38 2021-01-20
 */
public class LRUCache {

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(1);

        lru.put(2,1);

        System.out.println(lru.get(2));


    }

    int capacity;
    int size;

    Map<Integer,Node> cache = new HashMap<>();

    Node head;
    Node tail;

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
    public void put(int key, int value) {
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
    public void moveToLast(Node node) {
        //原位置删除
        removeNode(node);
        //添加到队尾
        addToLast(node);
    }

    /**
     * 添加到队尾
     */
    public void addToLast(Node node) {
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
    }


    /**
     * 删除队头
     */
    public Node removeHead() {
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
    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}
