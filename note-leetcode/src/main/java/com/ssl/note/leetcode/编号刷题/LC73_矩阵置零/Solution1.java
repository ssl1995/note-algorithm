package com.ssl.note.leetcode.编号刷题.LC73_矩阵置零;

import java.util.Arrays;

public class Solution1 {

  /**
   * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
   */
  public void setZeroes(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    boolean rowZero = false;
    boolean colZero = false;

    // 1、首行首列是否有空
    for (int i = 0; i < n; i++) {
      // 首行
      if (matrix[0][i] == 0) {
        rowZero = true;
        break;
      }
    }
    for (int i = 0; i < m; i++) {
      // 首列
      if (matrix[i][0] == 0) {
        colZero = true;
        break;
      }
    }

    // 2、从[1,1]开始扫描是否有0，用首行首列标记
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    // 3、从[1,1]开始扫描，重置为0,不能破坏首行首列的标记信息
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }
    // 4、首行首列是否重置为0
    if (rowZero) {
      for (int i = 0; i < n; i++) {
        matrix[0][i] = 0;
      }
    }
    if (colZero) {
      for (int i = 0; i < m; i++) {
        matrix[i][0] = 0;
      }
    }
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};

    solution.setZeroes(matrix);
    System.out.println(Arrays.deepToString(matrix));
  }
}
