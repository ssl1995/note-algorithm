package com.ssl.note.leetcode.编号刷题.LC74_搜索矩阵;

public class Solution {

  /**
   * 给你一个满足下述两条属性的 m x n 整数矩阵：
   * 1、每行中的整数从左到右按非严格递增顺序排列。
   * 2、每行的第一个整数大于前一行的最后一个整数。
   * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
   */
  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    int n = matrix[0].length;
    int left = 0;
    int right = m * n - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      // 转成二维坐标
      // 用电影院找座位来理解：除法定行，取余定列
      int row = mid / n;
      int col = mid % n;
      if (matrix[row][col] < target) {
        left = mid + 1;
      } else if (matrix[row][col] > target) {
        right = mid - 1;
      } else {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
    int target = 3;
    System.out.println(solution.searchMatrix(matrix, target));
  }
}
