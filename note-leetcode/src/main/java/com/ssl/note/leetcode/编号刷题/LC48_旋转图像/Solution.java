package com.ssl.note.leetcode.编号刷题.LC48_旋转图像;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/1/24 9:01 AM
 * @description
 */
public class Solution {
  /**
   * 旋转图像
   * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
   * 输出：[[7,4,1],[8,5,2],[9,6,3]]
   */
  public void rotate(int[][] matrix) {
    if (matrix == null || matrix.length < 2) {
      return;
    }
    int n = matrix.length;
    // 1、转置：交换 matrix[i][j] 和 matrix[j][i]
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        swap(matrix, i, j, j, i);
      }
    }
    // 2、水平翻转：每行左右交换 matrix[i][j] 和 matrix[i][n-1-j]
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n / 2; j++) {
        swap(matrix, i, j, i, n - 1 - j);
      }
    }
  }

  private void swap(int[][] nums, int i1, int j1, int i2, int j2) {
    if (nums == null) {
      return;
    }
    int temp = nums[i1][j1];
    nums[i1][j1] = nums[i2][j2];
    nums[i2][j2] = temp;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
//    int[][] matrix = {{1, 2}, {3, 4}};
    solution.rotate(matrix);
    // 期望输出: [[13,9,5,1],[14,10,6,2],[15,11,7,3],[16,12,8,4]]
    System.out.println(Arrays.deepToString(matrix));
  }
}
