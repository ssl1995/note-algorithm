package com.ssl.note.leetcode.编号刷题.LC1143_最长公共子序列;

public class Solution1 {
  /**
   * 最长公共子序列 - 空间优化版本
   * 输入：text1 = "abcde", text2 = "ade"输出：3
   * 解释：最长公共子序列是 "ade"，它的长度为 3。
   */
  public int longestCommonSubsequence(String text1, String text2) {
    // 确保 text1 是较长的字符串，这样 dp 数组更小
    if (text1.length() < text2.length()) {
      return longestCommonSubsequence(text2, text1);
    }

    int m = text1.length();  // 长字符串长度
    int n = text2.length();  // 短字符串长度

    // 一维dp数组：dp[j] 表示 text2[0..j-1] 和当前 text1 前缀的最长公共子序列
    int[] dp = new int[n + 1];

    // 遍历 text1 的每个字符
    for (int i = 1; i <= m; i++) {
      // prev：保存 dp[j-1] 的值，即上一轮循环中的"左上方" dp[i-1][j-1]
      int prev = 0;

      // 遍历 text2 的每个字符
      for (int j = 1; j <= n; j++) {
        // temp：保存当前 dp[j] 的旧值（更新 prev 用）
        // 更新前：dp[j] 存储的是上一行 i-1 的值（相当于 dp[i-1][j]）
        int temp = dp[j];

        if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
          // 字符相等：当前位置的最长公共子序列 = 左上角 + 1
          // prev 就是左上角 dp[i-1][j-1] 的值
          dp[j] = prev + 1;
        } else {
          // 字符不等：取 较大值
          // dp[j]：上方 dp[i-1][j]（刚被 temp 保存过）
          // dp[j-1]：左方 dp[i][j-1]（本轮刚更新过）
          dp[j] = Math.max(dp[j], dp[j - 1]);
        }

        // 更新 prev：为下一列的左上角值做准备
        // prev 变成 temp（即这一轮更新前的 dp[j]，相当于 dp[i-1][j]）
        prev = temp;
      }
    }

    // 返回 dp[n]：text2 整体和 text1 的最长公共子序列长度
    return dp[n];
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s1 = "abcde";
    String s2 = "ade";
    System.out.println(solution.longestCommonSubsequence(s1, s2));  // 输出3
  }
}
