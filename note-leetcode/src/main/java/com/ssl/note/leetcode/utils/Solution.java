package com.ssl.note.leetcode.utils;

import java.util.Arrays;

public class Solution {
  public int lengthOfLongestSubstring(String s) {
    if (s.isEmpty()) {
      return 0;
    }

    int[] map = new int[256];
    Arrays.fill(map, -1);

    int left = -1;
    int maxLen = 0;

    char[] cs = s.toCharArray();

    for (int i = 0; i < cs.length; i++) {
      char c = cs[i];

      left = Math.max(left, map[c]);

      maxLen = Math.max(maxLen, i - left);

      map[c] = i;
    }

    return maxLen;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();

  }

}
