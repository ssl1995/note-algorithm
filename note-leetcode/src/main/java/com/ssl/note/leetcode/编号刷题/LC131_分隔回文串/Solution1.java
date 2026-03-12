package com.ssl.note.leetcode.编号刷题.LC131_分隔回文串;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {

  /**
   * 分割回文串
   * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
   * 输入：s = "aab"
   * 输出：[["a","a","b"],["aa","b"]]
   */
  public List<List<String>> partition(String s) {
    if (s == null) {
      return new ArrayList<>();
    }
    int n = s.length();
    // 动态规划，预处理回文串串的判断
    boolean[][] dp = new boolean[n][n];
    for (int i = n - 1; i >= 0; i--) {
      for (int j = i; j <= n - 1; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          // 1、<2个字符一定是回文串
          // 2、当前i,j是相同字符串，那么判断i+1,j-1的字符串是否是回文串
          dp[i][j] = (j - i + 1 <= 2) || dp[i + 1][j - 1];
        }
      }
    }

    List<List<String>> res = new ArrayList<>();
    dfs(s, 0, dp, new ArrayList<>(), res);
    return res;
  }

  private void dfs(String s, int index, boolean[][] dp, List<String> path, List<List<String>> res) {
    if (index == s.length()) {
      res.add(new ArrayList<>(path));
      return;
    }
    for (int i = index; i < s.length(); i++) {
      // 用dp判断回文串，时间复杂度O(1)
      if (!dp[index][i]) {
        continue;
      }
      path.add(s.substring(index, i + 1));
      // [index,i]已经是回文了，递归i+1检查是不是回文串
      dfs(s, i + 1, dp, path, res);
      path.remove(path.size() - 1);
    }
  }


  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "aab";
    System.out.println(solution.partition(s));
  }
}
