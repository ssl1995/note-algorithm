package com.ssl.note.leetcode.编号刷题.LC54_螺旋矩阵;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {

  /**
   * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
   */
  private List<Integer> res;

  public List<Integer> spiralOrder(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;

    int a = 0, b = 0;
    int c = m - 1, d = n - 1;

    res = new ArrayList<>();

    while (a <= c && b <= d) {
      f(matrix, a++, b++, c--, d--);
    }

    return res;
  }

  // 开区间取值
  private void f(int[][] matrix, int a, int b, int c, int d) {
    // 只剩一行：闭区间直接填完，结束
    if (a == c) {
      for (int i = b; i <= d; i++) {
        res.add(matrix[a][i]);
      }
      return;
    }
    // 只剩一列：闭区间直接填完，结束
    if (b == d) {
      for (int i = a; i <= c; i++) {
        res.add(matrix[i][b]);
      }
      return;
    }

    // 以下保证至少两行两列，四条边开区间各留一个角给下一条边
    for (int i = b; i < d; i++) {
      res.add(matrix[a][i]);
    }

    for (int i = a; i < c; i++) {
      res.add(matrix[i][d]);
    }

    for (int i = d; i > b; i--) {
      res.add(matrix[c][i]);
    }

    for (int i = c; i > a; i--) {
      res.add(matrix[i][b]);
    }
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
//    int[][] matrix = {{1}, {2}, {3}};
    System.out.println(solution.spiralOrder(matrix));
  }
}
