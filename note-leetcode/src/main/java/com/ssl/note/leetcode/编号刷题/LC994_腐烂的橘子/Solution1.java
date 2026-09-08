package com.ssl.note.leetcode.编号刷题.LC994_腐烂的橘子;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1 {

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
    int m = grid.length;
    int n = grid[0].length;

    Deque<int[]> queue = new ArrayDeque<>();
    fresh = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 2) {
          queue.offer(new int[]{i, j});
        } else if (grid[i][j] == 1) {
          fresh++;
        }
      }
    }

    if (fresh == 0) {
      return 0;
    }

    int res = 0;
    // 每轮 BFS 代表一分钟的扩散
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
      // 完成一轮扩散，时间 +1
      res++;
      // 一旦所有新鲜橘子都腐烂了，立即返回当前时间
      if (fresh == 0) {
        return res;
      }
    }

    // BFS 结束但仍有新鲜橘子，说明有橘子无法被腐烂
    return -1;
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
    Solution1 practice = new Solution1();
    int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
    System.out.println(practice.orangesRotting(grid));
  }
}
