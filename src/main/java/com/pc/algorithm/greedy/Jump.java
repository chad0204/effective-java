package com.pc.algorithm.greedy;

/**
 * 55. 跳跃游戏
 * 45. 跳跃游戏 II
 *
 * 还有com.pc.algorithm.intervals.EraseOverlapIntervals中的两题
 *
 * @author pengchao
 * @date 15:44 2021-02-10
 */
public class Jump {

    public static void main(String[] args) {

        canJump(new int[]{2,3,1,1,4});
//        jump(new int[]{2,3,1,1,4});
        jump(new int[]{2,3,1,1,4,2,1});


    }

    /**
     * 判断能否到达最后一位
     *
     * 1.如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
     * 2.可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
     * 3.如果可以一直跳到最后，就成功了。
     *
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int n = nums.length;
        int farthest = 0 ;
        //判断的是否能到n-1，n-1不用算
        for(int i=0;i<n-1;i++) {
            // 不断计算能跳到的最远距离，并更新
            farthest = Math.max(farthest,i+nums[i]);
            //目前的最远距离farthest超过当前位置i，就可以继续，否则说明无法跳过
            if(farthest<=i) {
                return false;
            }
        }

        return farthest>=n-1;
    }


    /**
     * 求到达最后位置，需要的最小次数
     *
     * 该题总是可以到达
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        int n = nums.length;
        int end = 0, farthest = 0;
        int jumps = 0;
        for (int i = 0; i < n - 1; i++) {
            farthest = Math.max(nums[i] + i, farthest);

            if (end == i) {
                //每次达到边界后，更新步数，定义新的边界
                jumps++;
                end = farthest;
            }
        }
        return jumps;
    }
}
