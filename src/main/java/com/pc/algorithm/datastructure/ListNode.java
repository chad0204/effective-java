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
        //创建链表
        ListNode head = new ListNode<>(1);	//根节点A

        head.next = new ListNode<>(2);
//        head.next.next = new ListNode<>(3);
//        head.next.next.next = new ListNode<>(4);
//        head.next.next.next.next = new ListNode<>(5);
//        head.next.next.next.next.next = new ListNode<>(6);
//        head.next.next.next.next.next.next = new ListNode<>(7);
//        head.next.next.next.next.next.next.next = new ListNode<>(8);
        return head;
    }


    public static ListNode buildStringPalindrome() {
        ListNode head = new ListNode<>("A");	//根节点A

        head.next = new ListNode<>("B");
        head.next.next = new ListNode<>("C");
        head.next.next.next = new ListNode<>("C");
        head.next.next.next.next = new ListNode<>("233");
        head.next.next.next.next.next = new ListNode<>("A");

        return head;
    }

    public static ListNode buildCycle() {
        //创建链表
        ListNode head = new ListNode<>(1);	//根节点A

//        head.next = new ListNode<>(2);
//        head.next.next = new ListNode<>(3);
//        head.next.next.next = new ListNode<>(4);
//        head.next.next.next.next = new ListNode<>(5);
//        head.next.next.next.next.next = new ListNode<>(6);
//        head.next.next.next.next.next.next = new ListNode<>(7);
//        head.next.next.next.next.next.next.next = new ListNode<>(8);
//        head.next.next.next.next.next.next.next.next = head.next.next;

        return head;
    }

    @Override
    public String toString() {
        return ""+val;
    }


    public static void traverse(ListNode head) {
        if(head==null) {
            return;
        }
        //前序遍历
        traverse(head.next);
        //后序遍历
        System.out.println(head.val);

    }



    public static void main(String[] args) {

        ListNode head = buildList();

        traverse(head);
    }
}
