package com.ssl.note.leetcode.编号刷题.LC438_找到字符串中所有字符异位词;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/2/26 6:25 PM
 * @description
 */
public class Solution {
  /**
   * 找到字符串中所有字符异位词
   * 输入: s = "cbaebabacd", p = "abc",s和p仅包含小写字母
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

    for (int i = 0; i <= n - 1; i++) {
      sMap[s.charAt(i) - 'a']++;
      pMap[p.charAt(i) - 'a']++;
    }

    List<Integer> res = new ArrayList<>();

    if (Arrays.equals(sMap, pMap)) {
      res.add(0);
    }

    for (int i = n; i <= m - 1; i++) {
      sMap[s.charAt(i - n) - 'a']--;
      sMap[s.charAt(i) - 'a']++;

      if (Arrays.equals(sMap, pMap)) {
        // 记录起始的位置
        // [cbae]与[abc],从e到第一个c的索引
        res.add(i - n + 1);
      }
    }

    return res;
  }


  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "cbaebabacd";
    String t = "abc";
    System.out.println(solution.findAnagrams(s, t));
  }
}
