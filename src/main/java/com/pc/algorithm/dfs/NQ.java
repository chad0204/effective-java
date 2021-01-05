package com.pc.algorithm.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 N×N 的棋盘，让你放置 N 个皇后，使得它们不能互相攻击。
 *
 *
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pengchao
 * @date 19:00 2021-01-04
 */
public class NQ {

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }


    public static List<List<String>> solveNQueens(int n) {
        List<char[]> board = new ArrayList<>(n);
        for(int i=0;i<n;i++) {//行
            char[] row = new char[n];
            for(int j=0;j<n;j++) {
                row[j] = '.';
            }
            board.add(row);
        }

        List<List<String>> res = new ArrayList<>(n);

        dfs(res,board,0);

        return res;
    }

    private static void dfs(List<List<String>> res,List<char[]> board,int row) {
        if(row == board.size()) {
            List<String> str = new ArrayList<>();
            for(char[] rows : board) {
                str.add(new String(rows));
            }
            res.add(str);
            return;
        }


        for(int col=0;col<board.size();col++) {
            if(isValid(board,row,col)) {
                board.get(row)[col] = 'Q';
                dfs(res,board,row+1);
                board.get(row)[col] = '.';
            }
        }

    }

    /**
     *
     * @param board
     * @param row 行
     * @param col 列
     * @return
     */
    private static boolean isValid(List<char[]> board, int row, int col) {
        //检查列是否有Q
        for(int i=0;i<board.size();i++) {
            if(board.get(i)[col] == 'Q') {
                return false;
            }
        }

        //检查右上是否有Q
        for(int i=row-1,j=col+1;i>=0 && j<board.size();i--,j++) {
            if(board.get(i)[j]  == 'Q') {
                return false;
            }
        }

        //检查左上是否有Q
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {
            if(board.get(i)[j] == 'Q') {
                return false;
            }
        }
        return true;
    }


}
