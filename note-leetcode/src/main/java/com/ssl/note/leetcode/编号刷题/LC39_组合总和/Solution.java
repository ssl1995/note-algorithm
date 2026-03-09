package com.ssl.note.leetcode.编号刷题.LC39_组合总和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/23 10:15 PM
 * @description
 */
public class Solution {
  /**
   * 组合总和：candidates无重复元素，但同一个数字可以重复使用
   * 输入: candidates = [2,3,5], target = 8
   * 输出: [[2,2,2,2],[2,3,3],[3,5]]
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    if (candidates == null || target <= 1) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    // 剪枝：先排序
    Arrays.sort(candidates);
    backtrack(candidates, 0, target, temp, res);
    return res;
  }

  // 回溯1：通用模板
  private void backtrack(int[] candidates, int index, int target, List<Integer> temp, List<List<Integer>> res) {
    if (target < 0 || index > candidates.length - 1) {
      return;
    }
    if (target == 0) {
      res.add(new ArrayList<>(temp));
      return;
    }
    for (int i = index; i < candidates.length; i++) {
      temp.add(candidates[i]);
      // 剪枝：排序完后，下一层的递归值<0，后续的值都无需再递归啦
      if (target - candidates[i] < 0) {
        break;
      }
      backtrack(candidates, i, target - candidates[i], temp, res);
      temp.remove(temp.size() - 1);
    }
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {2, 3, 5};
    int target = 8;
    System.out.println(solution.combinationSum(nums, target));
  }

}
