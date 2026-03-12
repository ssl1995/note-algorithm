package com.ssl.note.leetcode.编号刷题.LC22_括号生成;

import java.util.ArrayList;
import java.util.List;

public class Solution {
  /**
   * 题目：数字n代表生成括号的对数，生成所有可能的并且有效的括号组合。
   * 输入：n = 3
   * 输出：["((()))","(()())","(())()","()(())","()()()"]
   */
  public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    backtrack(n, n, path, res);
    return res;
  }

  /**
   * 左右括号有效的条件：
   * 1、左右括号数量对等
   * 2、任何前缀中左括号 >= 右括号
   */
  private void backtrack(int leftNeed, int rightNeed, StringBuilder path, List<String> res) {
    // 1. 递归出口：leftNeed和rightNeed都为0时，说明左右括号都用完了
    if (leftNeed == 0 && rightNeed == 0) {
      res.add(path.toString());
      return;
    }
    // 2. 递归体：leftNeed大于0时，说明还可以添加左括号
    if (leftNeed > 0) {
      path.append("(");
      backtrack(leftNeed - 1, rightNeed, path, res);
      // 回溯
      path.deleteCharAt(path.length() - 1);
    }
    // 3. 递归体：leftNeed小于rightNeed时，说明还可以添加右括号
    if (leftNeed < rightNeed) {
      path.append(")");
      backtrack(leftNeed, rightNeed - 1, path, res);
      // 回溯
      path.deleteCharAt(path.length() - 1);
    }
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int n = 3;
    // [((())), (()()), (())(), ()(()), ()()()]
    System.out.println(solution.generateParenthesis(n));
  }

}
