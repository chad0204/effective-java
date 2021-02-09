package com.pc.algorithm.dp.subseq;

/**
 * 1143. 最长公共子序列
 *
 * @author pengchao
 * @date 19:56 2021-02-06
 */
public class LCS {

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] meme = new int[text1.length()+1][text2.length()+1];
        return dp(text1,0,text2,0,meme);
    }

    public int dp(String text1, int len1,String text2,int len2,int[][] meme) {
        //base case
        if(len1==text1.length() || len2 == text2.length()) {
            meme[len1][len2] = 0;
            return 0;
        }

        if(meme[len1][len2]!=0) {
            return meme[len1][len2];
        }


        if(text1.charAt(len1) == text2.charAt(len2)) {
            return dp(text1,len1+1,text2,len2+1,meme)+1;
        } else {
            /*
            charAt(len1)和charAt(len2)与公共子序列的三种情况
            */
            meme[len1][len2] = Math.max(
                    Math.max(
                            //1.charAt(len1)不是
                            dp(text1,len1+1,text2,len2,meme),
                            //2.charAt(len2)不是
                            dp(text1,len1,text2,len2+1,meme)
                    ),
                    //3.charAt(len1)和charAt(len2)都不是
                    dp(text1,len1+1,text2,len2+1,meme)

            );
            return meme[len1][len2];
        }
    }


    public int longestCommonSubsequenceDp(String text1, String text2) {

        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i = 0;i<=text1.length();i++) {
            for(int j = 0;j<=text2.length();j++) {

                //这两个0可以省略，直接从1开始循环longestPalindrome
                if(i==0) {
                    dp[i][j] = 0;
                    continue;
                }
                if(j==0) {
                    dp[i][j] = 0;
                    continue;
                }

                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }



}
