package com.ssl.note.leetcode.编号刷题.LC51_N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

  /**
   * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
   * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
   * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
   * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
   * 输入：n = 4
   * 输出：
   * [[".Q..",
   * "...Q",
   * "Q...",
   * "..Q."],
   * ["..Q.",
   * "Q...",
   * "...Q",
   * ".Q.."]]
   * 解释：如上图所示，4 皇后问题存在两个不同的解法。
   */
  public List<List<String>> solveNQueens(int n) {

    char[][] board = new char[n][n];
    for (char[] row : board) {
      Arrays.fill(row, '.');
    }
    // 列是否使用过
    boolean[] colUsed = new boolean[n];
    // 主对角线：左上到右下
    // (0,0), (1,1), (2,2), (3,3) → 0-0=0, 1-1=0, 2-2=0, 3-3=0
    //  特点：行 - 列 = 常数
    boolean[] diag1 = new boolean[2 * n];
    // 副对角线：右上到左下
    // (0,3), (1,2), (2,1), (3,0) → 0+3=3, 1+2=3, 2+1=3, 3+0=3
    // 行 + 列 = 常数
    boolean[] diag2 = new boolean[2 * n];

    List<List<String>> res = new ArrayList<>();
    dfs(board, 0, n, colUsed, diag1, diag2, res);

    return res;
  }

  private void dfs(char[][] board, int row, int n, boolean[] colUsed, boolean[] diag1, boolean[] diag2, List<List<String>> res) {
    if (row == n) {
      addToRes(board, res);
      return;
    }
    for (int col = 0; col < n; col++) {
      // 主对角线在数组中下标：row-col的最小值在(0,n-1)位置，为了让数组下标不越界，加上n-1
      int d1 = row - col + n - 1;
      // 副对角线在数组中的下标：row+col最小值(0,0)就是大于0的，所以不用加任何数
      int d2 = row + col;
      if (colUsed[col] || diag1[d1] || diag2[d2]) {
        continue;
      }
      board[row][col] = 'Q';
      colUsed[col] = true;
      diag1[d1] = true;
      diag2[d2] = true;

      dfs(board, row + 1, n, colUsed, diag1, diag2, res);

      board[row][col] = '.';
      colUsed[col] = false;
      diag1[d1] = false;
      diag2[d2] = false;
    }
  }

  private void addToRes(char[][] board, List<List<String>> res) {
    List<String> temp = new ArrayList<>();
    for (char[] cs : board) {
      temp.add(String.copyValueOf(cs));
    }
    res.add(temp);
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int n = 4;
    List<List<String>> res = solution.solveNQueens(n);
    System.out.println(res);
  }


}
