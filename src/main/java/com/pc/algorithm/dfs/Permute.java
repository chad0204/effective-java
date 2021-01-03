package com.pc.algorithm.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *
 * 46 全排列
 * 返回一组无重复数的全排列
 *
 *   无重复！！
 *
 *   如果数组包含重复值，返回所有不重复的全排列？
 *   排序，数组当前位置数如果和前一个一样，并且前一个已经选中，过滤
 *
 *
 *
 *   时间 n*n!
 *   空间 n*n!
 *
 * @author pengchao
 * @date 15:47 2020-12-30
 */
public class Permute {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        Permute solution = new Permute();
        List<List<Integer>> lists = solution.permute(nums);
        System.out.println(lists);
    }

    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        //标记是否已经被选中
        boolean[] used = new boolean[len];
        //选中的数
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, len, 0, path, used, res);//其实只需要三个参数
        return res;
    }

    /**
     *
     * 递归的前后类似前序和后序遍历的处理
     *
     * 深度优先在树结构就是回溯算法
     *
     * @param nums
     * @param len 数组长度
     * @param depth 递归到第几层
     * @param path 已经选了哪些数
     * @param used 被选中的设为true,避免每次都遍历所有数（空间换时间）
     * @param res 结果
     */
    private void dfs(int[] nums,
                     int len,
                     int depth,
                     Deque<Integer> path,
                     boolean[] used,
                     List<List<Integer>> res) {
        //当深度和变量个数一致，说明所有变量都遍历完成
        if (depth == len) {
            res.add(new ArrayList<>(path));//全程只保留一份，每次回溯又变成空列表，所以这里的添加要copy一份，不然最后都是空列表
            return;
        }

        // 在非叶子结点处，产生不同的分支，这一操作的语义是：在还未选择的数中依次选择一个元素作为下一个位置的元素，这显然得通过一个循环实现。
        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;//标记第i个数被选中
                //depth + 1 定位到下一层
                dfs(nums, len, depth + 1, path, used, res);
                // 注意：下面这两行代码发生 「回溯」backtrack，回溯发生在从 深层结点 回到 浅层结点 的过程，代码在形式上和递归之前是对称的
                used[i] = false;
                path.removeLast();
            }
        }
    }


}

