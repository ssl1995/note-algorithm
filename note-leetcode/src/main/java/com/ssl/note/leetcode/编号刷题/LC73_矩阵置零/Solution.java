package com.ssl.note.leetcode.编号刷题.LC73_矩阵置零;

import java.util.Arrays;

public class Solution {

  /**
   * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
   */
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    boolean[] row = new boolean[m];
    boolean[] col = new boolean[n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          row[i] = true;
          col[j] = true;
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (row[i] || col[j]) {
          matrix[i][j] = 0;
        }
      }
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};

    solution.setZeroes(matrix);
    System.out.println(Arrays.deepToString(matrix));
  }
}
