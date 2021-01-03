package com.pc.algorithm.dfs;

import java.util.*;

/**
 *
 *  组合总和 39
 *  给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 *
 *
 * [1,2,5]  7
 *

 *
 *    递归到子节点开始遍历时，子节点遍历的起点是父节点所在的遍历下标
 *    树衍生到负值或者0结束
 *
 *
 *
 *
 *
 *
 * @author pengchao
 * @date 10:56 2020-12-30
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{10,1,2,7,6,1,5},8));
    }

    public static List<List<Integer>> permute(int[] nums,int target) {

        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if(len<=0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>(len);//选中的数

        Arrays.sort(nums);//剔除

        boolean[] used = new boolean[len];

        dfs(nums,0,path,res,target,used);

        return res;
    }

    private static void dfs(int[] nums,int begin, Deque<Integer> path, List<List<Integer>> res,int target,boolean[] used) {
        if(target<0) {
            //结果不成立，不需要递归了
            return;
        }
        if(target==0) {
            //得到结果，不需要递归了
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i=begin;i<nums.length;i++) {
            if(target-nums[i]<0) {//排序后的数组，当计算出现负值，后面的都可以丢弃了
                break;
            }

            if(used[i] || (i>0 && nums[i] == nums[i-1] && !used[i-1])) {
                continue;
            }

            path.addLast(nums[i]);
            used[i]=true;
            dfs(nums,i,path,res,target-nums[i],used);
            //backtrack
            used[i]=false;
            path.removeLast();
        }
    }


}
