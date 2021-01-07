package com.pc.algorithm.slidingWindows;

import com.pc.enumpackage.Food;
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
        //两个map，windows用于保存在窗口内的字母，needs用来保存需要匹配的字母的频数
        HashMap<Character, Integer> needs = new HashMap<>();
        HashMap<Character, Integer> windows = new HashMap<>();

        for(char c : t.toCharArray()) {
            needs.put(c,needs.getOrDefault(c,0)+1);
        }


        //left指针，right指针，合法字母个数valid
        int left =0;
        int right=0;
        int valid =0;


        //start,len，记录最小子串的起始位置和长度
        int start = 0;
        int len = Integer.MAX_VALUE;

        //right向前
        while (right<s.length()) {

            char add = s.charAt(right);

            right++;

            //更新windows
            if(needs.containsKey(add)) {
                windows.put(add,windows.getOrDefault(add,0)+1);
                if(needs.get(add).equals(windows.get(add))) {
                    valid++;
                }
            }

            //left向前
            while(valid==needs.size()) {
                //记录当前子串或者更新最小子串
                if(right-left < len) {
                    start = left;
                    len = right-left;
                }

                //移除的字符
                char rmv = s.charAt(left);

                left++;

                //更新windows
                if(needs.containsKey(rmv)) {
                    if(needs.get(rmv).equals(windows.get(rmv))) {
                        valid--;
                    }
                    windows.put(rmv,windows.getOrDefault(rmv,0)-1);
                }
            }
        }


        return len==Integer.MAX_VALUE ? "" : s.substring(start,start+len);
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
