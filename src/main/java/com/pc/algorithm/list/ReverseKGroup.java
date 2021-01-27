package com.pc.algorithm.list;

import com.pc.algorithm.datastructure.ListNode;

/**
 * 25. K 个一组翻转链表
 *
 * @author pengchao
 * @date 22:32 2021-01-27
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null) {
            return null;
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
        head.next = reverseKGroup(tail,k);

        return newHead;

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
