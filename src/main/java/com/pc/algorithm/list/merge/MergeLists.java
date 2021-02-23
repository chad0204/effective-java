package com.pc.algorithm.list.merge;

import com.pc.algorithm.datastructure.ListNode;
import java.util.PriorityQueue;

/**
 * 21. 合并两个有序链表
 * 23. 合并K个升序链表
 *
 * 类似：617. 合并二叉树
 *
 *
 *
 * @author pengchao
 * @date 17:28 2021-02-23
 */
public class MergeLists {


    /**
     * 迭代
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }



    /**
     * 递归
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsII (ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode prevHead = new ListNode(-1);

        ListNode prev = prevHead;
        while(l1!=null && l2!=null) {
            if(l1.val<=l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        if(l1==null) {
            prev.next = l2;
        } else {
            prev.next = l1;
        }

        return prevHead.next;

    }


    /**
     * 优先队列
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoListsIII(ListNode<Integer> l1, ListNode<Integer> l2) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        while(l1!=null) {
            queue.add(l1.val);
            l1 = l1.next;
        }
        while(l2!=null) {
            queue.add(l2.val);
            l2 = l2.next;
        }

        ListNode head = new ListNode(-1);
        ListNode prev = head;
        int size = queue.size();
        for(int i=0;i<size;i++) {
            prev.next = new ListNode(queue.poll());
            prev = prev.next;

        }

        return head.next;
    }


    /**
     * 合并k个
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists,0,lists.length-1);
    }


    public ListNode merge(ListNode[] lists,int start,int end) {
        if(start>end) {
            return null;
        }
        if(start==end) {
            return lists[start];
        }

        int mid = start - (start-end)/2;

        ListNode left = merge(lists,start,mid);
        ListNode right = merge(lists,mid+1,end);

        return mergeTwoLists(left,right);
    }



}
