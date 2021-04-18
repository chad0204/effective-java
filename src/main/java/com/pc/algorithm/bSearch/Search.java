package com.pc.algorithm.bSearch;

/**
 *
 *
 * @author pengchao
 * @date 10:55 2021-03-25
 */
public class Search {

    public static void main(String[] args) {

        System.out.println(search(new int[]{4,5,6,7,8,1,2,3},8));


    }


    /**
     *
     * 33 搜索旋转排序数组
     *
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length-1;

        while(left<=right) {
            int mid = left + (right-left)/2;

            if(nums[mid] == target) {
                return mid;
            }
            //右边有序
            if(nums[mid] < nums[right]  ) {
                //右边
                if(nums[mid] < target  && nums[right] >= target) {
                    left = mid+1;
                }
                //左边
                else {
                    right = mid-1;
                }

            }
            //左边有序
            else {
                //左边
                if(target < nums[mid] && nums[left] <= target ) {
                    right = mid-1;
                } else {
                    left = mid+1;

                }
            }
        }

        return -1;

    }
}
