package com.pc.algorithm.list.doubleIndex;

import com.pc.algorithm.datastructure.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * 234.回文链表（简单）
 *
 * @author pengchao
 * @date 18:04 2021-01-12
 */
public class Palindrome {

    public static void main(String[] args) {


        System.out.println(isPalindrome(ListNode.buildStringPalindromeString()));
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


    /**
     * 双指针解法
     * @param head
     * @return
     */
    public boolean isPalindromeWithIndex(ListNode<Integer> head) {
        if(head==null) {
            return true;
        }


        List<Integer> list = new ArrayList<>();

        list.add(head.val);
        while(head.next!=null) {
            list.add(head.next.val);
            head = head.next;
        }

        int left = 0;
        int right = list.size()-1;

        while(left<right) {
            if(!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;

    }
}
