package com.ssl.note.leetcode.编号刷题.LC239_滑动窗口最大值;

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

    // 双端队列队：从大到小存数组中的下标
    Deque<Integer> queue = new LinkedList<>();
    // 原始数组3个元素，k=1，返回3个元素 = nums.length - k +1
    int[] res = new int[nums.length - k + 1];
    int index = 0;

    for (int i = 0; i < nums.length; i++) {
      int currentNum = nums[i];
      // 队尾维护：移除比currentNum小的元素
      while (!queue.isEmpty() && nums[queue.peekLast()] <= currentNum) {
        queue.pollLast();
      }
      queue.offerLast(i);

      // 队首维护：移除超出窗口范围的元素
      int leftBound = i - k; // 优化点3：预计算窗口左边界
      if (queue.peekFirst() <= leftBound) {
        queue.pollFirst();
      }

      // 记录结果
      if (i >= k - 1) {
        res[index++] = nums[queue.peekFirst()];
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
