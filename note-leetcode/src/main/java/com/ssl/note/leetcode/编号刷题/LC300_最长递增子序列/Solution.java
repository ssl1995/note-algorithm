package com.ssl.note.leetcode.编号刷题.LC300_最长递增子序列;

import java.util.Arrays;

public class Solution {

  /**
   * 最长递增子序列
   * 输入：nums = [10,9,2,5,3,7,101,18]
   * 输出：4
   * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4
   * 时间复杂度：O(n^2)，稍微有点长，可以用下一个版本的优化
   * 空间复杂度：O(n)
   */
  public int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int n = nums.length;
    // dp[i]表示以nums[i]为结尾的最长递增子序列的长度
    int[] dp = new int[n];
    // 每一个数都可以是自己的子序列
    Arrays.fill(dp, 1);

    int maxLen = 1;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < i; j++) {
        // 子序列，递增
        if (nums[j] < nums[i]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);

          maxLen = Math.max(maxLen, dp[i]);
        }
      }
    }

    return maxLen;
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] arr1 = {10, 9, 1, 5, 2, 6, 66, 18};
    System.out.println(solution.lengthOfLIS(arr1));
  }
}