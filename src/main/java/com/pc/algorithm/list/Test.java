package com.pc.algorithm.list;

import com.pc.algorithm.datastructure.ListNode;

/**
 *
 *
 * @author pengchao
 * @date 10:48 2021-01-12
 */
public class Test {

    public static void main(String[] args) {

        ListNode head = ListNode.buildList();

        for(ListNode p = head;p!=null;p = p.next) {

            System.out.print(p);
        }
        System.out.println();

//        ListNode last = reverse(head);
//        ListNode last = reverseN(head,4);
        ListNode last = reverseBetween(head,3,5);

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
        //last在递归中是不变的，做为新的头节点
        ListNode newHead = reverse(head.next);
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
            //前驱节点后移当当前节点
            prev = curr;
            //当前节点后移到当前节点的next
            curr = next;

        }
        return prev;
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

        node.next.next = node;
        // 让反转之后的 head 节点和后面的节点连起来
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
