package com.ssl.note.leetcode.编号刷题.LC438_找到字符串中所有字符异位词;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/2/26 6:25 PM
 * @description
 */
public class Solution1 {
  /**
   * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引
   * s和p仅包含小写字母
   * 输入: s = "cbaebabacd", p = "abc"
   * 输出: [0,6]
   * 解释:
   * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
   * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
   */
  public List<Integer> findAnagrams(String s, String p) {
    if (s == null || (p.length() > s.length())) {
      return new ArrayList<>();
    }
    int m = s.length();
    int n = p.length();
    // s,p只包含小写字母,只用26个字母即可;否则，用256即可
    // sMap:记录当前窗口 [left, right] 内的字符出现次数。
    int[] sMap = new int[26];
    // pMap:记录p中每个单词出现的次数
    int[] pMap = new int[26];
    for (int i = 0; i < n; i++) {
      pMap[p.charAt(i) - 'a']++;
    }

    List<Integer> res = new ArrayList<>();

    // 维护一个窗口 [left, right]，确保窗口内各字符的出现次数不超过 p 中的次数。
    int left = 0;
    for (int i = 0; i < m; i++) {
      int index = s.charAt(i) - 'a';
      sMap[index]++;

      // 若当前字符的计数超过 pMap，则移动左指针 left，减少左边字符的计数
      // 直到窗口内字符计数均不超过 pMap。
      while (sMap[index] > pMap[index]) {
        int leftIndex = s.charAt(left) - 'a';
        sMap[leftIndex]--;
        left++;
      }

      // 匹配成功：说明长度相等
      if (i - left + 1 == n) {
        res.add(left);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    String s = "cbaebabacd";
    String t = "abc";
    System.out.println(solution.findAnagrams(s, t));
  }
}
