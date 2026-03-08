package com.ssl.note.leetcode.编号刷题.LC78_子集;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/28 11:15 PM
 * @description
 */
public class Solution {

  /**
   * 子集
   * 数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）
   * 输入：nums = [1,2,3]
   * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
   */
  public List<List<Integer>> subsets(int[] nums) {
    List<Integer> path = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    backtrack(nums, 0, path, res);

    return res;
  }

  private void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> res) {
    // 子集:一进来就收集
    res.add(new ArrayList<>(path));

    for (int i = start; i < nums.length; i++) {
      path.add(nums[i]);

      backtrack(nums, i + 1, path, res);

      path.remove(path.size() - 1);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 2, 3};
    // [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    List<List<Integer>> res = solution.subsets(nums);
    System.out.println(res);
  }
}
