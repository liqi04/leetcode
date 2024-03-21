package leetcode;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 * 中等
 * 相关标签
 * 相关企业
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 *
 *
 *
 * 示例 1：
 *
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 * 示例 2：
 *
 * 输入：coins = [2], amount = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = new int[]{ 1,2, 5};
        System.out.println(coinChange(coins, 100));
    }

    /**
     * 循环解法
     * 定义 dp[i] 为凑成金额 i 所需的最少硬币数量
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        // dp长度为amount+1，因为要计算[0~amount]的最小硬币数
        int[] dp = new int[amount + 1];
        // 设置默认值为amount+1，因为硬币数量不可能超过amount
        Arrays.fill(dp,amount+1);
        // 如果amount=0，那么硬币数量为0
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                int tempAmount = i - coin;
                // 如果tempAmount小于0，那么说明凑不出来
                if (tempAmount < 0) {
                    continue;
                }
                // 走到这里说明凑得出来，那么取最小值
                dp[i] = Math.min(dp[i], dp[tempAmount] + 1);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    /**
     * 动态规划解法
     * 要求出amount的最小硬币数，可以先求出amount-1的最小硬币数，然后再加上1个硬币,
     * @param coins
     * @param amount
     * @return
     */

    public static int coinChange2(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        if (amount < 0 ){
            return -1;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int sub = coinChange2(coins, amount - coins[i]);
            if (sub == -1){
                continue;
            }
            res = Math.min(res, sub + 1);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
