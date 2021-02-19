package com.pc.algorithm.dp;

/**
 * 28. 实现 strStr()
 *
 *
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 *
 * @author pengchao
 * @date 16:48 2021-02-19
 */
public class KMP {

    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();

        //超过m-n，剩下的不足n,肯定不匹配了
        for(int i=0;i<=m-n;i++) {
            int j;
            for(j=0;j<n;j++) {
                //注意这里的 i+j,保证两个字符串的下标都移动
                if(haystack.charAt(i+j) != needle.charAt(j)) {
                    break;
                }
            }
            if(j==n) {
                return i;
            }
        }

        return -1;
    }
}
