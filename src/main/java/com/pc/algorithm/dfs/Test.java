package com.pc.algorithm.dfs;

import java.util.*;

/**
 *
 * [a,b,c]三种无限多
 *
 * @author pengchao
 * @date 16:01 2021-01-03
 */
public class Test {

    public static void main(String[] args) {
        String[] nums = new String[]{"a","b","b"};

        System.out.println(permute(nums));

    }


    public static List<List<String>> permute(String[] nums) {

        List<List<String>> res = new ArrayList<>();
        int len = nums.length;
        if(len==0) {
            return res;
        }

        Deque<String> path = new ArrayDeque<>(len);

        boolean[] used = new boolean[len];

        dfs(nums,path,used,res,0);

        return res;
    }

    private static void dfs(String[] nums, Deque<String> path,boolean[] used ,List<List<String>> res,int depth) {

        if(nums.length==path.size()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=0;i<nums.length;i++) {
            if(used[i] || (i>0 && nums[i].equals(nums[i-1]) && !used[i-1])) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums,path,used,res,depth);
            used[i] = false;
            path.removeLast();

        }
    }
}
