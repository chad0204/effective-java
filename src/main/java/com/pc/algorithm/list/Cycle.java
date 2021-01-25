package com.pc.algorithm.list;

import com.pc.algorithm.datastructure.ListNode;

/**
 * 141. 环形链表
 *
 * 双指针技巧
 *
 * @author pengchao
 * @date 20:46 2021-01-21
 */
public class Cycle {


    public static void main(String[] args) {
        detectCycle(ListNode.buildList());
        detectCycle(ListNode.buildCycle());
    }


    /**
     * 快慢指针
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast !=null && fast.next !=null) {
            //走多少步都无所谓，只要速度不一样
            fast = fast.next.next;//走两步
            slow = slow.next;//走一步
            if(fast == slow) {
                return true;
            }
        }

        return false;

    }

    /**
     *
     *
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while (fast !=null && fast.next !=null) {
            fast = fast.next.next;//走两步
            slow = slow.next;//走一步
            if(fast == slow) {
                break;
            }
        }

        if(fast == null || fast.next==null) {
            return null;
        }

        fast=head;
        while (fast!=slow) {
            fast = fast.next;
            slow = slow.next;
        }

        //没有环的话，slow返回null
        return slow;


    }
    

}
