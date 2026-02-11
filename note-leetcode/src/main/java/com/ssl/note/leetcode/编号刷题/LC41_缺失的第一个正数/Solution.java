package com.ssl.note.leetcode.编号刷题.LC41_缺失的第一个正数;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Solution {

  /**
   * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。   *
   * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
   * 输入：nums = [3,4,-1,1]
   * 输出：2
   * 解释：1 在数组中，但 2 没有。
   */
  public int firstMissingPositive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int n = nums.length;
    // 第一次遍历，放正确位置
    for (int i = 0; i < n; i++) {
      while (
        // 结果一定在[1,n]范围内
        // 因为最好情况:{1,2,3,4}结果最小没出现正整数=5
          nums[i] >= 1 && nums[i] <= n
              &&
              // 遍历的数 正确位置：该数-1的索引对应的数
              nums[i] != nums[nums[i] - 1]
      ) {
        // nums[i]正确的位置索引:nums[i]-1
        int targetIndex = nums[i] - 1;
        int temp = nums[targetIndex];
        nums[targetIndex] = nums[i];
        nums[i] = temp;
      }
    }
    // 第二次遍历找 i +1 !=nums[i]
    for (int i = 0; i < n; i++) {
      if (i + 1 != nums[i]) {
        return i + 1;
      }
    }
    // 全部正确答案是n+1
    return n + 1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {3, 4, -1, 1};
    System.out.println(solution.firstMissingPositive(nums));
  }

}
