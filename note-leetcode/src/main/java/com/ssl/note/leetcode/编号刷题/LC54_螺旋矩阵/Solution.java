package com.ssl.note.leetcode.编号刷题.LC54_螺旋矩阵;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
   */
  public List<Integer> spiralOrder(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int top = 0, bottom = m - 1;
    int left = 0, right = n - 1;

    List<Integer> res = new ArrayList<>();
    while (top <= bottom && left <= right) {
      // 向左
      for (int i = left; i <= right; i++) {
        res.add(matrix[top][i]);
      }
      top++;
      for (int i = top; i <= bottom; i++) {
        res.add(matrix[i][right]);
      }
      right--;
      if (top <= bottom) {
        for (int i = right; i >= left; i--) {
          res.add(matrix[bottom][i]);
        }
        bottom--;
      }
      if(left<=right){
        for (int i = bottom; i >=top ; i--) {
          res.add(matrix[i][left]);
        }
        left++;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    System.out.println(solution.spiralOrder(matrix));
  }
}
