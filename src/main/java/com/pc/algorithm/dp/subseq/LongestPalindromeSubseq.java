package com.pc.algorithm.dp.subseq;

import java.util.Arrays;

/**
 * 516. 最长回文子序列
 *
 *
 * 设序列下标为i，j (0<=i<j<=n),s[i,..,j]为子序列，假设s[i+1,..,j-1]的结果为l，
 * 如果s[i]和s[j]相等，那么s[i,..,j] = l+2，不想等，分别加入两边
 *
 *  if(s[i] == s[j]) {
 *      dp[i][j] = dp[i+1][j-1] + 2;
 *  } else {
 *      dp[i][j] = max(dp[i+1][j],[i,j-1]);
 *  }
 *
 *
 *  结果是dp[0][n-1]
 *
 *
 * @author pengchao
 * @date 12:38 2021-02-09
 */
public class LongestPalindromeSubseq {

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseqDq("cbacbbc"));
    }


    public static int longestPalindromeSubseq(String s) {

        int[][] mem = new int[s.length()][s.length()];


        return dp(s,0,s.length()-1,mem);
    }

    public static int dp(String s,int i,int j,int[][] mem) {
        if(i==j) {
            //一个字符
            return 1;
        } else if(i>j) {
            //没有字符
            return 0;
        }

        if(mem[i][j]!=0) {
            return mem[i][j];
        }

        if(s.charAt(i) == s.charAt(j)) {
            //i，j构成回文字符
            mem[i][j] = dp(s,i+1,j-1,mem)+2;
            return mem[i][j];
        } else {
            //i，j不构成回文字符，分别加入到i+1和j-1中
            mem[i][j] = Math.max( dp(s,i+1,j,mem), dp(s,i,j-1,mem));
            return mem[i][j];
        }
    }



    public static int longestPalindromeSubseqDq(String s) {

        int[][] dp = new int[s.length()+1][s.length()+1];

        //倒着遍历,因为求dp[i][j]需要知道dp[i+1][j-1],dp[i][j-1],dp[i+1][j]
        for(int i=s.length()-1;i>=0;i--) {
            for(int j=0;j<s.length();j++) {
                if (j == i) {
                    dp[i][j] = 1;
                    continue;
                }
                if (j < i) {
                    dp[i][j] = 0;
                    continue;
                }

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    //并入左边，并入右边
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][s.length()-1];
    }

    public int longestPalindromeSubseq_II(String s) {
        //dp[i][j]表示在s[i,...,j]之间，最长的回文子序列
        int[][] dp = new int[s.length()][s.length()];

        for(int i=s.length()-1;i>=0;i--) {
            //一个字符的回文子序列就是1
            dp[i][i] = 1;
            for(int j=i+1;j<s.length();j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1]+2;
                } else {
                    dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];

    }



}
