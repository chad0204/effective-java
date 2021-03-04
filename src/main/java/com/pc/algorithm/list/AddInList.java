package com.pc.algorithm.list;

import com.pc.algorithm.datastructure.ListNode;

/**
 *  NC40	两个链表生成相加链表
 *
 *  面试题 02.05. 链表求和
 *
 * @author pengchao
 * @date 21:34 2021-03-02
 */
public class AddInList {

    public static ListNode buildListI() {
        //创建链表
        ListNode head = new ListNode<>(9);	//根节点A

        head.next = new ListNode<>(3);
        head.next.next = new ListNode<>(7);
        return head;
    }

    public static ListNode buildListII() {
        //创建链表
        ListNode head = new ListNode<>(6);	//根节点A

        head.next = new ListNode<>(3);
        return head;
    }


    public static void main(String[] args) {


        System.out.println(addInList(buildListI(),buildListII()));



    }


    public static ListNode addInList (ListNode<Integer> head1, ListNode<Integer> head2) {
        ListNode<Integer> node1 = reverseList(head1);
        ListNode<Integer> node2 = reverseList(head2);

        //生成一个新链表
        ListNode head = new ListNode(-1);
        ListNode prev = head;

        //用于保存进位
        int carry = 0;

        while(node1!=null || node2!=null || carry!=0) {
            int sum = (node1 ==null ? 0: node1.val)  +  (node2==null?0:node2.val) + carry;
            carry = sum /10;
            prev.next = new ListNode(sum%10);
            prev = prev.next;

            node1 = node1==null ? node1 : node1.next;
            node2 = node2==null ? node2 : node2.next;
        }
        return head.next;
    }

    public static ListNode reverseList(ListNode head) {
        if(head.next==null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
