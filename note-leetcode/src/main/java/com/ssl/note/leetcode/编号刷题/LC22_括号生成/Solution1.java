package com.ssl.note.leetcode.编号刷题.LC22_括号生成;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
  /**
   * 题目：数字n代表生成括号的对数，生成所有可能的并且有效的括号组合。
   * 输入：n = 3
   * 输出：["((()))","(()())","(())()","()(())","()()()"]
   */
  public List<String> generateParenthesis(int n) {
    List<List<String>> dp = new ArrayList<>();
    for (int i = 0; i <= n; i++) dp.add(new ArrayList<>());
    dp.get(0).add("");
    for (int i = 1; i <= n; i++) {
      for (int p = 0; p < i; p++) {
        for (String left : dp.get(p)) {
          for (String right : dp.get(i - 1 - p)) {
            dp.get(i).add("(" + left + ")" + right);
          }
        }
      }
    }
    return dp.get(n);
  }


  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int n = 3;
    // [((())), (()()), (())(), ()(()), ()()()]
    System.out.println(solution.generateParenthesis(n));
  }

}
