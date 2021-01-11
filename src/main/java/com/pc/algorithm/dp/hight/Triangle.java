package com.pc.algorithm.dp.hight;

import java.util.Arrays;
import java.util.List;

/**
 *
 * 120. 三角形最小路径和
 * 给定一个三角形 triangle ，找出自顶向下的最小路径和。
 *
 *
 *
 * level表示层数
 * i表示索引
 *
 * level = 0, res = [level][0]
 * max([level+1][i],)
 *
 *
 * @author pengchao
 * @date 10:42 2021-01-09
 */
public class Triangle {

        public static void main(String[] args) {
            List<List<Integer>> triangle = Arrays.asList(
                    Arrays.asList(2),
                    Arrays.asList(3,4),
                    Arrays.asList(6,5,7),
                    Arrays.asList(4,1,8,3));

            System.out.println(minimumTotal(triangle));


            System.out.println(dp(triangle));

        }


        public static int minimumTotal(List<List<Integer>> triangle) {

            int[][] member = new int[triangle.size()][triangle.size()];
            return dfs(0,0,triangle,member);

        }

    /**
     * 深度优先 后序遍历
     * @param x
     * @param y
     * @param triangle
     * @param member
     * @return
     */
        private static int dfs(int x, int y,List<List<Integer>> triangle,int[][] member) {
            if(x==triangle.size()) {
                System.out.println(" member["+x+"]["+y+"]"+0);
                return 0;
            }

//            if(member[x][y]!=0) {
//                System.out.println(" member["+x+"]["+y+"]"+member[x][y]);
//                return member[x][y];
//            }

            member[x][y]  = Math.min(dfs(x+1,y,triangle,member),dfs(x+1,y+1,triangle,member))
                            +
                            triangle.get(x).get(y);
            System.out.println(" member["+x+"]["+y+"]"+member[x][y]);
            return member[x][y];
        }


    /**
     *  设三角形的深度为n
     *  dp[n+1][y] = 0
     *
     *
     *  动态转移方程
     *  dp[x][y] = Math.min(dp[x+1][y],dp[x+1][y+1]) + triangle.xy
     *
     *  base case
     *  dp[n+1][y] = 0 (设三角形的深度为n,当层数超过深度，节点不存在值为0)
     *
     *
     *
     */
    private static int dp(List<List<Integer>> triangle) {

        int[][] dp = new int[triangle.size()+1][triangle.size()+1];

        for(int i=triangle.size()-1;i>=0;i--) {//行

            for(int j=0;j<=triangle.get(i).size()-1;j++) {
                //dp[4][0]、dp[4][1]、dp[4][2]、dp[4][3]、dp[4][4] == 0
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
                System.out.println("dp["+i+"]["+j+"]:"+dp[i][j]);
            }

        }
        return dp[0][0];
    }


}
