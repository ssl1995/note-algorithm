package com.ssl.note.leetcode.utils;

import java.util.*;

public class Solution {
  public int maxSubArray(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }

    int pre = 0;
    int max = Integer.MIN_VALUE;

    int i = 0;
    while (i < nums.length) {
      if (pre < 0) {
        pre = 0;
      }
      pre += nums[i];
      max = Math.max(max, pre);
      i++;
    }
    return max;
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
  }
}
