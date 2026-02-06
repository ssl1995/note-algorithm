package com.ssl.note.leetcode.编号刷题.LC283_移动零;

import java.util.Arrays;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/20 9:17 AM
 * @Describe:
 */
public class Solution {

  /**
   * 移动零
   * 输入: nums = [0,1,0,3,12]
   * 输出: [1,3,12,0,0]
   */
  public void moveZeroes(int[] nums) {
    int slow = 0;
    for (int fast = 0; fast < nums.length; fast++) {
      if (nums[fast] == 0) {
        continue;
      }
      // 非零时才交换
      swap(nums, slow++, fast);
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
    Solution solution = new Solution();
    int[] nums = {0, 1, 0, 3, 12};
    solution.moveZeroes(nums);
    System.out.println(Arrays.toString(nums));
  }


}
