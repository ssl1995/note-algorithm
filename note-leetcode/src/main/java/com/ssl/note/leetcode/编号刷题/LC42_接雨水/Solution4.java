package com.ssl.note.leetcode.编号刷题.LC42_接雨水;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author SongShengLin
 * @date 2022/1/23 10:36 PM
 * @description
 */
public class Solution4 {
  /**
   * 接雨水
   * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
   * 输出：6
   * 解法三：单调递减栈
   */
  public int trap(int[] height) {
    int water = 0;

    int leftMax = 0;
    int rightMax = 0;

    int left = 0;
    int right = height.length - 1;

    while (left < right) {
      leftMax = Math.max(leftMax, height[left]);
      rightMax = Math.max(rightMax, height[right]);

      if (leftMax < rightMax) {
        water += leftMax - height[left];
        left++;
      } else {
        water += rightMax - height[right];
        right--;
      }
    }

    return water;
  }

  public static void main(String[] args) {
    Solution4 solution = new Solution4();
    int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.println(solution.trap(nums));
  }
}
