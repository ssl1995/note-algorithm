package com.ssl.note.leetcode.编号刷题.LC79_单词搜索;

/**
 * @author SongShengLin
 * @date 2022/1/29 11:05 AM
 * @description
 */
public class Solution {

  /**
   * 单词搜索
   * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
   * 输出：true
   */
  public boolean exist(char[][] board, String word) {
    int m = board.length;
    int n = board[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (backtrack(board, i, j, word, 0)) {
          return true;
        }
      }
    }
    return false;
  }

  /*
   * 回溯= dfs+撤销
   */
  private boolean backtrack(char[][] board, int i, int j, String word, int index) {
    // 结束条件1：失败
    if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1
        || board[i][j] != word.charAt(index)) {
      return false;
    }
    // 结束条件2：word全部匹配成功
    if (index == word.length() - 1) {
      return true;
    }
    // 做选择：原地标记
    // 防止重复：向四个方向递归，第一次是4个方向，后面都是3个方向，因为标记的来时路不能重复
    board[i][j] = '*';
    // 四个方向找
    boolean found = backtrack(board, i - 1, j, word, index + 1)
        || backtrack(board, i + 1, j, word, index + 1)
        || backtrack(board, i, j - 1, word, index + 1)
        || backtrack(board, i, j + 1, word, index + 1);
    // 回溯特征撤销选择：标记还原，递归结束，返回上一层
    board[i][j] = word.charAt(index);
    return found;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
    String word = "ABCCED";
    System.out.println(solution.exist(board, word));
  }


}
