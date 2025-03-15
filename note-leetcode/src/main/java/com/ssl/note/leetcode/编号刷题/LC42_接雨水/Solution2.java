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
   */
  public int trap(int[] height) {

    int water = 0;
    // 上一个解法会超时，可以提前遍历获取i位置左右2边的高墙的位置
    int[] leftMax = new int[height.length];
    for (int i = 0; i < height.length; i++) {
      if (i == 0 || i == height.length - 1) {
        leftMax[i] = -1;
        continue;
      }
      leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
    }

    int[] rightMax = new int[height.length];
    for (int i = height.length - 1; i >= 0; i--) {
      if (i == 0 || i == height.length - 1) {
        rightMax[i] = -1;
        continue;
      }
      rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
    }

    for (int i = 1; i < height.length - 1; i++) {
      int min = Math.min(leftMax[i], rightMax[i]);
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
