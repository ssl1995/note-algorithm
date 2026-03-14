package com.ssl.note.leetcode.编号刷题.LC20_有效的括号;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2022/1/20 11:29 PM
 * @description
 */
public class Solution {

  /**
   * 有效的括号
   * 输入：s = "{[]}"
   * 输出：true
   */
  public boolean isValid(String s) {
    if (s == null) {
      return false;
    }
    Map<Character, Character> map = new HashMap<>();
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');

    Deque<Character> stack = new ArrayDeque<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (map.containsKey(c)) {
        stack.push(c);
        continue;
      }
      // 出栈时没有元素，就不可能是有效的括号
      if (stack.isEmpty()) {
        return false;
      }

      char pop = stack.pop();
      if (map.get(pop) != c) {
        return false;
      }
    }
    // s='{'时，栈还有元素
    return stack.isEmpty();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.isValid("{"));
  }
}
