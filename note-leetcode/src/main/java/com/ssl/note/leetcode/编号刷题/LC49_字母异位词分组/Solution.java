package com.ssl.note.leetcode.编号刷题.LC49_字母异位词分组;

import java.util.*;

public class Solution {

  /**
   * 字母异位词分组
   * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
   * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
   * 时间和空间复杂度：O（n*k）
   */
  public List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null || strs.length == 0) {
      return new ArrayList<>();
    }
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      // 使用次数 + 特殊字符，组成一个唯一键
      String key = getCountKey(str);

      map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
    }

    return new ArrayList<>(map.values());
  }

  /*
   * 使用次数 + 特殊字符，组成一个唯一键
   */
  private String getCountKey(String str) {
    if (str == null) {
      return "";
    }
    int[] count = new int[26];
    char[] cs = str.toCharArray();
    for (char c : cs) {
      count[c - 'a']++;
    }

    StringBuilder sb = new StringBuilder();
    for (int num : count) {
      sb.append(num).append("#");
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    System.out.println(solution.groupAnagrams(strs));
  }
}
