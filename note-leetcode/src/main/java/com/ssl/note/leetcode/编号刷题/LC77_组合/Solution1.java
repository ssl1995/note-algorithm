package com.ssl.note.leetcode.编号刷题.LC77_组合;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {

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

    backtrack(n, k, 1, path, res);

    return res;
  }

  /**
   * 剪枝：
   * 当前已选了 path.size() 个
   * 还需要选 k - path.size() 个
   * 从 i 到 n 还有 n - i + 1 个数可选
   * 如果 n - i + 1 < k - path.size()：剩余的不够选了，剪掉！
   * 等价于：i > n - (k - path.size()) + 1 时就可以停了
   * 简化：for 循环的上界从 n 变成 n - (k - path.size()) + 1
   */
  private void backtrack(int n, int k, int start, List<Integer> path, List<List<Integer>> res) {
    if (path.size() == k) {
      res.add(new ArrayList<>(path));
      return;
    }
    // 剪枝
    int need = k - path.size();
    for (int i = start; i <= n - need + 1; i++) {
      path.add(i);

      backtrack(n, k, i + 1, path, res);

      path.remove(path.size() - 1);
    }
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int n = 4;
    int k = 2;
    List<List<Integer>> combine = solution.combine(n, k);
    System.out.println(combine);
  }
}
