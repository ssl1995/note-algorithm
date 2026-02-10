package com.ssl.note.leetcode.编号刷题.LC56_合并区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/26 11:08 PM
 * @description
 */
public class Solution {
  /**
   * 合并区间
   * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
   * 输出：[[1,6],[8,10],[15,18]]
   */
  public int[][] merge(int[][] intervals) {
    if (intervals == null || intervals.length == 0) {
      return new int[0][2];
    }
    // 按照左端点排序
    Arrays.sort(intervals, Comparator.comparingInt(num -> num[0]));

    List<int[]> res = new ArrayList<>();
    for (int[] curNum : intervals) {
      int left = curNum[0];
      int right = curNum[1];

      if (res.isEmpty() || res.get(res.size() - 1)[1] < left) {
        res.add(new int[]{left, right});
      } else {
        int[] lastNum = res.get(res.size() - 1);
        lastNum[1] = Math.max(lastNum[1], right);
      }
    }
    // new int[0][]:告诉JVM，需要什么类型
    return res.toArray(new int[res.size()][]);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[][] nums = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    System.out.println(Arrays.deepToString(solution.merge(nums)));
  }
}
