package com.pc.algorithm.dfs;

import java.util.*;

/**
 * 求数组的所有子集
 * 结果集中不包含重复的子集
 *
 * @author pengchao
 * @date 22:00 2021-01-02
 */
public class Subset {

    public static void main(String[] args) {

        System.out.println(subsets(new int[]{1,2,2}));
        //[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
        //[[], [1], [1, 2], [1, 2, 2], [1, 2], [2], [2, 2], [2]]

    }

    public static List<List<Integer>> subsets(int[] nums) {

        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        if(len==0) {
            return res;
        }

        Deque<Integer> path = new ArrayDeque<>(len);
        boolean[] used = new boolean[len];
        Arrays.sort(nums);
        dfs(nums,0,path,used,res);
        return res;
    }

    /**
     *
     * 用过的元素跳过，used
     * 每次遍历从上一次的结束下标开始depath
     * @param nums
     * @param depath
     * @param path
     * @param used
     * @param res
     */
    private static void dfs(int[] nums,int depath, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {

        if(path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        res.add(new ArrayList<>(path));
        //每次从depath开始，每次分支之后遍历的数不包含之前遍历过的
        for(int i=depath;i<nums.length;i++) {
            if(used[i] || (i>0 && nums[i]==nums[i-1] && !used[i-1] )) {//!used[i-1] 表示回溯到父节点后，走的是另一个分支
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums,i,path,used,res);
            used[i] = false;
            path.removeLast();
        }
    }

}
