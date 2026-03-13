package com.ssl.note.leetcode.编号刷题.LC51_N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {

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
    List<List<String>> ans = new ArrayList<>();
    char[][] board = new char[n][n];
    for (char[] row : board) Arrays.fill(row, '.');
    int limit = (1 << n) - 1;
    dfs(0, n, limit, 0, 0, 0, board, ans);
    return ans;
  }

  // 位运算回溯
  private void dfs(int row, int n, int limit, int colMask, int diag1Mask, int diag2Mask, char[][] board, List<List<String>> ans) {
    if (row == n) {
      List<String> path = new ArrayList<>();
      for (char[] chars : board) path.add(new String(chars));
      ans.add(path);
      return;
    }
    int available = limit & ~(colMask | diag1Mask | diag2Mask);
    while (available != 0) {
      int bit = available & -available;
      int col = Integer.numberOfTrailingZeros(bit);
      board[row][col] = 'Q';
      dfs(row + 1, n, limit,
          colMask | bit,
          ((diag1Mask | bit) << 1) & limit,
          (diag2Mask | bit) >> 1,
          board, ans);
      board[row][col] = '.';
      available -= bit;
    }
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int n = 4;
    List<List<String>> res = solution.solveNQueens(n);
    System.out.println(res);
  }


}
