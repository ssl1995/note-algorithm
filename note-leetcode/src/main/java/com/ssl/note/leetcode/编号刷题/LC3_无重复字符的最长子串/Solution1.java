package com.ssl.note.leetcode.编号刷题.LC3_无重复字符的最长子串;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/1/18 9:19 AM
 * @description
 */
public class Solution1 {
  /**
   * 无重复字符的最长子串
   * 输入: s = "abcabcbb"
   * 输出: 3
   * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
   * 概念：1.子串 = 连续的 2.子序列 = 不连续的
   */
  public int lengthOfLongestSubstring(String s) {
    if (s.length() == 0) {
      return 0;
    }

    int[] map = new int[256]; // 存储字符最后一次出现的索引
    Arrays.fill(map, -1); // 初始化为-1（未出现过）

    int pre = -1; // 当前窗口的左边界前一个位置
    int maxLen = 0; // 最大长度

    char[] cs = s.toCharArray();

    for (int i = 0; i < cs.length; i++) {
      char c = cs[i];

      // 更新左边界：取当前字符上次出现的位置和当前left的较大值
      pre = Math.max(pre, map[c]);

      // 计算当前窗口长度（right - left）
      maxLen = Math.max(maxLen, i - pre);

      // 记录当前字符的最新位置
      map[c] = i;
    }
    return maxLen;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "abcabcbb";
    System.out.println(solution.lengthOfLongestSubstring(s));
  }
}
