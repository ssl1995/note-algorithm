package com.ssl.note.leetcode.编号刷题.LC42_接雨水;

/**
 * @author SongShengLin
 * @date 2022/1/23 10:36 PM
 * @description
 */
public class Solution2 {
  /**
   * 接雨水：会超时
   * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
   * 输出：6
   * 解法二：动态规划存储每列的左右两边最大值
   * 规避 每次遍历都找左右两边最高列的最小 + 本次列计算雨水的面积，逐步累加
   */
  public int trap(int[] height) {

    int water = 0;
    int n = height.length;
    // 计算i左边最高列
    int[] left = new int[n];
    for (int i = 1; i <= n - 1; i++) {
      left[i] = Math.max(left[i - 1], height[i - 1]);
    }
    // 计算i右边最高列
    int[] right = new int[n];
    for (int i = n - 2; i >= 0; i--) {
      right[i] = Math.max(right[i + 1], height[i + 1]);
    }
    // 计算雨水：列的左右两边最高列中的最小 - 当前列高度
    for (int i = 0; i <= n - 1; i++) {
      int min = Math.min(left[i], right[i]);
      // 当前列高度 < 左右两边最高列中的最小，才能接到雨水
      if (height[i] < min) {
        water += min - height[i];
      }
    }

    return water;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int[] nums = {4, 2, 0, 3, 2, 5};
    System.out.println(solution.trap(nums));
  }
}
