package com.pc.algorithm.list.doubleIndex;

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
     * 取出环开始节点
     *
     * 第一次相遇时
     * f = 2s
     * f = s + nb
     * 知道
     * s = nb
     *
     * 设，距离换的长度为a,环周长为b
     *  每次经过环开始点的路径都满足
     *  k = a+nb;
     *
     *
     *  当相遇时，
     *  s = nb，，由k = a+nb 得到此时s再走a长度就到达环起始点
     *
     *  在增加一个指针，和a一样的速度，两指针相遇时各自走的路程就是a
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

        //说明fast走完了还没相遇，直接返回
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
