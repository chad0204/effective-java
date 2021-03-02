package com.pc.algorithm;

import com.pc.algorithm.datastructure.ListNode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author pengchao
 * @date 22:19 2021-01-25
 */
public class Test {



    public static boolean checkInclusion(String s1, String s2) {
        HashMap<Character,Integer> windows = new HashMap<>();
        HashMap<Character,Integer> needs = new HashMap<>();

        for(char c : s2.toCharArray()) {
            needs.put(c,needs.getOrDefault(c,0)+1);
        }

        int left=0,right=0,vaild=0;

        while(right<s1.length()) {

            char add = s1.charAt(right);
            if(needs.containsKey(add)) {
                windows.put(add,windows.getOrDefault(add,0)+1);
                if(windows.get(add).equals(needs.get(add))) {
                    vaild++;
                }
            }

            right++;

            while(right-left==s2.length()) {

                if(vaild==needs.size()) {
                    return true;
                }

                char rmv = s1.charAt(left);

                if(needs.containsKey(rmv)) {
                    if(windows.get(rmv).equals(needs.get(rmv))) {
                        vaild--;
                    }
                    windows.put(rmv,windows.getOrDefault(rmv,0)-1);
                }

                left++;
            }

        }

        return false;

    }

    public static void main(String[] args) {

        reverseKGroup(ListNode.buildList(),3);






    }

    public static ListNode reverseKGroup (ListNode head, int k) {

        ListNode tail = head;
        for(int i=0;i<k;i++) {
            tail = tail.next;
            if(tail==null) {
                return head;
            }
        }

        ListNode newHead = reverse(head,tail);

        //反转
        head.next = reverseKGroup(tail,k);


        return newHead;
    }

    public static ListNode reverse(ListNode head, ListNode tail) {
        if(head==null) {
            return null;
        }
        if(head.next==tail) {
            return head;
        }

        ListNode newHead = reverse(head.next,tail);
        head.next.next = head;
        head.next = null;
        return newHead;
    }



}
