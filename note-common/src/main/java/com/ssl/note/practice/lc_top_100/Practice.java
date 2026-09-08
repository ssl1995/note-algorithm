package com.ssl.note.practice.lc_top_100;

import java.util.ArrayDeque;
import java.util.Deque;

public class Practice {
  int fresh;

  public int orangesRotting(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;

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

    if (fresh == 0) {
      return -1;
    }

    int times = 1;

    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        int[] dir = queue.poll();
        int x = dir[0];
        int y = dir[1];
        dfs(grid, m, n, x + 1, y, queue);
        dfs(grid, m, n, x - 1, y, queue);
        dfs(grid, m, n, x, y + 1, queue);
        dfs(grid, m, n, x, y - 1, queue);
      }
      times++;
    }
    return fresh == 0 ? -1 : times;
  }

  private void dfs(int[][] grid, int m, int n, int i, int j, Deque<int[]> queue) {
    if (i < 0 || i > m - 1 || j < 0 || j > n - 1) {
      return;
    }
    if (grid[i][j] == 1) {
      grid[i][j] = 2;
      queue.offer(new int[]{i, j});
      fresh--;
    }
  }

  public static void main(String[] args) {
    Practice practice = new Practice();
    int[][] grod = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
    // aaabcbc
    System.out.println(practice.orangesRotting(grod));
  }

}
