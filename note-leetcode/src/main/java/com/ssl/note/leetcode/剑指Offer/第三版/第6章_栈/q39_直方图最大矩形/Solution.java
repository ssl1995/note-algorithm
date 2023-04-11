package com.ssl.note.leetcode.剑指Offer.第三版.第6章_栈.q39_直方图最大矩形;

import java.util.Deque;
import java.util.LinkedList;


/**
 * @author SongShengLin
 * @date 2021/10/7 9:47 上午
 * @description
 */
public class Solution {
    /**
     * 柱状图中最大的矩形
     * 输入：heights = [2,1,5,6,2,3]
     * 输出：10
     * 解释：最大的矩形为图中红色区域，面积为 10
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null) {
            return 0;
        }
        // 单调栈
        Deque<Integer> stack = new LinkedList<>();
        int maxArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            // 栈中保证单调递增，遇到待加入的小的，就要出栈
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int len = i - k - 1;
                maxArea = Math.max(maxArea, len * heights[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int len = heights.length - k - 1;
            maxArea = Math.max(maxArea, len * heights[j]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(solution.largestRectangleArea(heights));
    }

}
