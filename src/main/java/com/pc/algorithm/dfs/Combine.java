package com.pc.algorithm.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 77. 组合
 *
 *
 * @author pengchao
 * @date 16:34 2021-01-04
 */
public class Combine {


    public static void main(String[] args) {
        System.out.println(combine(4,4));
    }



    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if(n<k) {
            return res;
        }
        int[] nums = new int[n];
        for(int i=0;i<n;i++) {
            nums[i] = i+1;
        }

        Deque<Integer> path = new ArrayDeque<>(n);
        boolean[] used = new boolean[n];

        dfs(nums,path,used,res,k,0);

        return res;
    }

    /**
     * 剪枝
     *
     * n=6 , k =4
     *
     * 那么当n = 3之后，就已经凑不够4个数了
     *
     * @param nums
     * @param path
     * @param used
     * @param res
     * @param k
     * @param begin
     */
    private static void dfs(int[] nums, Deque<Integer> path, boolean[] used, List<List<Integer>> res,int k,int begin) {
        if(path.size()==k) {
            res.add(new ArrayList<>(path));
            return;
        }


        for(int i=begin;i<nums.length;i++) {

            if(used[i]) {
                continue;
            }

            //剪枝，遍历的起点到一定值，剩下的元素已经不够k个数了
            if(begin>nums.length-(k-path.size())+1) {
                break;
            }


            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums,path,used,res,k,i);
            used[i] = false;
            path.removeLast();
        }
    }
}
