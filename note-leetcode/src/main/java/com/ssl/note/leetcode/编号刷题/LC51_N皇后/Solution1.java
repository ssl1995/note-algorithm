package com.ssl.note.leetcode.编号刷题.LC51_N皇后;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

  List<List<String>> res = new ArrayList<>();

  /**
   * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
   * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
   * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
   * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
   * 输入：n = 4
   * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
   * 解释：如上图所示，4 皇后问题存在两个不同的解法。
   */
  public List<List<String>> solveNQueens(int n) {
    // 初始化cs二维数组，根据题目条件初始化放入.
    char[][] cs = new char[n][n];
    for (char[] c : cs) {
      Arrays.fill(c, '.');
    }

    backTrack(cs, 0, n);

    return res;
  }


  public void backTrack(char[][] cs, int i, int n) {
    // 终止条件:row越过n-1,说明这是一种正确的放法
    if (i == n) {
      res.add(char2List(cs));
      return;
    }
    for (int j = 0; j < n; ++j) {
      // 到达一行，遍历该行的每一列是否与之前的皇后是否产生攻击
      if (isValid(cs, i, j, n)) {
        cs[i][j] = 'Q';

        backTrack(cs, i + 1, n);

        cs[i][j] = '.';
      }
    }
  }

  public boolean isValid(char[][] cs, int row, int col, int n) {
    // 行不用检查，因为是逐行放入cs中
    // 检查列
    for (int i = 0; i < n; ++i) {
      if (cs[i][col] == 'Q') {
        return false;
      }
    }
    // 检查45°斜线
    for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
      if (cs[i][j] == 'Q') {
        return false;
      }
    }
    // 检查135°反斜线
    for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++) {
      if (cs[i][j] == 'Q') {
        return false;
      }
    }
    return true;
  }

  public List<String> char2List(char[][] cs) {
    List<String> list = new ArrayList<>();
    for (char[] c : cs) {
      // char[]直接转成String，加进list中
      list.add(String.copyValueOf(c));
    }
    return list;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int n = 4;
    List<List<String>> res = solution.solveNQueens(n);
    System.out.println(res);
  }


}
