package com.pc.algorithm.binarySearch;

import java.util.Arrays;

/**
 * 二分查找
 *
 * 704. 二分查找
 *
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 *
 * @author pengchao
 * @date 14:29 2021-01-06
 */
public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(search(new int[]{-1,0,3,4,9,12},4));
        System.out.println(left_bound_search(new int[]{3,3,3,3,3,3},1));//left!=target
        System.out.println(left_bound_search(new int[]{3,3,3,3,3,3},4));//left >= nums.length
        System.out.println(right_bound_search(new int[]{3,3,3,3,3,3},3));
    }


    /**
     *
     *
     * 当right=nums.length-1,搜索区间就是[left,right],当left==right时，left和right还未遍历,所以终止条件应该是left>right,否则,即while(left<=right)
     * 比如search(new int[]{-1,0,3,4,9,12},4)，当用while(left<right)就返回-1。
     *
     * 当right=nums.length  ,搜索区间就是[left,right),终止条件就是left=right,即while(left<right)
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length-1;//⚠️

        while (left<=right) {
            //防止溢出
//            int mid = (left+right)/2;
            int mid = left+ (right-left) /2;//⚠️

            if(nums[mid] == target) {
                return mid;//找到终止
            } else if(nums[mid] > target) {
                right = mid-1;//⚠️
            } else if(nums[mid] < target) {
                left = mid+1;//⚠️
            }
        }

        return -1;
    }

    public static int left_bound_search(int[] nums, int target) {
        int left = 0;

        int right = nums.length-1;//⚠️

        while (left<=right) {//
            //防止溢出
//            int mid = (left+right)/2;
            int mid = left+ (right-left) /2;//⚠️

            if(nums[mid] == target) {
                right = mid-1;//等于找左边
            } else if(nums[mid] > target) {
                right = mid-1;////找左边
            } else if(nums[mid] < target) {
                left = mid+1;//找右边
            }
        }


        if (left >= nums.length) {
            //所有元素都比target小 left >= nums.length
            return -1;
        }

        if(nums[left] != target) {
            //所有元素都比target大 nums[left] != target
            return -1;
        }

        return left;
    }

    public static int right_bound_search(int[] nums, int target) {
        int left = 0;

        int right = nums.length-1;//⚠️

        while (left<=right) {//
            //防止溢出
//            int mid = (left+right)/2;
            int mid = left+ (right-left) /2;//⚠️

            if(nums[mid] == target) {
                left = mid+1;//等于向右
            } else if(nums[mid] < target) {
                left = mid+1;//向右
            } else if(nums[mid] > target) {
                right = mid-1;//向左
            }
        }
        //所有元素都比target大 nums[right] != target
        //所有元素都比target小 right < 0
        if (right < 0 || nums[right] != target)
            return -1;
        return left;
    }


    /**
     * 找出最左和最右元素
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {

        int[] res = new int[2];
        Arrays.fill(res,-1);

        //先找左
        int left = 0;
        int right = nums.length-1;
        while(left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid]==target) {
                right = mid-1;
            } else if(nums[mid]>target) {
                right = mid-1;
            } else if(nums[mid]<target) {
                left = mid+1;
            }
        }
        if(left<nums.length && nums[left]==target ) {
            res[0] = left;
        }

        //再找右
        left = 0;
        right = nums.length-1;
        while(left<=right) {
            int mid = left + (right-left)/2;
            if(nums[mid]==target) {
                left = mid+1;
            } else if(nums[mid]>target) {
                right = mid-1;
            } else if(nums[mid]<target) {
                left = mid+1;
            }
        }

        if(right>=0 && nums[right]==target) {
            res[1] = right;
        }
        return res;
    }
}
