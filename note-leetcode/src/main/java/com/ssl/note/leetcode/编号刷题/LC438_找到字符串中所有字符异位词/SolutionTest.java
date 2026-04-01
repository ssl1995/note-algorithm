package com.ssl.note.leetcode.编号刷题.LC438_找到字符串中所有字符异位词;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/2/26 6:25 PM
 * @description
 */
public class SolutionTest {
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
    List<Integer> res = new ArrayList<>();
    int needCount = 0;
    int[] pMap = new int[26];
    for (char c : p.toCharArray()) {
      if (pMap[c - 'a'] == 0) {
        needCount++;
      }
      pMap[c - 'a']++;
    }

    int[] sMap = new int[26];
    int left = 0;
    int valid = 0;
    for (int right = 0; right < s.length(); right++) {
      char c = s.charAt(right);
      int cIndex = c - 'a';
      sMap[cIndex]++;

      if (sMap[cIndex] == pMap[cIndex]) {
        valid++;
      }

      while (right - left + 1 > p.length()) {
        int leftIndex = s.charAt(left) - 'a';

        if (sMap[leftIndex] == pMap[leftIndex]) {
          valid--;
        }
        sMap[leftIndex]--;
        left++;
      }

      if (needCount == valid) {
        res.add(left);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    SolutionTest solution = new SolutionTest();
    String s = "bpaa";
    String t = "aa";
    System.out.println(solution.findAnagrams(s, t));
  }
}
