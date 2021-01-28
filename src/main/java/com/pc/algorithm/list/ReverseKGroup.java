package com.pc.algorithm.list;

import com.pc.algorithm.datastructure.ListNode;

/**
 * 25. K 个一组翻转链表
 *
 * @author pengchao
 * @date 22:32 2021-01-27
 */
public class ReverseKGroup {


    public static void main(String[] args) {

        ListNode head = reverseKGroupDESC(ListNode.buildList(),3);

        for(;head!=null;head = head.next) {
            System.out.println(head);
        }


    }


    /**
     * k个一组反转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null || head.next==null) {
            return head;
        }
        ListNode tail = head;

        for(int i=0;i<k;i++) {
            if(tail==null) {
                return head;
            }
            tail = tail.next;
        }
        //反转head,tail
        ListNode newHead = reverse(head,tail);

        //反转完，原先的head就是尾，把这个head.next指向下一个被反转的尾
        head.next = reverseKGroup(tail,k);

        return newHead;
    }


    /**
     *
     *
     * 12345678
     *
     * 876 543 21
     *
     * 678 345 21
     *
     * 12543876
     *
     *
     */
    public static ListNode reverseKGroupDESC(ListNode head, int k) {
        if(head==null || head.next==null) {
            return head;
        }

        ListNode nodeDESC = reverse(head);

        ListNode newHead = _reverseKGroupDESC(nodeDESC,k);

        return reverse(newHead);
    }

    public static ListNode _reverseKGroupDESC(ListNode head, int k) {
        if(head==null || head.next==null) {
            return head;
        }

        boolean isReverse = false;
        ListNode tail = head;
        for(int i=0;i<k;i++) {
            if(tail==null) {
                return head;
            }
            tail = tail.next;
        }

        ListNode newHead = reverse(head,tail);
        head.next = _reverseKGroupDESC(tail,k);

        return newHead;
    }



    /**
     * 迭代反转
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if(head==null || head.next==null) {
            return head;
        }
        //反转
        ListNode prev = null;
        while(head!=null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }





    /**
     * 迭代
     * @param head
     * @param tail
     * @return
     */
    public static ListNode reverse(ListNode head, ListNode tail) {
        ListNode prev,curr;
        prev = null;
        curr = head;
        while (curr != tail) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 递归
     * @param head
     * @param tail
     * @return
     */
    public static ListNode reverse1(ListNode head, ListNode tail) {
        if(head.next==tail) {
            return head;
        }

        ListNode newHead = reverse1(head.next,tail);

        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
