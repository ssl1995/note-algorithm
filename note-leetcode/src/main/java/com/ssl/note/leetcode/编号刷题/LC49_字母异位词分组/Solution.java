package com.ssl.note.leetcode.编号刷题.LC49_字母异位词分组;

import java.util.*;

public class Solution {

  /**
   * 字母异位词分组
   * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
   * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
   *
   * 时间和空间复杂度：O（n*k）
   */
  public List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null || strs.length == 0) {
      return new ArrayList<>();
    }
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      char[] cs = str.toCharArray();

      int[] count = new int[26];
      for (char c : cs) {
        count[c - 'a']++;
      }

      StringBuilder key = new StringBuilder();
      for (int cn : count) {
        key.append(cn).append("#");
      }

      String keyStr = key.toString();

      map.computeIfAbsent(keyStr, k -> new ArrayList<>()).add(str);
    }

    return new ArrayList<>(map.values());
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    System.out.println(solution.groupAnagrams(strs));
  }
}
