package com.pc.algorithm.dp.rob;


import com.pc.algorithm.datastructure.TreeNode;
import java.util.Arrays;

/**
 * 打家劫舍
 *
 *
 * @author pengchao
 * @date 15:09 2021-01-08
 */
public class Rob {


    public static void main(String[] args) {


//        System.out.println(dp(new int[]{2,3,2}));
        System.out.println(dp(new int[]{1,2,3,1}));


    }


    /**
     *
     * 1.相连的不能偷，房屋是一排，头尾不相连
     * 输入：nums = [2,3,2]
     * 输出：3
     *
     * 输入：nums = [1,2,3,1]
     * 输出：4
     *
     *
     * 选择
     * 抢或不抢
     *
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {

        int[] memo = new int[nums.length];
        Arrays.fill(memo,-1);

        return dfs(nums, 0,memo);
    }

    /**
     *
     * @param nums
     * @param start 表示从哪开始抢
     * @return
     */
    private static int dfs(int[] nums, int start,int[] memo) {
        //  走过了最后一间房子，所以抢钱为0
        if(start>=nums.length) {
            return 0;
        }

        if (memo[start] != -1) return memo[start];


        int res = Math.max(dfs(nums,start+1,memo),dfs(nums,start+2,memo)+nums[start]);
        memo[start] = res;
        return res;
    }


    /**
     *
     * 状态转移方程
     *  dp[i] = max(dp[i+1],dp[i+2]+nums[i])
     *
     * base case
     *
     * n 为最后一个房子的位置
     *  dp[n] = 0
     *  dp[n+1] = 0
     *
     *
     * @param nums
     * @return
     */
    private static int dp(int[] nums) {

        int n = nums.length;
        // dp[i] = x 表示：
        // 从第 i 间房子开始抢劫，最多能抢到的钱为 x
        // base case: dp[n] = 0
        int[] dp = new int[n + 2];

        for(int i=n-1;i>=0;i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
            System.out.println( " dp["+i+"]="+dp[i]);
        }
        return dp[0];
    }

    /**
     * 优化
     * @param nums
     * @return
     */
    private static int _dp(int[] nums) {
        int n = nums.length;
        // 记录 dp[i+1] 和 dp[i+2]
        int dp_i_1 = 0, dp_i_2 = 0;
        // 记录 dp[i]
        int dp_i = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }

    /**
     * 下面这种解法和上面的区别是，自顶向下
     *  需要考虑边界问题，也就是 dp[i-1] dp[i-2]的问题
     *
     *  如果自下而上，dp[i+1] dp[i+2] 默认可以为0 ,而dp[i-1] dp[i-2] 需要特殊处理
     *
     *
     *
     * @param nums
     * @return
     */
    public int rob_(int[] nums) {

        int[] dp = new int[nums.length+1];

        for(int i=0;i<=nums.length;i++) {
            if(i==0) {
                dp[i] = 0;
                continue;
            }
            if(i==1) {
                dp[i] = nums[i-1];
                continue;
            }
            if(i==2) {
                dp[i] = Math.max(nums[i-1],nums[i-2]);
                continue;
            }
            //抢和不抢
            dp[i] = Math.max(dp[i-2]+nums[i-1],dp[i-1]);

        }

        return dp[nums.length];

    }


    /**
     *
     * 2.环形数组，房屋首位是相连的
     *
     * 三种情况种选最大值
     * 1一种只选第一个
     * 2一种只选最后一个
     * 3或者头尾都不要 （由于金额非负，所以这种情况肯定最小）
     *
     *
     *
     */
    public static int rob_circle(int[] nums) {
        if(nums.length==0) {
            return 0;
        }
        if(nums.length==1) {
            return nums[0];
        }

        return Math.max(dp(nums,0,nums.length-2),dp(nums,1,nums.length-1));
    }

    private static int dp(int[] nums, int start, int end) {

        int[] dp = new int[nums.length+2];

        for(int i = end;i>=start;i--) {
            dp[i] = Math.max(dp[i+1],dp[i+2]+nums[i]);
        }
        return dp[start];
    }

    /**
     *
     * 3.房屋不是一排也不是一圈 而是一颗二叉树
     *
     *
     * dp[i] =
     *
     *
     */
    public int rob(TreeNode<Integer> root) {
        if(root==null) {
            return 0;
        }
        //抢根节点,需要跳一个节点
        int do_it = root.val
                + (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));

        //不抢根节点，不需要跳一个节点
        int not_do =
                rob(root.left) +  //左
                rob(root.right);//右

        return Math.max(do_it,not_do);

    }


}
