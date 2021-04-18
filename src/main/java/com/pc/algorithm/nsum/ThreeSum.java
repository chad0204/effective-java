package com.pc.algorithm.nsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和
 *
 *  给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pengchao
 * @date 20:03 2021-01-21
 */
public class ThreeSum {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int a = -1;
        list.add(a);


        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));

    }


    /**
     *
     *  思路： 穷举，先找一个，然后用重复元素twoSum的思路来解决
     *
     * @param nums
     * @return
     */
    public  static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length==0) {
            return res;
        }

        Arrays.sort(nums);


        for(int i=0;i<nums.length;i++) {

            int target = 0 - nums[i];

            //求两数求和
            List<List<Integer>> twoSums = TwoSum.twoSumNoDup(nums,i+1,target);

            for(List<Integer> list :twoSums ) {
                list.add(nums[i]);
                res.add(list);
            }

            //跳过第一个数字重复的情况，否则会出现重复结果
            while(i<nums.length-1 && nums[i]==nums[i+1]) {
                i++;
            }

        }

        return res;

    }
}
