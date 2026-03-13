package com.ssl.note.leetcode.编号刷题.LC51_N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

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
    if (n < 0) {
      return new ArrayList<>();
    }

    char[][] board = new char[n][n];
    for (char[] c : board) {
      Arrays.fill(c, '.');
    }

    List<List<String>> res = new ArrayList<>();
    backtrack(board, 0, n, res);

    return res;
  }

  private void backtrack(char[][] board, int row, int n, List<List<String>> res) {
    if (row == n) {
      addToRes(board, res);
      return;
    }
    for (int col = 0; col < n; col++) {
      if (!isValid(board, row, col, n)) {
        continue;
      }
      board[row][col] = 'Q';

      backtrack(board, row + 1, n, res);

      board[row][col] = '.';
    }
  }

  /**
   * 0       1        2       3
   * 0  (0,0)   (0,1)   (0,2)   (0,3)
   * |                         |
   * |    ↘    ↘    ↘          |  ↙    ↙    ↙    ↙
   * |                         |
   * 1  (1,0)   (1,1)   (1,2)   (1,3)
   * |                         |
   * |    ↘    ↘    ↘          |  ↙    ↙    ↙    ↙
   * |                         |
   * 2  (2,0)   (2,1)   (2,2)   (2,3)
   * |                         |
   * |    ↘    ↘    ↘          |  ↙    ↙    ↙    ↙
   * |                         |
   * 3  (3,0)   (3,1)   (3,2)   (3,3)
   */
  private boolean isValid(char[][] board, int row, int col, int n) {
    // 逐行从上往下放置，检查上半部分
    // 检查列：当前列的上半部分
    for (int i = 0; i < row; i++) {
      if (board[i][col] == 'Q') {
        return false;
      }
    }

    // 检查45°斜线（左上 → 右下）
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }

    // 检查135°斜线（右上 → 左下）
    for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
      if (board[i][j] == 'Q') {
        return false;
      }
    }

    return true;
  }

  private void addToRes(char[][] board, List<List<String>> res) {
    List<String> temp = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        sb.append(board[i][j]);
      }
      temp.add(sb.toString());

      sb.setLength(0);
    }
    res.add(temp);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 4;
    // [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
    List<List<String>> res = solution.solveNQueens(n);
    System.out.println(res);
  }


}
