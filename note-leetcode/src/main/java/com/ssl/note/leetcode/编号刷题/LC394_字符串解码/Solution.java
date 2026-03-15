package com.ssl.note.leetcode.编号刷题.LC394_字符串解码;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Solution {

  /**
   * 字符串编码
   * 输入：s = "3[a]2[bc]"
   * 输出："aaabcbc"
   * 输入：s = "3[a2[c]]"
   * 输出："accaccacc"
   */
  public String decodeString(String s) {
    if (s == null) {
      return "";
    }

    StringBuilder res = new StringBuilder();

    Deque<Integer> numsStack = new ArrayDeque<>();
    Deque<String> letterStack = new ArrayDeque<>();
    int multi = 0;

    char[] cs = s.toCharArray();
    for (char c : cs) {
      // 数字
      if (c >= '0' && c <= '9') {
        multi = multi * 10 + (c - '0');
      } else if (c == '[') {
        // 左括号，压入数字栈和字母栈
        numsStack.push(multi);
        letterStack.push(res.toString());
        multi = 0;
        res = new StringBuilder();
      } else if (c == ']') {
        // 右括号，弹出数字栈和字母栈，拼接字符串
        StringBuilder temp = new StringBuilder();
        int count = numsStack.pop();
        for (int i = 0; i < count; i++) {
          temp.append(res);
        }
        // 与弹出的字母栈拼接
        res = new StringBuilder(letterStack.pop() + temp);
      } else {
        // 字母，保存当前res
        res.append(c);
      }
    }

    return res.toString();
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    String s = "3[a2[c]]";
    // accaccacc
    System.out.println(solution.decodeString(s));
  }
}
