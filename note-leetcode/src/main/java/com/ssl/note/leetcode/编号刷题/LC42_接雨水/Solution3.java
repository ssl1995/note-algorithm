package com.ssl.note.leetcode.编号刷题.LC42_接雨水;

/**
 * @author SongShengLin
 * @date 2022/1/23 10:36 PM
 * @description
 */
public class Solution3 {
  /**
   * 接雨水
   * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
   * 输出：6
   */
  public int trap(int[] height) {
    int water = 0;
    int max = 0;
    int left = 0, right = height.length - 1;

    while (left < right) {
      // 桶高：左右指针、历史桶高的最大值
      max = Math.max(max, Math.min(height[left], height[right]));

      // 雨水量 += 最低高度 - 左右最矮
      water += height[left] <= height[right] ? (max - height[left++]) : (max - height[right--]);
    }
    return water;
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.println(solution.trap(nums));
  }
}
