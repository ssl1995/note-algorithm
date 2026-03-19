package com.ssl.note.leetcode.编号刷题.LC118_杨辉三角;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
   * 1.第一行只有一个数字1。
   * 2.每一行的首尾数字都是1。
   * 3.每一行中间的数字等于上一行中相邻两个数字的和。
   * 输入: numRows = 5
   * 输出: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
   * - 时间复杂度：O(n²)，其中n是numRows，需要计算每个元素
   * - 空间复杂度：O(n²)，需要存储整个杨辉三角
   */
  public List<List<Integer>> generate(int numRows) {
    int[][] dp = new int[numRows][numRows];
    for (int i = 0; i < numRows; i++) {
      dp[i][0] = 1;
      dp[i][i] = 1;

      for (int j = 1; j < i; j++) {
        dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
      }
    }
    List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < numRows; i++) {
      List<Integer> row = new ArrayList<>();
      for (int j = 0; j <= i; j++) {
        row.add(dp[i][j]);
      }
      res.add(row);
    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int numRows = 5;
    System.out.println(solution.generate(numRows));
  }
}
