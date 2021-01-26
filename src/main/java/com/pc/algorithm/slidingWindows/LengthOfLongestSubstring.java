package com.pc.algorithm.slidingWindows;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 *
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * @author pengchao
 * @date 14:45 2021-01-07
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {


        System.out.println(lengthOfLongestSubstring("abcdefgc"));
        System.out.println(lengthOfLongestSubstring("abcabcbbe"));
    }


    public static int lengthOfLongestSubstring(String s) {

        HashMap<Character,Integer> windows = new HashMap<>();

        int right=0,left = 0;

        int len = 0;

        while (right<s.length()) {
            char add = s.charAt(right);
            right++;

            //更新窗口
            windows.put(add,windows.getOrDefault(add,0)+1);

            //这里left会一直向前走到没有重复为止，while里面是包含重复值的
            while (windows.get(add)>1) {

                char rmv = s.charAt(left);
                left++;

                //更新窗口
                windows.put(rmv,windows.getOrDefault(rmv,0)-1);
            }

            //左右都走完之后产生结果
            if(right-left>len) {
                len = right-left;
            }

        }

        return len;
    }
}
