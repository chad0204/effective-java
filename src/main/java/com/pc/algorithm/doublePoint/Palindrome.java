package com.pc.algorithm.doublePoint;

/**
 * 5. 最长回文子串
 *
 * @author pengchao
 * @date 20:56 2021-01-28
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("a"));



        int a = 15/10;

        System.out.println(a);
    }

    private static String longestPalindrome(String s) {

        String res = "";
        for(int i=0;i<s.length();i++) {
            String s1 = palindrome(s,i ,i);//以一个点为中心的回文串
            String s2 = palindrome(s,i ,i+1);//以两个点为中心的回文串
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }




        return res;


    }

    public static String palindrome(String s,int left ,int right) {
        while(left>=0 && right<s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1,right);
    }


}
