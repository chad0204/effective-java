package com.pc.algorithm.list;

import com.pc.algorithm.datastructure.ListNode;

/**
 * 234.回文链表（简单）
 *
 * @author pengchao
 * @date 18:04 2021-01-12
 */
public class Palindrome {

    public static void main(String[] args) {


        System.out.println(isPalindrome(ListNode.buildStringPalindrome()));
    }

    private static ListNode left;
    public static  boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    public static boolean traverse(ListNode right) {
        if(right==null) {
            return true;
        }

        //right是倒序的，从最后一个开始向前，后序遍历相当于栈,第一个出递归的是最后一个节点
        boolean res = traverse(right.next);

        //比较left和right
        res = res && right.val == left.val;
        //头节点向前
        left = left.next;

        return res;
    }
}
