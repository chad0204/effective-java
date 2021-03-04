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
                    //3.charAt(len1)和charAt(len2)都不是，这种情况可以忽略，都不在肯定比有一个在小
                    dp(text1,len1+1,text2,len2+1,meme)

            );
            return meme[len1][len2];
        }
    }


    /**
     *
     *
     *
     *
     * 设有S,T两个字符串，公共子序列是dp[i][j]
     *
     * S{s1,s2,s3....si},T{t1,t2,t3,t4....tj}
     *
     * 子问题 si-1 tj-1
     * 如果S的最后一位和T的最后一位相同，那么dp[i][j] = dp[i-1][j-1]+1
     * 如果S的最后一位和T的最后一位不同，那么dp[i][j] = max{dp[i][j-1],dp[i-1][j]}
     *
     * 边界
     * 只剩下{s1}和{t1}，如果相等就返回1，不等就返回0
     *
     *           0a   1b  2c  3d  4e
     *    dp 0  1    2    3   4   5
     *
     *    0
     *
     * 0a 1     1    1    1   1   1
     *
     * 1c 2     1    1    2   2   2
     *
     * 2e 3     1    1    2   2   3
     *
     *
     * 细节：dp数组的长度是字符串长度+1
     *

     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequenceDp(String text1, String text2) {

        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for(int i = 1;i<=text1.length();i++) {
            for(int j = 1;j<=text2.length();j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }


    public static int longestCommonSubsequenceII(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for(int i=0;i<text1.length();i++) {

            for(int j=0;j<text2.length();j++) {

                if(text1.charAt(i) == text2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j]+1;
                } else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1],dp[i+1][j]);
                }
            }

        }

        return dp[text1.length()][text2.length()];
    }

    /**
     * 牛客  NC127	最长公共子串
     *
     * 求出最长公共子串
     *  不是子序列，子串是连续的
     *  要求返回出子串，不是长度值
     *
     * @param str1
     * @param str2
     * @return
     */
    public String LCS (String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1+1][len2+1];

        int maxLen = 0;
        int end = 0;
        for(int i=1;i<=len1;i++) {
            for(int j=1;j<=len2;j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = 0;
                }

                if(dp[i][j]>=maxLen) {
                    maxLen = dp[i][j];
                    end = i;
                }
            }
        }
        return str1.substring(end-maxLen,end);
    }



    public static void main(String[] args) {
        longestCommonSubsequenceDp("abcded","acead");
        longestCommonSubsequenceII("abcde","ace");
    }


}
