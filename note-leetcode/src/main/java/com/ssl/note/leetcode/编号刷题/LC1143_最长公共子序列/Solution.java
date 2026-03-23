package com.ssl.note.leetcode.编号刷题.LC1143_最长公共子序列;

public class Solution {
  /**
   * 最长公共子序列
   * 输入：text1 = "abcde", text2 = "ade"输出：3
   * 解释：最长公共子序列是 "ade"，它的长度为 3。
   */
  public int longestCommonSubsequence(String text1, String text2) {
    // 边界判断：空串直接返回0
    if (text1 == null || text2 == null || text1.isEmpty() || text2.isEmpty()) {
      return 0;
    }

    // 转换为字符数组便于操作
    char[] str1 = text1.toCharArray();
    char[] str2 = text2.toCharArray();
    int m = str1.length;  // 字符串1长度
    int n = str2.length;  // 字符串2长度

    // 创建dp表：dp[i][j]表示str1[0..i]和str2[0..j]的最长公共子序列长度
    int[][] dp = new int[m][n];

    // ========== 初始化 ==========
    // 1. dp[0][0]：两个字符串第一个字符的比较
    dp[0][0] = str1[0] == str2[0] ? 1 : 0;

    // 2. 第一列（j=0）：str2只取第一个字符str2[0]
    // 逐行遍历str1，如果当前字符等于str2[0]，则找到公共字符记为1
    // 否则继承上方dp[i-1][0]的值（之前已经找到的公共字符）
    for (int i = 1; i < m; i++) {
      dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
    }

    // 3. 第一行（i=0）：str1只取第一个字符str1[0]
    // 逐列遍历str2，如果当前字符等于str1[0]，则找到公共字符记为1
    // 否则继承左方dp[0][j-1]的值（之前已经找到的公共字符）
    for (int j = 1; j < n; j++) {
      dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
    }

    // ========== 填表 ==========
    // 从第二行第二列开始，遍历整个dp表
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        // 状态转移：
        // 情况1：当前两个字符不相等
        // 此时公共子序列来源于：要么丢弃str1[i]，要么丢弃str2[j]
        // 取两者的较大值：max(上方dp[i-1][j], 左方dp[i][j-1])
        if (str1[i] != str2[j]) {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        } else {
          // 情况2：当前两个字符相等
          // 则公共子序列长度 = 之前的最长公共子序列 + 1
          // 来源于左上方dp[i-1][j-1]（去掉这两个字符后的最长公共子序列）
          dp[i][j] = dp[i - 1][j - 1] + 1;
        }
      }
    }

    // 返回右下角的值：整个字符串的最长公共子序列长度
    return dp[m - 1][n - 1];
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String s1 = "abcde";
    String s2 = "ade";
    System.out.println(solution.longestCommonSubsequence(s1, s2));  // 输出3
  }
}
