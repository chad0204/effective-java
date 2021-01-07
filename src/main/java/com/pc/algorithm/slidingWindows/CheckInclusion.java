package com.pc.algorithm.slidingWindows;

import java.util.HashMap;

/**
 * 567. 字符串的排列
 *
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * @author pengchao
 * @date 11:18 2021-01-07
 */
public class CheckInclusion {

    public static void main(String[] args) {

        System.out.println(checkInclusion("abc","ccccbbbbaaaa"));
    }


    public static boolean checkInclusion(String s1, String s2) {
        HashMap<Character,Integer> needs = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();
        for(char c : s1.toCharArray()) {
            needs.put(c,needs.getOrDefault(c,0)+1);
        }

        int right = 0;
        int left = 0;
        int valid = 0;

        while (right<s2.length()) {

            char add = s2.charAt(right);

            right++;

            if(needs.containsKey(add)) {
                windows.put(add,windows.getOrDefault(add,0)+1);
                if(needs.get(add).equals(windows.get(add))) {
                    valid++;
                }
            }

            //因为找的是全排列，所以不能有间隔，故窗口长度不能超过是s1

            //right走s1.length ，left走1
            while (right-left==s1.length()) {

                if(valid==needs.size()) {
                    return true;
                }

                char rmv = s2.charAt(left);

                left++;

                if(needs.containsKey(rmv)) {
                    if(needs.get(rmv).equals(windows.get(rmv))) {
                        valid--;
                    }
                    windows.put(rmv,windows.getOrDefault(rmv,0)-1);
                }

            }
        }
        return false;
    }

}
