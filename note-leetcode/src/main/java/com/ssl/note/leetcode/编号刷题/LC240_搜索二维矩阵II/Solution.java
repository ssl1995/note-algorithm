package com.ssl.note.leetcode.编号刷题.LC240_搜索二维矩阵II;

/**
 * @author SongShengLin
 * @date 2021/11/30 10:28 下午
 * @description
 */
public class Solution {
  /**
   * 二维数组中的查找，元素值从上到小、从左到右递增
   * 输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
   */
  public boolean findNumberIn2DArray(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0) {
      return false;
    }
    // 左上 右上
    // 左下 右下
    // 思路：从右上（或者左下）角开始，比target大，则往左下角移动；比target小，则往右上角移动
    // 原因：左上和右下，无论怎么变化指针，都是增或者减，都不能利用到特性
    int i = 0;
    int j = matrix[0].length - 1;
    while (i < matrix.length && j >= 0) {
      if (matrix[i][j] < target) {
        i++;
      } else if (matrix[i][j] > target) {
        j--;
      } else {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
    int target = 5;
    System.out.println(solution.findNumberIn2DArray(matrix, target));
  }
}
