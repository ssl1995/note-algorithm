package com.ssl.note.leetcode.编号刷题.LC72_编辑距离;

/**
 * @author SongShengLin
 * @date 2022/1/27 9:11 AM
 * @description
 */
public class Solution1 {

  /**
   * 编辑距离
   * 返回将word1转换成word2所使用的最少操作数
   * 输入：word1 = "intention", word2 = "execution"
   * 输出：5
   * 解释：
   * intention -> inention (删除 't')
   * inention -> enention (将 'i' 替换为 'e')
   * enention -> exention (将 'n' 替换为 'x')
   * exention -> exection (将 'n' 替换为 'c')
   * exection -> execution (插入 'u')
   */
  // 一维数组空间优化
  public int minDistance(String word1, String word2) {
    if (word1.length() < word2.length()) {
      return minDistance(word2, word1);
    }
    int m = word1.length();
    int n = word2.length();
    int[] dp = new int[n + 1];

    for (int j = 1; j <= n; j++) {
      dp[j] = j;
    }

    for (int i = 1; i <= m; i++) {
      int prev = dp[0];  // 左上角的值
      dp[0] = i;         // 第一列更新
      for (int j = 1; j <= n; j++) {
        int temp = dp[j];
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
          dp[j] = prev;
        } else {
          dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), prev) + 1;
        }
        prev = temp;
      }
    }
    return dp[n];
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String w1 = "intention";
    String w2 = "execution";
    System.out.println(solution.minDistance(w1, w2));
  }
}
