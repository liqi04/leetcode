package leetcode;

import java.util.Arrays;

/**
 *
 53. 最大子数组和
 中等
 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

 子数组
 是数组中的一个连续部分。



 示例 1：

 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 输出：6
 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 示例 2：

 输入：nums = [1]
 输出：1
 示例 3：

 输入：nums = [5,4,-1,7,8]
 输出：23


 提示：

 1 <= nums.length <= 105
 -104 <= nums[i] <= 104
 */
public class MaxSubArray {

    /**
     * 双指针
     * 滑动窗口，如果窗口和为负数，那么移动窗口
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int left = 0,right=0,windowsRight = 0;
        while (right < nums.length){
             sum += nums[right];
            if (sum > max) {
                max = sum;
                windowsRight = right;
            }
            while (sum <= 0 && left<= right){
                sum -= nums[left];
                left++;
            }
            right++;
        }
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, left, windowsRight + 1)));
        return max;
    }

    /**
     * 动态规划解法
     * 定义：dp[i] 记录以 nums[i] 为结尾的「最大子数组和」
     * 那么dp[i + 1] 的最大和为 dp[i] 和 dp[i] + num[i+1] 的最大值
     * @param nums
     * @return
     */
    static int maxSubArrayDP(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        // 定义：dp[i] 记录以 nums[i] 为结尾的「最大子数组和」
        int[] dp = new int[n];
        // base case
        // 第一个元素前面没有子数组
        dp[0] = nums[0];
        // 状态转移方程
        // 得到 nums 的最大子数组
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            res = Math.max(res, dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArrayDP(nums));
        System.out.println(maxSubArray(nums));
    }
}
