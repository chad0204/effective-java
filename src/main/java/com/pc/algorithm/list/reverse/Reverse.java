package com.pc.algorithm.list.reverse;

import com.pc.algorithm.datastructure.ListNode;

/**
 *
 *  206. 反转链表
 *  92.反转链表II（中等）
 * @author pengchao
 * @date 10:48 2021-01-12
 */
public class Reverse {

    public static void main(String[] args) {

        ListNode head = ListNode.buildList();

        for(ListNode p = head;p!=null;p = p.next) {
            System.out.print(p);
        }
        System.out.println();

        ListNode last = reverse(head);


        for(ListNode p = last;p!=null;p = p.next) {
            System.out.print(p);
        }
        System.out.println();




    }

    /**
     * 反转链表
     *
     * 递归
     *
     *    head
     *    1    ->   2   ->   3   ->   4   ->   5   ->   6 -> null
     *
     *                                        head.next.next = head;
     *    1    ->   2   ->   3   ->   4   ->   5   <-   6    null
     *
     *                                       head.next = null;
     *    1    ->   2   ->   3   ->   4   ->   5   <-   6    null
     *                                         |
     *                                        null
     *
     *                        ...
     *
     *    1    <-   2   <-   3   <-   4   <-   5   <-   6    null
     *    |
     *   null
     *
     *
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if (head==null || head.next == null) {
            return head;
        }

        //记住，这个递归是将head.next之后的链表反转
        ListNode newHead = reverse(head.next);
        //反转完之后，操作head
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 迭代
     *
     *  prev  curr
     *  null   1    ->   2   ->   3   ->   4   ->   5   ->   6 -> null
     *
     *        curr.next = prev;
     *  prev    curr
     *  null <- 1    ->   2   ->   3   ->   4   ->   5   ->   6 -> null
     *
     *        prev = curr;
     *          pre
     *         curr
     *  null <- 1    ->   2   ->   3   ->   4   ->   5   ->   6 -> null
     *
     *         curr = next;
     *          pre        curr
     *  null <- 1    ->   2   ->   3   ->   4   ->   5   ->   6 -> null
     *
     *
     *              ....
     *
     *
     *
     * @param head
     * @return
     */
    public static ListNode _reverse(ListNode head) {
        ListNode prev = null;//一开始前驱节点为null
        ListNode curr = head;

        while (curr!=null) {
            //暂存next节点
            ListNode next = curr.next;
            //当前节点指向前驱节点
            curr.next = prev;
            //前驱节点后移到当前节点
            prev = curr;
            //当前节点后移到当前节点的next
            curr = next;

        }
        return prev;
    }



}


