package com.ssl.note.leetcode.编号刷题.LC46_全排列;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/24 8:43 AM
 * @description
 */
public class Solution1 {
  /**
   * 给定一个不含重复数字的数组 nums ，返回其所有可能的全排列 。
   * 你可以 按任意顺序 返回答案。
   * 输入：nums = [1,2,3]
   * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
   * 原地交换的形式
   */
  public List<List<Integer>> permute(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    process(nums, 0, res);
    return res;
  }

  // 回溯：原地交换方式,不是通用的方式，不利于总结与LC77和LC78的区别
  private void process(int[] nums, int i, List<List<Integer>> res) {
    if (i == nums.length) {
      List<Integer> temp = new ArrayList<>();
      for (int num : nums) {
        temp.add(num);
      }
      res.add(temp);
      return;
    }
    // j位置从=i位置开始
    for (int j = i; j < nums.length; j++) {
      // 交换i，j位置
      swap(nums, i, j);

      process(nums, i + 1, res);

      // 回溯：i,j位置归位
      swap(nums, i, j);
    }
  }

  private void swap(int[] nums, int i, int j) {
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }


  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {1, 2, 3};
    List<List<Integer>> permute = solution.permute(nums);
    System.out.println(permute);
  }
}
