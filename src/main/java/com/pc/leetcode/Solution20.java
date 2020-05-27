package com.pc.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * @author pengchao
 * @date 10:42 2020-05-25
 */
public class Solution20 {





    public static boolean isValid(String s) {


        Map<Character,Character> map = new HashMap<>();
        map.put('{','}');
        map.put('[',']');
        map.put('(',')');

        char[] chars = s.toCharArray();

        int left = 0;

        for(int i = 0; i < chars.length; i++) {

            s.charAt(i);


        }





        return false;
    }

    public static void main(String[] args) {

        String str = "fadfadsdag";

        char[] chars = str.toCharArray();


        System.out.println();


    }

}
