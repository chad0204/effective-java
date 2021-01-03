package com.pc.algorithm.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * TODO
 *
 * @author pengchao
 * @date 16:01 2021-01-03
 */
public class Test {

    public static void main(String[] args) {




    }


    public List<List<Integer>> permuteUnique(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(len==0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>(len);

        boolean[] used = new boolean[len];

        dfs(nums,path,used,res);

        return res;
    }

    public void dfs(int[] nums,Deque<Integer> path,boolean[] used,List<List<Integer>> res) {
        if(nums.length == path.size()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=0;i<nums.length;i++) {
            if(used[i] || (i>0 && nums[i]==nums[i-1] && !used[i-1])) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(nums,path,used,res);
            used[i] = false;
            path.removeLast();
        }

    }
}
