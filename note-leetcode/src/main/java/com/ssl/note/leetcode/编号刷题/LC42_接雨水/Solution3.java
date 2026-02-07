package com.ssl.note.leetcode.编号刷题.LC42_接雨水;

import java.time.Period;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
   * 解法三：单调递减栈
   */
  public int trap(int[] height) {
    int water = 0;
    Deque<Integer> stack = new ArrayDeque<>();

    for (int i = 0; i < height.length; i++) {
      // 栈不为空 且 当前高度大于栈顶高度，可以形成凹槽 = 可以接雨水
      while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
        // 凹槽底部
        int bottom = stack.pop();
        // 递减栈出栈后，栈顶元素就是凹槽的左边界
        // 如果没有左边界，则无法接雨水
        if (stack.isEmpty()) {
          break;
        }
        int left = stack.peek();
        // -1的原因：计算的是凹槽接水的宽度
        int width = i - left - 1;

        // Math.min(height[left], height[i]) = 水面高度 = 木桶原理
        int waterH = Math.min(height[left], height[i]);
        // 底部可能不是平地（高度可能不是0），所以要减去底部的高度。
        int h = waterH - height[bottom];

        // 加上当前列能接的水量
        water += width * h;
      }
      stack.push(i);
    }

    return water;
  }

  public static void main(String[] args) {
    Solution3 solution = new Solution3();
    int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    System.out.println(solution.trap(nums));
  }
}
