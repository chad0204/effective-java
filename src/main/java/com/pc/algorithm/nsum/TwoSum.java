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

        System.out.println(Arrays.toString(twoSumWithIndex(new int[]{3,2,4}, 6)));
        System.out.println(twoSumNoDup(new int[]{1,1,1,2,2,2,3,3,3,}, 4));
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
     * 有序数组，返回两数的值，可以用双指针解法
     */
    public static int[] twoSumWithIndex(int[] nums, int target) {
        //先排序,但是结果的顺序已经不是原来数组的顺序了
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;

        while (left<right) {
            int sum = nums[left] + nums[right];
            if(sum==target) {
                return new int[]{nums[left],nums[right]};
            }
            if(sum<target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{};
    }

    /**
     * 如果结果包含重复值，如何剔除
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> twoSumNoDup(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //先排序
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length-1;

        while (left<right) {
            int l = nums[left];
            int r = nums[right];
            int sum = l + r;

            if(sum==target) {
                res.add(Arrays.asList(nums[left],nums[right]));

                while (left<right && l==nums[left]) {
                    left++;
                }
                while (left<right && r==nums[right]) {
                    right--;
                }

                left++;right--;
            }
            if(sum<target) {
                while (left<right && l==nums[left]) {
                    left++;
                }
            } else {

                while (left<right && r==nums[right]) {
                    right--;
                }

            }
        }

        return res;
    }







}
