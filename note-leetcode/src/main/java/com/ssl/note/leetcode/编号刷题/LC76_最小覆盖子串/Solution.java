package com.ssl.note.leetcode.编号刷题.LC76_最小覆盖子串;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

/**
 * @author SongShengLin
 * @date 2022/1/28 8:33 AM
 * @description
 */
public class Solution {
  /**
   * 最小覆盖子串
   * 返回 s 中涵盖 t 所有字符的最小子串
   * 输入：s = "ADOBECODEBANC", t = "ABC"
   * 输出："BANC"
   */
  public String minWindow(String s, String t) {
    if (s.length() < t.length()) {
      return "";
    }

    int m = s.length();
    int n = t.length();

    // 记录窗口内匹配的字符种类
    Map<Character, Integer> window = new HashMap<>();
    // 统计t中字符的种类
    Map<Character, Integer> need = new HashMap<>();

    int needCount = 0;
    for (int i = 0; i <= n - 1; i++) {
      char curChar = t.charAt(i);
      need.put(curChar, need.getOrDefault(curChar, 0) + 1);
      needCount++;
    }

    int start = 0;
    int minLen = Integer.MAX_VALUE;

    int valid = 0;
    int left = 0;
    for (int i = 0; i <= m - 1; i++) {
      char c = s.charAt(i);

      // 窗口内字符匹配上了
      if (need.containsKey(c)) {
        window.put(c, window.getOrDefault(c, 0) + 1);
        if (window.get(c).equals(need.get(c))) {
          valid++;
        }
      }

      // 缩小窗口
      while (needCount == valid) {
        char d = s.charAt(left);

        // 记录长度
        if (i - left + 1 < minLen) {
          start = left;
          minLen = i - left + 1;
        }

        if (need.containsKey(d)) {
          if (window.get(d).equals(need.get(d))) {
            // 窗口内字符匹配数减1,所以是while
            valid--;
          }
          window.put(d, window.get(d) - 1);
        }

        left++;
      }
    }

    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(solution.minWindow(s, t));
  }
}
