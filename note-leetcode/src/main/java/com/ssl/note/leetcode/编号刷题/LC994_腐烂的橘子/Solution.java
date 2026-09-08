package com.ssl.note.leetcode.编号刷题.LC994_腐烂的橘子;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

  /**
   * 腐烂的橘子
   * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
   * 值 0 代表空单元格；
   * 值 1 代表新鲜橘子；
   * 值 2 代表腐烂的橘子。
   * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
   * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
   * 输入：grid = [[2,1,1],[1,1,0],[0,1,1]]
   * 输出：4
   */
  private int fresh;

  public int orangesRotting(int[][] grid) {
    if (grid == null) {
      return 0;
    }
    int m = grid.length;
    int n = grid[0].length;
    // 1、多路BFS，腐烂橘子入队列，同时计算新鲜橘子数量
    fresh = 0;
    Deque<int[]> queue = new ArrayDeque<>();
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          fresh++;
        } else if (grid[i][j] == 2) {
          queue.offer(new int[]{i, j});
        }
      }
    }
    // 2、如果没有新鲜橘子
    if (fresh == 0) {
      return 0;
    }
    // 初始化的腐烂橘子不算时间
    int times = -1;
    // 3、层次遍历队列
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        int[] poll = queue.poll();
        int i = poll[0];
        int j = poll[1];
        f(grid, queue, i - 1, j);
        f(grid, queue, i + 1, j);
        f(grid, queue, i, j - 1);
        f(grid, queue, i, j + 1);
      }
      // 每完整处理完一层，时间 +1
      times++;
    }

    return fresh == 0 ? times : -1;
  }

  private void f(int[][] grid, Deque<int[]> queue, int i, int j) {
    if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
      return;
    }
    if (grid[i][j] == 1) {
      queue.offer(new int[]{i, j});
      grid[i][j] = 2;
      fresh--;
    }
  }

  public static void main(String[] args) {
    Solution practice = new Solution();
    int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
    System.out.println(practice.orangesRotting(grid));
  }
}
