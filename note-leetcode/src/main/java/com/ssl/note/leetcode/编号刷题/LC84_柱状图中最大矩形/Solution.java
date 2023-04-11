package com.ssl.note.leetcode.编号刷题.LC84_柱状图中最大矩形;

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
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            // 栈底到栈顶：从大到小
            while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
                // 以h[j]为最小值往左右两边扩，能扩大什么位置
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
    public int largestRectangleArea2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int N = height.length;
        int[] stack = new int[N];
        int si = -1;
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            while (si != -1 && height[i] <= height[stack[si]]) {
                int j = stack[si--];
                int k = si == -1 ? -1 : stack[si];
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack[++si] = i;
        }
        while (si != -1) {
            int j = stack[si--];
            int k = si == -1 ? -1 : stack[si];
            int curArea = (height.length - k - 1) * height[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(solution.largestRectangleArea(heights));
    }
}
