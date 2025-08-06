package com.ssl.note.leetcode.代码随想录.A01.数组;

import java.util.Arrays;

public class LC59 {

  /**
   * 给你一个正整数 n ，生成一个包含 1 到 n平方 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
   * 输入：n = 3
   * 输出：[[1,2,3],[8,9,4],[7,6,5]]
   */
  public int[][] generateMatrix(int n) {
    if (n <= 0) {
      return new int[0][0];
    }
    int[][] res = new int[n][n];
    int startX = 0;
    int startY = 0;
    int i = 0;
    int j = 0;

    int loop = 1; // 循环次数
    int offset = 1;
    int count = 1;

    while (loop <= n / 2) {
      // 上边
      for (j = startY; j < n - offset; j++) {
        res[startX][j] = count++;
      }

      // 右边
      for (i = startX; i < n - offset; i++) {
        res[i][j] = count++;
      }

      // 下边
      for (; j > startY; j--) {
        res[i][j] = count++;
      }

      // 左边
      for (; i > startX; i--) {
        res[i][j] = count++;
      }

      startX++;
      startY++;
      offset++;
      loop++;
    }

    // n是奇数，中心位置填充最后一个元素 = n的平方
    if (n % 2 == 1) {
      res[startX][startY] = n * n;
    }

    return res;
  }

  public static void main(String[] args) {
    LC59 lc59 = new LC59();
    int[][] res = lc59.generateMatrix(3);
    System.out.println(Arrays.deepToString(res));
  }
}
