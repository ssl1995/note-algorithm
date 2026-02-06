package com.ssl.note.leetcode.编号刷题.LC15_三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {

  /**
   * 三数之和
   * 是否存在3个数使得a+b+c=0
   * 输入：nums = [-1,0,1,2,-1,-4]
   * 输出：[[-1,-1,2],[-1,0,1]]
   */
  public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length < 3) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    // 1.排序
    Arrays.sort(nums);
    int n = nums.length;
    for (int i = 0; i < n - 2; i++) {
      // 2.剪枝：最小值大于0，不可能有解
      if (nums[i] > 0) {
        break;
      }
      // 3.去重：跳过重复的第一个数
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      // 4.双指针
      int left = i + 1;
      int right = n - 1;
      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right];
        if (sum < 0) {
          left++;
        } else if (sum > 0) {
          right--;
        } else {
          // 5.找到答案
          res.add(Arrays.asList(nums[i], nums[left], nums[right]));
          // 6.跳过重复值
          while (left < right && nums[left] == nums[left + 1]) {
            left++;
          }
          while (left < right && nums[right] == nums[right - 1]) {
            right--;
          }
          left++;
          right--;
        }
      }
    }
    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {-1, 0, 1, 2, -1, -4};
    System.out.println(solution.threeSum(nums));
  }

}
