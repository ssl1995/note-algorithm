package com.ssl.note.leetcode.编号刷题.LC17_电话号码组合;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
  /**
   * 电话号码组合
   * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合
   * 输入：digits = "23"
   * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
   */
  public List<String> letterCombinations(String digits) {
    if (digits == null || digits.isEmpty()) {
      return new ArrayList<>();
    }
    Map<Character, String> map = new HashMap<>();
    map.put('2', "abc");
    map.put('3', "def");
    map.put('4', "ghi");
    map.put('5', "jkl");
    map.put('6', "mno");
    map.put('7', "pqrs");
    map.put('8', "tuv");
    map.put('9', "wxyz");
    List<String> res = new ArrayList<>();
    backtrack(digits, 0, new StringBuilder(), res, map);
    return res;
  }

  // 回溯
  private void backtrack(String digits, int index, StringBuilder temp, List<String> res, Map<Character, String> map) {
    if (index == digits.length()) {
      // 加入结果集
      res.add(temp.toString());
      return;
    }
    // 2 -> abc
    String lett = map.get(digits.charAt(index));
    for (Character c : lett.toCharArray()) {
      temp.append(c);
      backtrack(digits, index + 1, temp, res, map);
      temp.deleteCharAt(temp.length() - 1);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String digits = "23";
    System.out.println(solution.letterCombinations(digits));
    System.out.println("---");
    System.out.println("".length());
  }

}
