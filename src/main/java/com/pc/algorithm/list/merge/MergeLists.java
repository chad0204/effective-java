package com.pc.algorithm.list.merge;

import com.pc.algorithm.datastructure.ListNode;
import java.util.Arrays;
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


    public static void main(String[] args) {

//        int[] a = new int[]{-10,-10,-9,-9,-9,-8,-8,-7,-7,-7,-6,-6,-6,-6,-6,-6,-6,-5,-5,-5,-4,-4,-4,-3,-3,-2,-2,-1,-1,0,1,1,1,2,2,2,3,3,3,4,5,5,6,6,6,6,7,7,7,7,8,9,9,9,9};
//        int[] b = new int[]{-10,-10,-9,-9,-9,-9,-8,-8,-8,-8,-8,-7,-7,-7,-7,-7,-7,-7,-7,-6,-6,-6,-6,-5,-5,-5,-5,-5,-4,-4,-4,-4,-4,-3,-3,-3,-2,-2,-2,-2,-2,-2,-2,-1,-1,-1,0,0,0,0,0,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,4,4,4,4,4,4,4,5,5,5,5,5,5,6,6,6,6,6,7,7,7,7,7,7,7,8,8,8,8,9,9,9,9};
        int[] a = new int[]{};
        int[] b = new int[]{1};
        merge(a,0,b, 1);
    }


    public static  void merge(int A[], int m, int B[], int n) {
        int[] temp = new int[m+n];
        int left = 0;
        int right = 0;
        int p = 0;

        while(left< m && right<n) {
            if(A[left] <= B[right]) {
                temp[p] = B[left];
                left++;
            } else {
                temp[p] = B[right];
                right++;
            }
            p++;
        }

        while(left< m) {
            temp[p] = A[left];
            left++;
            p++;
        }
        while(right< n) {
            temp[p] = B[right];
            right++;
            p++;
        }

//        for(int i=0;i<m+n;i++) {
            A = temp;
//        }


        System.out.println(Arrays.toString(A));
    }



}
