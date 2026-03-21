package com.ssl.note.leetcode.编号刷题.LC322_零钱兑换;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/6/20 23:07
 * @description
 */
public class Solution {

  /**
   * 零钱兑换
   * =完全背包问题
   * 输入：coins = [1, 2, 5], amount = 11
   * 输出：3
   * 解释：11 = 5 + 5 + 1
   * 1 <= coins.length <= 12
   * 1 <= coins[i] <= 231 - 1
   * 0 <= amount <= 104
   */
  public int coinChange(int[] coins, int amount) {
    if (coins == null || coins.length == 0) {
      return -1;
    }
    int[] dp = new int[amount + 1];
    // 模拟不可达，设置值为amount + 1
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;

    for (int i = 1; i <= amount + 1; i++) {
      for (int coin : coins) {
        if (i - coin >= 0) {
          dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
        }
      }
    }

    return dp[amount] > amount ? -1 : dp[amount];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] coins = {3};
    int t = 2;
    System.out.println(solution.coinChange(coins, t));
  }
}
