package com.ssl.note.leetcode.编号刷题.LC84_柱状图中最大矩形;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author SongShengLin
 * @date 2022/1/29 3:29 PM
 * @description
 */
public class Solution {
  /**
   * 柱状图中最大矩形
   * 输入：heights = [2,1,5,6,2,3]
   * 输出：10
   * 解释：最大的矩形为图中红色区域，面积为 10
   */
  public int largestRectangleArea(int[] height) {
    if (height == null || height.length == 0) {
      return 0;
    }
    int maxArea = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < height.length; i++) {
      // 栈底到栈顶：从小到大，遇到比栈顶小的就要出栈
      while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
        // 栈顶：h[j]，表示为最小值往左右两边扩，能扩大什么位置
        int j = stack.pop();
        // 左边比它大的扩不到
        int k = stack.isEmpty() ? -1 : stack.peek();
        // i是当前右边比它大的扩不到
        // [k..j..i]中[..j..]长度是i-k+1-2=i-k-1
        int curArea = (i - k - 1) * height[j];
        maxArea = Math.max(maxArea, curArea);
      }
      stack.push(i);
    }
    while (!stack.isEmpty()) {
      int j = stack.pop();
      int k = stack.isEmpty() ? -1 : stack.peek();
      int curArea = (height.length - k - 1) * height[j];
      maxArea = Math.max(maxArea, curArea);
    }
    return maxArea;
  }

  /**
   * 数组非负，代表直方图，求直方图的最大长方形面积
   * 方法2：时间最快的，栈用int优化啦
   */
  public int largestRectangleArea_test(int[] heights) {
    Deque<Integer> stack = new ArrayDeque<>();

    int maxArea = 0;
    int n = heights.length;
    for (int i = 0; i < n; i++) {
      while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
        // 长
        int j = stack.pop();
        int len = heights[j];

        // 宽
        // 左侧边界：k + 1（因为 k 是第一个比 j 小的，所以有效宽度从 k+1 开始）
        // 右侧边界：i - 1（因为 i 是第一个比 j 小的，所以有效宽度到 i-1 结束）
        // 宽度 = (i - 1) - (k + 1) + 1 = i - k - 1
        int k = stack.isEmpty() ? -1 : stack.peek();

        // 面积
        int curArea = (i - k - 1) * len;
        maxArea = Math.max(maxArea, curArea);
      }
      stack.push(i);
    }

    // 栈中可能还剩下一些元素，这些元素的 右侧没有比它们小的柱子
    while (!stack.isEmpty()) {
      int j = stack.pop();
      int k = stack.isEmpty() ? -1 : stack.peek();
      // n是数组长度，充当右边界
      int curAras = (n - k - 1) * heights[j];

      maxArea = Math.max(maxArea, curAras);
    }

    return maxArea;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] heights = {2, 1, 5, 6, 2, 3};
    System.out.println(solution.largestRectangleArea(heights));
  }
}
