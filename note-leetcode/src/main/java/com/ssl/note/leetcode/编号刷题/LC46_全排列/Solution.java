package com.ssl.note.leetcode.编号刷题.LC46_全排列;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/24 8:43 AM
 * @description
 */
public class Solution {
  /**
   * 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列 。
   * 你可以 按任意顺序 返回答案。
   * 输入：nums = [1,2,3]
   * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   */
  public List<List<Integer>> permute(int[] nums) {
    List<Integer> path = new ArrayList<>();
    boolean[] used = new boolean[nums.length];
    List<List<Integer>> res = new ArrayList<>();
    backtrack(nums, path, used, res);

    return res;
  }

  // 回溯：path和used数组方式
  private void backtrack(int[] nums,
                         List<Integer> path, boolean[] used,
                         List<List<Integer>> res) {
    if (path.size() == nums.length) {
      res.add(new ArrayList<>(path));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }
      path.add(nums[i]);
      used[i] = true;

      backtrack(nums, path, used, res);

      path.remove(path.size() - 1);
      used[i] = false;
    }
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 2, 3};
    List<List<Integer>> permute = solution.permute(nums);
    System.out.println(permute);
  }
}
