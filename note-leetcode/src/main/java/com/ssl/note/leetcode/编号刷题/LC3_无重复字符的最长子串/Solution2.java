package com.ssl.note.leetcode.编号刷题.LC3_无重复字符的最长子串;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/1/18 9:19 AM
 * @description
 */
public class Solution2 {
  /**
   * 无重复字符的最长子串
   * 输入: s = "abcabcbb"
   * 输出: 3
   * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
   * 概念：1.子串 = 连续的 2.子序列 = 不连续的
   */
  public int lengthOfLongestSubstring(String s) {
    if (s.isEmpty()) {
      return 0;
    }
    int maxLen = 0; // 最大长度
    int left = 0;// 窗口内无重复字符的左边界

    int[] map = new int[256];
    Arrays.fill(map, -1);

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      // 重复字符在窗口内，left 跳跃
      if (map[c] >= left) {
        left = map[c] + 1;
      }

      // 记录字符位置
      map[c] = i;
      // 更新最大长度
      maxLen = Math.max(maxLen, i - left + 1);
    }
    return maxLen;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    String s = "abba";
    System.out.println(solution.lengthOfLongestSubstring(s));
  }
}
