package com.pc.algorithm.slidingWindows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 *
 * 输入:
 * s: "cbaebabacd" p: "abc"
 *
 * 输出:
 * [0, 6]
 *
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pengchao
 * @date 14:19 2021-01-07
 */
public class FindAnagrams {

    public static void main(String[] args) {
        System.out.println(findAnagrams("abab","ab"));
    }





    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        HashMap<Character,Integer> needs = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();
        for(char c : p.toCharArray()) {
            needs.put(c,needs.getOrDefault(c,0)+1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        int start = 0;

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

            while (right-left == p.length()) {

                if(valid==needs.size()) {
                    res.add(left);
                }

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

        return res;
    }
}
