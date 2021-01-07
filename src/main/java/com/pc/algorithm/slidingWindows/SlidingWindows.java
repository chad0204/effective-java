package com.pc.algorithm.slidingWindows;

import java.util.HashMap;

/**
 *
 * @author pengchao
 * @date 19:20 2021-01-06
 */
public class SlidingWindows {


    public static void main(String[] args) {


        System.out.println(minWindow("ADOBECODEBANC","ABC"));


    }





    /**
     * s 包含所有t的最小子串
     *
     * 输入：s = "ADOBECODEBANC", t = "ABCA"
     * 输出："BANC"
     *
     * @param s
     * @param t
     */
    public static String minWindow(String s, String t) {

        HashMap<Character,Integer> needs = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();
        for(Character c : t.toCharArray()) {
            needs.put(c,needs.getOrDefault(c,0)+1);
        }

        char[] source = s.toCharArray();

        int left = 0;
        int right = 0;

        int valid = 0;//满足条件的字符个数

        // 记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;

        while (right<s.length()) {
            char c = source[right];
            right++;

            //记录窗口
            if(needs.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c, 0) + 1);
                if(windows.get(c).equals(needs.get(c))) {//windows中的数已经满足needs，valid不需要在增
                    valid++;
                }
            }

            //收缩左边,只有相等时才收缩
            while (valid == needs.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }


                // d 是将移出窗口的字符,用来更新windows
                char d = source[left];

                // 左移窗口
                left++;


                // 进行窗口内数据的一系列更新
                if (needs.containsKey(d)) {
                    if (windows.get(d).equals(needs.get(d)))//这里可能会存在windows>needs的情况，这里还是满足条件的，valid不需要减
                        valid--;
                    windows.put(d,windows.getOrDefault(d,0)-1);
                }
            }
        }

        // 返回最小覆盖子串
        return len == Integer.MAX_VALUE ?
                "" : s.substring(start, start+len);
    }


    /**
     * 暴力枚举所有可能的子串
     * @param s
     * @param t
     */
    public static void force(String s, String t) {


        int vaild = 0;
        for(int i=0;i<s.length();i++) {
            for(int j=i+3;j<=s.length();j++) {
                //判断子串是否满足
                System.out.println(s.substring(i,j));
            }
        }

    }
}
