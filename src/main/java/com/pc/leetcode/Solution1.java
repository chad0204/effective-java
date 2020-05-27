package com.pc.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两数之和
 *
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author pengchao
 * @date 09:49 2020-05-25
 */
public class Solution1 {

    public static int[] twoSum1(int[] nums, int target) {

        Map<Integer,Integer> map = new HashMap<>();

        for(int i=0;i<nums.length;i++) {
            map.put(nums[i],i);
        }

        for(int i=0;i<nums.length;i++) {
            //补充值
            int complement = target - nums[i];
            if(map.containsKey(complement) && map.get(complement)!=i) {
                return new int[]{i,map.get(complement)};
            }
        }

        throw new IllegalArgumentException("no two num");
    }


    public static int[] twoSum2(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for(int j=i+1;j<nums.length;j++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        throw new IllegalArgumentException("no two num");
    }





    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};

        int[] result = twoSum1(nums,6);


    }


}
