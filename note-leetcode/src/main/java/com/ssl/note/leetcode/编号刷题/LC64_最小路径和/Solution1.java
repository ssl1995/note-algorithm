package com.ssl.note.leetcode.编号刷题.LC64_最小路径和;

public class Solution1 {

  /**
   * 题目：给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
   * 说明：每次只能向下或者向右移动一步。
   * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
   * 输出：7
   * 解释：因为路径 1→3→1→1→1 的总和最小。
   */
  public int minPathSum(int[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    // 求和问题，不能单独初始化第一列或者第一行
    int[] dp = new int[n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (i == 0 && j == 0) {
          dp[j] = grid[i][j];
        } else if (i == 0) {
          dp[j] = dp[j - 1] + grid[i][j];
        } else if (j == 0) {
          dp[j] = dp[j] + grid[i][j];
        } else {
          dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
        }
      }
    }

    return dp[n - 1];
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
    System.out.println(solution.minPathSum(grid));
  }
}
