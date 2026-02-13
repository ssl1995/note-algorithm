package com.ssl.note.leetcode.编号刷题.LC48_旋转图像;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/1/24 9:01 AM
 * @description
 */
public class Solution1 {
  /**
   * 旋转图像
   * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
   * 输出：[[7,4,1],[8,5,2],[9,6,3]]
   */
  public void rotate(int[][] matrix) {
    if (matrix == null || matrix.length < 2) {
      return;
    }
    // 左上角和右下角坐标
    int tR = 0, tC = 0;
    int dR = matrix.length - 1, dC = matrix[0].length - 1;

    while (tR < dR) {
      rotateEdge(matrix, tR++, tC++, dR--, dC--);
    }
  }

  /**
   * 1 2 3    7 4 1
   * 4 5 6 -> 8 5 2
   * 7 8 9    9 6 3
   * 四个点：
   * A(tR,tC) D(tR,dC)
   * B(dR,tC) C(dR,dC)
   * 逆时针：temp=A → A=B → B=C → C=D → D=temp
   */
  private void rotateEdge(int[][] matrix, int tR, int tC, int dR, int dC) {
    int times = dR - tR;
    for (int i = 0; i < times; i++) {
      int temp = matrix[tR][tC + i];
      matrix[tR][tC + i] = matrix[dR - i][tC];
      matrix[dR - i][tC] = matrix[dR][dC - i];
      matrix[dR][dC - i] = matrix[tR + i][dC];
      matrix[tR + i][dC] = temp;
    }
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
    solution.rotate(matrix);
    // 期望输出: [[13,9,5,1],[14,10,6,2],[15,11,7,3],[16,12,8,4]]
    System.out.println(Arrays.deepToString(matrix));
  }
}
