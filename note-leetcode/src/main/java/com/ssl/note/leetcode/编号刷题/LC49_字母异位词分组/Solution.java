package com.ssl.note.leetcode.编号刷题.LC49_字母异位词分组;

import java.util.*;

public class Solution {

  /**
   * 字母异位词分组
   * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
   * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
   */
  public List<List<String>> groupAnagrams(String[] strs) {
    if (strs == null || strs.length == 0) {
      return new ArrayList<>();
    }
    Map<String, List<String>> map = new HashMap<>();

    for (String s : strs) {
      char[] charArray = s.toCharArray();

      Arrays.sort(charArray);
      String sorted = new String(charArray);

//      if (!map.containsKey(sorted)) {
//        map.put(sorted, new ArrayList<>());
//        continue;
//      }
      map.putIfAbsent(sorted,new LinkedList<>());
      map.get(sorted).add(s);
    }

    return new ArrayList<>(map.values());
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
    System.out.println(solution.groupAnagrams(strs));
  }
}
