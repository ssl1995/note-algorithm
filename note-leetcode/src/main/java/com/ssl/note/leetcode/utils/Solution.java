package com.ssl.note.leetcode.utils;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
  public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length < k || k < 1) {
      return new int[]{};
    }

    Deque<Integer> queue = new LinkedList<>();
    int[] res = new int[nums.length - k + 1];
    int index = 0;

    for (int i = 0; i < nums.length; i++) {
      int curNum = nums[i];

      while (!queue.isEmpty() && curNum >= nums[queue.peekLast()]) {
        queue.pollLast();
      }

      queue.offerLast(i);

      int leftBound = i - k;
      if (queue.peekFirst() <= leftBound) {
        queue.pollFirst();
      }

      if (i >= k - 1) {
        res[index++] = nums[queue.peekFirst()];
      }
    }

    return res;

  }

  public static void main(String[] args) {
    Solution solution = new Solution();

  }

}
