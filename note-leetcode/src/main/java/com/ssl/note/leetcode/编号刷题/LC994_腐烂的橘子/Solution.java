package com.ssl.note.leetcode.编号刷题.LC994_腐烂的橘子;

import java.util.ArrayDeque;
import java.util.Queue;

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
  public int orangesRotting(int[][] grid) {
    if (grid == null) {
      return 0;
    }
    // 1、腐烂橘子入队，统计新鲜橘子
    Queue<int[]> queue = new ArrayDeque<>();
    int m = grid.length;
    int n = grid[0].length;

    int fresh = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] == 1) {
          fresh++;
        } else if (grid[i][j] == 2) {
          queue.offer(new int[]{i, j});
        }
      }
    }
    // 2、特殊清况，没有新鲜橘子，返回0
    if (fresh == 0) {
      return 0;
    }

    // 四个方向遍历
    int[][] directions = new int[4][2];
    directions[0] = new int[]{1, 0};
    directions[1] = new int[]{-1, 0};
    directions[2] = new int[]{0, 1};
    directions[3] = new int[]{0, -1};

    int mins = 0;
    // 3、终止条件优化：没有新鲜橘子可以提前跳出循环
    while (!queue.isEmpty() && fresh > 0) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        int[] poll = queue.poll();

        if (poll == null) {
          continue;
        }

        for (int[] nums : directions) {
          int nx = poll[0] + nums[0];
          int ny = poll[1] + nums[1];
          // 越界或不是新鲜橘子，跳过啊实打实的
          if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
            continue;
          }
          // 是啊大师的阿萨德：实打实的安师大大师的
          if (grid[nx][ny] != 1) {
            continue;
          }

          grid[nx][ny] = 2;
          queue.offer(new int[]{nx, ny});
          fresh--;
        }
      }// for

      // 统计分钟数，在for循环外
      mins++;
    }
    // 4、返回检查是否有新鲜橘子
    return fresh == 0 ? mins : -1;
  }
}
