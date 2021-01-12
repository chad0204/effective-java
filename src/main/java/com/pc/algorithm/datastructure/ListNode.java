package com.pc.algorithm.datastructure;

/**
 * 单链表节点
 *
 * @author pengchao
 * @date 10:42 2021-01-12
 */
public class ListNode<T> {
    public T val;
    public ListNode next;
    public ListNode(T x) { val = x; }


    public static ListNode buildList() {
        //创建二叉树
        ListNode head = new ListNode<>(1);	//根节点A

        head.next = new ListNode<>(2);
        head.next.next = new ListNode<>(3);
        head.next.next.next = new ListNode<>(4);
        head.next.next.next.next = new ListNode<>(5);
        head.next.next.next.next.next = new ListNode<>(6);
        head.next.next.next.next.next.next = new ListNode<>(7);
        head.next.next.next.next.next.next.next = new ListNode<>(8);
        return head;
    }

    @Override
    public String toString() {
        return ""+val;
    }
}
