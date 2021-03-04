package com.pc.algorithm.matrix;

import java.util.ArrayList;

/**
 * 54. 螺旋矩阵
 *
 * @author pengchao
 * @date 14:43 2021-03-04
 */
public class SpiralOrder {

    public static void main(String[] args) {

        spiralOrder(new int[][]{{2,3}});


    }

    public static ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<>();
        if(matrix.length==0) {
            return res;
        }

        int left=0;
        int right = matrix[0].length-1;
        int top = 0;
        int bottom = matrix.length-1;

        while(left<=right && top<=bottom) {
            //从左往右打印行
            for(int col=left;col<=right;col++) {
                res.add(matrix[top][col]);
            }
            //从上往下打印列
            for(int row= top+1;row<=bottom;row++) {
                res.add(matrix[row][right]);
            }

            //有多行才成立
            if(top<bottom) {
                //从右往左
                for(int col=right-1;col>=left;col--) {
                    res.add(matrix[bottom][col]);
                }
            }

            //有多列才成立
            if(left<right) {
                //从下往上
                for(int row = bottom-1;row>top;row--) {
                    res.add(matrix[row][left]);
                }
            }


            left++;
            right--;
            top++;
            bottom--;
        }


        return res;

    }
}
