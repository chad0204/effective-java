package com.pc.algorithm.list.reverse;

import com.pc.algorithm.datastructure.ListNode;

/**
 *
 * 92. 反转链表 II
 *
 *  反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * @author pengchao
 * @date 22:32 2021-01-27
 */
public class ReverseBetween {

    public static void main(String[] args) {

    }

    /**
     * 反转 前n个位置的链表
     * @param node
     * @param n
     * @return
     */
    //后一个断开位置n的后驱节点
    static ListNode successor = null;
    public static ListNode reverseN(ListNode node,int n) {
        if (n == 1) {
            //记录第 n + 1 个节点
            successor = node.next;
            return node;//第n个节点是新的头节点
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(node.next, n - 1);
        //更新head.next 的next
        node.next.next = node;
        //这里的next是前一个head的next.next
        node.next = successor;
        return last;
    }


    /**
     * 反转 n~m直接的链表元素
     *
     *  如果 m == 1，就相当于反转链表开头的 n 个元素
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);//反转从m到n的元素
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }


}
