package com.pc.algorithm.list.reverse;

import com.pc.algorithm.datastructure.ListNode;

/**
 * 剑指 Offer 52. 两个链表的第一个公共节点
 *
 * NC66	两个链表的第一个公共结点
 *
 *
 *
 *
 * 双指针
 *
 *
 *
 * @author pengchao
 * @date 19:56 2021-03-03
 */
public class IntersectionNode {


    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if(pHead1==null||pHead2==null) return null;
        ListNode p1=pHead1;
        ListNode p2=pHead2;
        while(p1!=p2){
            if(p1==null){
                //pHead1走到头后链入pHead2
                p1=pHead2;
            }else{
                p1=p1.next;
            }
            if(p2==null){
                //pHead2走到头后链入pHead1
                p2=pHead1;
            }else{
                p2=p2.next;
            }
        }
        return p1;
    }


}
