package com.ssl.note.leetcode.编号刷题.LC322_零钱兑换;

public class Solution1 {

  /**
   * 01背包问题
   * 背包容量为C，物品的体积和价值分别为w和v，求背包中物品的个数，使得背包中物品体积和为C，并且价值最大
   * 限制：每个物品不能重复选择，只能选0或1个
   * 逆序遍历
   * 动态转移方程：dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
   * 输入：coins = [1, 2, 5], amount = 8
   */
  public int knapsack01(int[] w, int[] v, int C) {
    int n = w.length;
    int[] dp = new int[C + 1];
    for (int i = 0; i < n; i++) {
      for (int j = C; j >= w[i]; j--) {  // 逆序遍历
        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
      }
    }
    return dp[C];
  }

  /**
   * 完全背包问题
   * 背包容量为C，物品的体积和价值分别为w和v，求背包中物品的个数，使得背包中物品体积和为C，并且价值最大
   * 限制：每个物品能重复选择
   * 正序遍历
   * 动态转移方程：dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
   * 输入：coins = [1, 2, 5], amount = 11
   */
  public int completeKnapsack(int[] w, int[] v, int C) {
    int n = w.length;
    int[] dp = new int[C + 1];
    for (int i = 0; i < n; i++) {
      for (int j = w[i]; j <= C; j++) {  // 正序遍历
        dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
      }
    }
    return dp[C];
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] w = {2, 3, 4};
    int[] v = {3, 4, 5};
    int C = 8;
    // 正确解是 2+3+4=9组成最大值
    System.out.println(solution.knapsack01(w, v, C));// 9
    // 完全背包：4个2是组成8的最大价值解
    System.out.println(solution.completeKnapsack(w, v, C));// 12
  }

}
