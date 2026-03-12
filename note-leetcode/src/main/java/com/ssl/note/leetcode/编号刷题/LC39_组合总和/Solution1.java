package com.ssl.note.leetcode.编号刷题.LC39_组合总和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/23 10:15 PM
 * @description
 */
public class Solution1 {
  /**
   * 组合总和：candidates无重复元素，但同一个数字可以重复使用
   * 输入: candidates = [2,3,5], target = 8
   * 输出: [[2,2,2,2],[2,3,3],[3,5]]
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    if (candidates == null || candidates.length == 0) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    // 剪枝优化：先排序
    Arrays.sort(candidates);

    backtrack(candidates, 0, target, temp, res);
    return res;
  }

  // 回溯1：通用模板
  private void backtrack(int[] candidates, int index, int target, List<Integer> temp, List<List<Integer>> res) {
    if (target < 0) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(temp));
      return;
    }
    for (int i = index; i < candidates.length; i++) {

      // 剪枝优化：排序完后，当前值已经大于剩余target，后续的值都更大，直接break
      if (candidates[i] > target) {
        break;
      }

      temp.add(candidates[i]);
      backtrack(candidates, i, target - candidates[i], temp, res);
      temp.remove(temp.size() - 1);
    }
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = { 2, 3, 5 };
    int target = 8;
    System.out.println(solution.combinationSum(nums, target));
  }

}
