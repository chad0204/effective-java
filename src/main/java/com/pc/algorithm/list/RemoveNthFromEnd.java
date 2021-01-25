package com.pc.algorithm.list;

import com.pc.algorithm.datastructure.ListNode;
import java.util.Arrays;

/**
 * 19. 删除链表的倒数第 N 个结点
 *
 * @author pengchao
 * @date 18:07 2021-01-24
 */
public class RemoveNthFromEnd {


    public static void main(String[] args) {

        String aa = "aaaa";


        removeNthFromEnd(ListNode.buildList(),1);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast,slow;
        fast = slow = head;

        while (n>0) {
            fast = fast.next;
            n--;
        }

        while (fast!=null && fast.next!=null) {
            fast = fast.next;
            slow = slow.next;
        }

        if(fast==null) {
            //说明快指针走到头了，说明倒数n个是第一个
            return head.next;
        }
        slow.next = slow.next.next;

        return head;


    }

}
