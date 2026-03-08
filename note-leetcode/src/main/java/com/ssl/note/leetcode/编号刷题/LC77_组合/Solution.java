package com.ssl.note.leetcode.编号刷题.LC77_组合;

import java.util.ArrayList;
import java.util.List;

public class Solution {

  /**
   * LC77_组合
   * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
   * 你可以按 任何顺序 返回答案。
   * 输入：n = 4, k = 2
   * 输出：
   * [
   * [2,4],
   * [3,4],
   * [2,3],
   * [1,2],
   * [1,3],
   * [1,4],
   * ]
   */
  public List<List<Integer>> combine(int n, int k) {
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    // 从1开始
    backtrack(n, k, 1, path, res);

    return res;
  }

  private void backtrack(int n, int k, int start, List<Integer> path, List<List<Integer>> res) {
    if (path.size() == k) {
      res.add(new ArrayList<>(path));
      return;
    }
    // 组合从start开始
    for (int i = start; i <= n; i++) {
      path.add(i);

      backtrack(n, k, i + 1, path, res);

      path.remove(path.size() - 1);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 4;
    int k = 2;
    List<List<Integer>> combine = solution.combine(n, k);
    System.out.println(combine);
  }
}
