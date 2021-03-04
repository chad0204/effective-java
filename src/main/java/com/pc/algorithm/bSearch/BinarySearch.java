package com.pc.algorithm.bSearch;

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
        System.out.println(left_bound_search(new int[]{2,3,4,4,4,7,7,8,10,10,11,12,13,14,15,15,17,18,19,23,24,24,24,24,25,26,26,26,27,27,28,29,29,30,33,36,38,38,40,40,41,43,43,43,44,46,46,47,51,52,52,53,54,56,57,57,57,58,58,61,61,61,62,64,64,66,66,67,67,67,70,72,74,74,74,75,75,78,78,78,79,79,80,83,83,83,83,84,84,86,88,89,89,90,91,91,92,93,93,96},1));
//        System.out.println(left_bound_search(new int[]{3,3,3,3,3,3},1));//left!=target
        System.out.println(left_bound_search(new int[]{3,3,3,3,3,3},3));//left >= nums.length
//        System.out.println(right_bound_search(new int[]{3,3,3,3,3,3},3));


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

    /**
     * 多次出现，最左边
     * @param nums
     * @param target
     * @return
     */
    public static int left_bound_search(int[] nums, int target) {
        int left = 0;

        int right = nums.length-1;//⚠️

        while (left<=right) {//
            //防止溢出
//            int mid = (left+right)/2;
            int mid = left+ (right-left) /2;//⚠️

            if(nums[mid] == target) {
                right = mid-1;//相等找左边
            } else if(nums[mid] > target) {
                right = mid-1;////找左边
            } else if(nums[mid] < target) {
                left = mid+1;//找右边
            }
        }


        if (left >= nums.length) {
            //所有元素都比target小，最终left会超出nums.length-1
            return -1;
        }

        if(nums[left] != target) {
            //所有元素都比target大，最终nums[left]也不会是target
            return -1;
        }

        return left;
    }

    /**
     * 多次出现，最右边X
     * @param nums
     * @param target
     * @return
     */
    public static int right_bound_search(int[] nums, int target) {
        int left = 0;

        int right = nums.length-1;//⚠️

        while (left<=right) {//
            //防止溢出
//            int mid = (left+right)/2;
            int mid = left+ (right-left) /2;//⚠️

            if(nums[mid] == target) {
                left = mid+1;//相等向右
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
        return right;
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
