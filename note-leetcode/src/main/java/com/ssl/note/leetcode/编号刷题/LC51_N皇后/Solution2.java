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
    List<List<String>> res = new ArrayList<>();
    char[][] board = new char[n][n];
    for (char[] row : board) {
      Arrays.fill(row, '.');
    }

    int limit = (1 << n) - 1;
    dfs(board, limit, 0, 0, 0, 0, res);
    return res;
  }

  /**
   * 位运算计算N皇后候选位置
   * limi:期望每1行所有候选位置，不可变，比如n=4，limit=1111
   * col:之前的列已经占用的位置，1表示已经占用
   * left:之前的左斜线已经占用的位置，1表示已经占用
   * right:之前的右斜线已经占用的位置，1表示已经占用
   */
  private void dfs(char[][] board, int limit, int row, int col, int left, int right, List<List<String>> res) {
    if (col == limit) {
      addRes(board, res);
      return;
    }
    // ban:已经被占用过的列、左右斜，这里是1=不可用，0=可用
    int ban = col | left | right;
    // can:找到可以用候选位置，从这里开始1=可用，0=不可用
    int can = limit & (~ban);
    // 遍历所有1的候选位置
    while (can != 0) {
      // 找最右侧1作为新位置放皇后
      int place = can & -can;
      // 候选位置删除用过的位置
      can ^= place;

      // place从右往左第一个1就是当前列放皇后的位置
//      int colIdx = getLastOneIndex(place);
      int colIdx = Integer.numberOfTrailingZeros(place);

      board[row][colIdx] = 'Q';
      // 递归下一行，当前列影响下一行的同列，左斜影响下一行右边，右斜影响下一行左边
      dfs(board, limit, row + 1, col | place, (left | place) >> 1, (right | place) << 1, res);
      board[row][colIdx] = '.';
    }
  }

  // 十进制从右到左第一个1位置，从0开始
  private int getLastOneIndex(int num) {
    int index = 0;
    for (int i = num; (i & 1) == 0; i >>= 1) {
      index++;
    }
    return index;
  }

  // 十进制从左到右第一个1位置，从0开始
  private int getFirstOneIndex(int num) {
//        return Integer.numberOfLeadingZeros(num);
//        return 31 - Integer.numberOfTrailingZeros(num);
    return 31 - getFirstOneIndex(num);
  }

  private void addRes(char[][] board, List<List<String>> res) {
    List<String> temp = new ArrayList<>();
    for (char[] row : board) {
      String rowStr = new String(row);
      temp.add(rowStr);
    }
    res.add(temp);
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int n = 4;
    List<List<String>> res = solution.solveNQueens(n);
    System.out.println(res);
  }


}
