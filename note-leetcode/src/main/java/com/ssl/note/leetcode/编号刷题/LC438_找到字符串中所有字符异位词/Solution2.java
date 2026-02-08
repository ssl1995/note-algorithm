package com.ssl.note.leetcode.编号刷题.LC438_找到字符串中所有字符异位词;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/2/26 6:25 PM
 * @description
 */
public class Solution2 {
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

    int[] sMap = new int[26];
    int[] pMap = new int[26];

    // 统计p中出现的字符种类
    int needCount = 0;

    for (int i = 0; i <= n - 1; i++) {
      int curIndex = p.charAt(i) - 'a';
      if (pMap[curIndex] == 0) {
        needCount++;
      }
      pMap[curIndex]++;
    }

    List<Integer> res = new ArrayList<>();
    int valid = 0;// 窗口内匹配上的字符数量
    int left = 0;
    for (int i = 0; i <= m - 1; i++) {
      int curIndex = s.charAt(i) - 'a';
      sMap[curIndex]++;
      // 记录窗口内匹配的字符数量
      if (sMap[curIndex] == pMap[curIndex]) {
        valid++;
      }
      // 核心：窗口超长，左边的字符离开
      while (i - left + 1 > n) {
        int leftIndex = s.charAt(left) - 'a';

        // 前面加过，移动后要移除
        if (sMap[leftIndex] == pMap[leftIndex]) {
          valid--;
        }

        sMap[leftIndex]--;
        left++;
      }

      // 需要的字符数=匹配上的字符数，找到了异位词
      if (needCount == valid) {
        res.add(left);
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    String s = "cbaebabacd";
    String t = "abc";
    System.out.println(solution.findAnagrams(s, t));
  }
}
