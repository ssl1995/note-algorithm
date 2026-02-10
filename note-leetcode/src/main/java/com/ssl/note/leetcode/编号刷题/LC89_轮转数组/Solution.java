package com.ssl.note.leetcode.编号刷题.LC89_轮转数组;

import java.util.Arrays;

public class Solution {
  /**
   * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
   * 输入: nums = [1,2,3,4,5,6,7], k = 3
   * 输出: [5,6,7,1,2,3,4]
   * 解释:
   * 向右轮转 1 步: [7,1,2,3,4,5,6]
   * 向右轮转 2 步: [6,7,1,2,3,4,5]
   * 向右轮转 3 步: [5,6,7,1,2,3,4]
   */
  public void rotate(int[] nums, int k) {
    if (nums == null || nums.length == 1 || k < 0) {
      return;
    }
    int n = nums.length;
    // k也可能超过数组长度
    k = k % n;

    int[] temp = new int[n];
    for (int i = 0; i < n; i++) {
      // 索引为i的数，在轮转k步后，应该在索引为(i+k)%n的位置
      temp[(i + k) % n] = nums[i];
    }
    System.arraycopy(temp, 0, nums, 0, n);
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 2, 3, 4, 5, 6, 7};
    int k = 3;
    solution.rotate(nums, k);
    System.out.println(Arrays.toString(nums));
  }
}
