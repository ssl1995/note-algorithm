package com.ssl.note.leetcode.编号刷题.LC42_接雨水;

/**
 * @author SongShengLin
 * @date 2022/1/23 10:36 PM
 * @description
 */
public class Solution1 {

  /**
   * 接雨水：会超时
   * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
   * 输出：6
   * 解法一暴力法：每次遍历都找左右两边最高列的最小 + 本次列计算雨水的面积，逐步累加
   */
  public int trap(int[] height) {

    int water = 0;
    for (int i = 1; i < height.length - 1; i++) {
      // 会超时，就是因为左右两边最高的列都是每次都遍历一遍，会超时
      int leftMax = 0;
      for (int j = i - 1; j >= 0; j--) {
        leftMax = Math.max(height[j], leftMax);
      }
      int rightMax = 0;
      for (int j = i + 1; j < height.length; j++) {
        rightMax = Math.max(height[j], rightMax);
      }

      int min = Math.min(leftMax, rightMax);
      if (height[i] < min) {
        water += min - height[i];
      }
    }

    return water;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {4, 2, 0, 3, 2, 5};
    System.out.println(solution.trap(nums));
  }
}
