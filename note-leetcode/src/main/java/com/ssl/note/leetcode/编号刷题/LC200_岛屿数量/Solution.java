package com.ssl.note.leetcode.编号刷题.LC200_岛屿数量;

/**
 * @author SongShengLin
 * @date 2022/6/18 17:08
 * @description
 */
public class Solution {

  /**
   * 岛屿数量
   * 输入：grid = [
   * ['1','1','1','1','0'],
   * ['1','1','0','1','0'],
   * ['1','1','0','0','0'],
   * ['0','0','0','0','0']
   * ]
   * 输出：1
   */
  public int numIslands(char[][] grid) {
    if (grid == null) {
      return 0;
    }
    int res = 0;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          dfs(grid, i, j);
          res++;
        }
      }
    }
    return res;
  }

  private void dfs(char[][] grid, int i, int j) {
    // 边界
    if (i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1) {
      return;
    }
    // 停止
    if (grid[i][j] != '1') {
      return;
    }
    // 感染标记
    grid[i][j] = '2';
    dfs(grid, i + 1, j);
    dfs(grid, i - 1, j);
    dfs(grid, i, j + 1);
    dfs(grid, i, j - 1);
  }

}
