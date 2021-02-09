package com.pc.algorithm.dp.subseq;

/**
 * 72. 编辑距离
 *
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/edit-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pengchao
 * @date 19:14 2021-02-01
 */
public class MinDistance {

    public static void main(String[] args) {
        System.out.println(minDistanceDp("",""));
    }


    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        return minDistance(word1, word2,word1.length()-1,word2.length()-1,dp);
    }


    public static int minDistance(String word1, String word2,int i,int j,int[][] dp) {
        if(i==-1) {
            return j+1;
        }
        if(j==-1) {
            return i+1;
        }

        if(dp[i][j]!=0)
            return dp[i][j];

        if(word1.charAt(i) == word2.charAt(j)) {
            dp[i][j] = minDistance(word1,word2,i-1,j-1,dp);
            return dp[i][j];
        } else {
            //增，删，改
            int add = minDistance(word1,word2,i,j-1,dp)+1;
            int rmv = minDistance(word1,word2,i-1,j,dp)+1;
            int upd = minDistance(word1,word2,i-1,j-1,dp)+1;
            dp[i][j] = Math.min(add,Math.min(rmv,upd));
            return dp[i][j];
        }
    }


    public static int minDistanceDp(String word1, String word2) {
        int len1 = word1.length()+1;
        int len2 = word2.length()+1;
        int[][] dp = new int[len1][len2];

        for(int i=0;i<len1;i++) {
            for(int j=0;j<len2;j++) {
                //word1为空，编辑次数就是word2的长度
                if(i==0) {
                    dp[0][j] = j;
                    continue;
                }
                //word2为空，编辑次数就是word1的长度
                if(j==0) {
                    dp[i][0] = i;
                    continue;
                }
                //如果用word1.charAt(i)超长了
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1]+1,Math.min(dp[i-1][j]+1,dp[i][j-1]+1));
                }

            }
        }
        return dp[len1-1][len2-1];

    }
}
