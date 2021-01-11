package com.pc.algorithm.nsum;

import java.util.*;

/**
 * 1. 两数之和
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pengchao
 * @date 15:49 2021-01-11
 */
public class TwoSum {


    public static void main(String[] args) {

        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }


    /**
     *
     * 利用map存储所有值，补偿判断
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> map = new HashMap<>();
        for(int i : nums) {
            map.put(i,nums[i]);
        }

        for(int i=0;i<nums.length;i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement) && map.get(i) != complement) {
                return new int[]{i,map.get(complement)};
            }

        }

        return new int[]{-1,-1};
    }

    /**
     * 双指针解法
     */
    public static int[] twoSumWithIndex(int[] nums, int target) {

        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;

        while (left<right) {
            int sum = nums[left] + nums[right];
            if(sum==target) {
                return new int[]{left,right};
            }
            if(sum<target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{};
    }





}
