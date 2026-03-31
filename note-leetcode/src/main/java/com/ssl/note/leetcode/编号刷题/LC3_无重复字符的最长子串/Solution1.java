package com.ssl.note.leetcode.编号刷题.LC3_无重复字符的最长子串;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
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
    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      // map：记录本
      // map+left的比较：保证上一次出现的位置在窗口内
      if (map.containsKey(c) && map.get(c) >= left) {
        left = map.get(c) + 1;
      }

      map.put(c, i);
      maxLen = Math.max(maxLen, i - left + 1);
    }

    return maxLen;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "abba";// 答案是ab，长度=2，这样容易理解
    System.out.println(solution.lengthOfLongestSubstring(s));
  }
}
