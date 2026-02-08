package com.ssl.note.leetcode.编号刷题.LC76_最小覆盖子串;

/**
 * @author SongShengLin
 * @date 2022/1/28 8:33 AM
 * @description
 */
public class Solution1 {
  /**
   * 最小覆盖子串
   * 返回 s 中涵盖 t 所有字符的最小子串
   * 输入：s = "ADOBECODEBANC", t = "ABC"
   * 输出："BANC"
   */
  public String minWindow(String s, String t) {
    if (s == null || t == null || s.length() < t.length()) {
      return "";
    }
    // 初始化：记录需要的数量
    int[] map = new int[256];
    for (char c : t.toCharArray()) {
      map[c]++;
    }

    int left = 0;
    int matchCount = t.length();// 字符总数（不去重）
    int start = 0, minLen = Integer.MAX_VALUE;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      // 扩张：如果是需要的字符，match--
      if (map[c] > 0) {
        matchCount--;
      }
      map[c]--;

      // 当所有字符都匹配上时，尝试收缩
      while (matchCount == 0) {
        // 更新最小窗口
        if (i - left + 1 < minLen) {
          start = left;
          minLen = i - left + 1;
        }

        // 收缩：移出左边字符
        char d = s.charAt(left);
        map[d]++;
        if (map[d] > 0) {
          // 移出后 map[d] > 0，说明又缺这个字符了
          matchCount++;
        }
        left++;
      }
    }

    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
  }


  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "ADOBECODEBANC";
    String t = "ABC";
    System.out.println(solution.minWindow(s, t));
  }
}
