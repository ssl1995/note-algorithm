package com.ssl.note.leetcode.编号刷题.LC10_正则表达式匹配;

public class Solution {
  /**
   * 实现匹配.和*的正则表达式；.表示任意一个字符，*表示前面的字符可以出现任意次（包含0次）
   * 动态规划法：s=aaa，p=ab*.*，返回true
   */
  public boolean isMatch(String s, String p) {
    int m = s.length();
    int n = p.length();
    // dp[i][j]表示s前i个字符,p前j个字符是否匹配
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for (int i = 2; i < n + 1; i++) {
      // s:空串
      // p=a*b*这种形式
      dp[0][i] = dp[0][i - 2] && p.charAt(i - 1) == '*';
    }

    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        // *有2种情况:匹配前面的0次或无数次
        if (p.charAt(j - 1) == '*') {
          // 情况1：匹配0次=删除p的最近2个字符，比如s=a,p=ab*
          if (dp[i][j - 2]) {
            dp[i][j] = true;
          } else {
            // 情况2：匹配1次或多次
            dp[i][j] = dp[i - 1][j] // s 的前 i-1 个字符已经和 p 的前 j 个字符匹配
                &&// s 的第 i 个字符与 * 前面的字符匹配，或者 * 前面是 .
                (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.');
          }
          continue;
        }
        // .或者相同字符，有2种情况
        // 情况1：相同字符
        if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) {
          dp[i][j] = true;
        }
        // 清况2：遇到.
        if (dp[i - 1][j - 1] && p.charAt(j - 1) == '.') {
          dp[i][j] = true;
        }
      }
    }

    return dp[m][n];
  }

  public static void main(String[] args) {
    String s = "aaa";
    String p = "ab*.*";
    Solution solution = new Solution();
        /*
         true	false	false	false	false	false
         false	true	false	true	false	true
         false	false	false	false	true	true
         false	false	false	false	false	true
         */
    System.out.println(solution.isMatch(s, p));
  }
}


