package com.ssl.note.leetcode.编号刷题.LC85_最大矩形;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2022/1/29 4:25 PM
 * @description
 */
public class Solution {
  /**
   * 最大矩形
   * matrix = [["1","0","1","0","0"],
   * ["1","0","1","1","1"],
   * ["1","1","1","1","1"],
   * ["1","0","0","1","0"]]
   * 输出：6
   * 解析：这道题主要是考查LC84题的应用
   */
  public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0) {
      return 0;
    }
    int col = matrix[0].length;
    int[] heights = new int[col];

    int maxArea = 0;
    for (char[] cs : matrix) {
      for (int i = 0; i < col; i++) {
        // 如果遇到0，重置高度=0
        // 如果遇到1，高度=上次高度+1
        heights[i] = cs[i] == '0' ? 0 : ++heights[i];
      }
      maxArea = Math.max(maxArea, largestRectangleArea(heights));
    }

    return maxArea;
  }

  /**
   * 柱状图中最大矩形
   * 力扣84题
   */
  private int largestRectangleArea(int[] heights) {
    if (heights == null || heights.length == 0) {
      return 0;
    }
    int n = heights.length;
    Deque<Integer> stack = new ArrayDeque<>();

    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
        int j = stack.pop();
        int k = stack.isEmpty() ? -1 : stack.peek();
        int area = (i - k - 1) * heights[j];
        maxArea = Math.max(maxArea, area);
      }
      stack.push(i);
    }

    while (!stack.isEmpty()) {
      int j = stack.pop();
      int k = stack.isEmpty() ? -1 : stack.peek();
      int area = (n - k - 1) * heights[j];
      maxArea = Math.max(maxArea, area);
    }

    return maxArea;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
//    char[][] matrix = {
//        {'1', '0', '1', '0', '0'},
//        {'1', '0', '1', '1', '1'},
//        {'1', '1', '1', '1', '1'},
//        {'1', '0', '0', '1', '0'}
//    };
    char[][] matrix1 = {
        {'0', '1'},
        {'1', '0'}
    };
    System.out.println(solution.maximalRectangle(matrix1));
  }
}


