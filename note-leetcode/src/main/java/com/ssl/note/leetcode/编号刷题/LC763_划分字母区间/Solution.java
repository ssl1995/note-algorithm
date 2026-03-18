package com.ssl.note.leetcode.编号刷题.LC763_划分字母区间;

import java.util.LinkedList;
import java.util.List;

public class Solution {

  /**
   * 题目核心要求
   * - 输入 ：一个由小写字母组成的字符串 S
   * - 输出 ：一个列表，表示将字符串划分为尽可能多的片段的长度
   * - 限制条件 ：同一字母最多出现在一个片段中
   * 题目理解关键点
   * 1. 划分片段 ：将字符串分成若干个连续的子串
   * 2. 尽可能多的片段 ：在满足条件的情况下，片段数量越多越好
   * 3. 同一字母最多出现在一个片段中 ：任何一个字母不能同时出现在两个或更多的片段中
   * 示例解释
   * 以输入 "ababcbaca defegde hijhklij" 为例：
   * - 正确划分 ： "ababcbaca" , "defegde" , "hijhklij"
   * - 长度分别为 9, 7, 8
   * - 每个字母只出现在一个片段中
   * - 例如：字母 'a' 只在第一个片段中出现，字母 'd' 只在第二个片段中出现
   * - 错误划分 ： "ababcbacadefegde" , "hijhklij"
   * - 虽然每个字母也只出现在一个片段中
   * - 但片段数量只有 2 个，不是尽可能多的
   * 1 <= s.length <= 500
   * s 仅由小写英文字母组成
   */
  public List<Integer> partitionLabels(String s) {
    if (s == null || s.length() == 0) {
      return new LinkedList<>();
    }
    int n = s.length();
    List<Integer> res = new LinkedList<>();
    // 1、更新每个字母的最后出现位置
    int[] lastIndex = new int[26];
    for (int i = 0; i < n; i++) {
      lastIndex[s.charAt(i) - 'a'] = i;
    }
    // 当前片段的起始位置
    int start = 0;
    // 当前片段的结束位置
    int end = 0;

    for (int i = 0; i < n; i++) {
      // 更新当前片段的结束位置为该字符的最后位置
      end = Math.max(end, lastIndex[s.charAt(i) - 'a']);

      if (i == end) {
        res.add(end - start + 1);
        start = end + 1;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "ababcbacadefegdehijhklij";
    System.out.println(solution.partitionLabels(s));
  }
}
