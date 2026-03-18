package com.ssl.note.leetcode.编号刷题.LC121_买卖股票的最佳时机;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/15 2:56 PM
 * @Describe:
 */
public class Solution1 {

  /**
   * 买卖股票的最佳时机
   * 输入：[7,1,5,3,6,4]
   * 输出：5
   * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
   * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
   */
  public int maxProfit(int[] prices) {
    if (prices == null) {
      return 0;
    }
    int n = prices.length;
    int[][] dp = new int[n][2];
    // i天不持有股票最大利润
    dp[0][0] = 0;
    // i天持有股票最大利润
    dp[0][1] = -prices[0];

    for (int i = 1; i < n; i++) {
      // i天不持有股票的最大利润：i-1天就没有持有 or i-1天持有，当天卖出
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
      // i天持有股票的最大利润：i-1天就持有，当天不变 or 当天买入
      dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
    }
    // 返回最后一天不持有股票的最大利润
    return dp[n - 1][0];
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {7, 1, 5, 3, 6, 4};
    System.out.println(solution.maxProfit(nums));
  }
}
