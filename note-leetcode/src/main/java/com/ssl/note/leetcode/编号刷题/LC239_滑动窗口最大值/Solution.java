package com.ssl.note.leetcode.编号刷题.LC239_滑动窗口最大值;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2022/6/19 12:06
 * @description
 */
public class Solution {

  /**
   * 滑动窗口最大值
   */
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length < k || k < 1) {
      return new int[]{};
    }

    int[] res = new int[nums.length - k + 1];

    Deque<Integer> deque = new ArrayDeque<>();

    for (int i = 0; i < nums.length; i++) {
      // 队尾元素：维持单调递减
      while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
        deque.pollLast();
      }
      deque.offerLast(i);
      // 队首元素：保持队列最大值下标，移除超出窗口范围的元素
      if (deque.peekFirst() <= i - k) {
        deque.pollFirst();
      }
      // 记录结果
      if (i - k + 1 >= 0) {
        res[i - k + 1] = nums[deque.peekFirst()];
      }

    }
    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
    int k = 3;
    // [3, 3, 5, 5, 6, 7]
    System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, k)));
  }
}
