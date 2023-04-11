package com.ssl.note.leetcode.剑指Offer.第三版.第6章_栈.q40_矩阵中最大矩形;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2021/10/7 11:07 上午
 * @description
 */
public class Solution {
    /*
     * 矩阵中1图形的面积
     * 输入：matrix = ["10100","10111","11111","10010"]
     * 输出：6
     */
    public int maximalRectangle(String[] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[] nums = new int[matrix[0].length()];
        int maxArea = 0;
        for (String numStr : matrix) {
            for (int j = 0; j < numStr.length(); j++) {
                int num = Integer.parseInt(String.valueOf(numStr.charAt(j)));
                //  遇到1高度加1
                if (num == 1) {
                    nums[j]++;
                } else if (num == 0) {
                    // 难点：遇到0必须充值高度为0
                    nums[j] = 0;
                }
            }
            int max = largestRectangleArea(nums);
            maxArea = Math.max(maxArea, max);
        }
        return maxArea;
    }


    private int largestRectangleArea(int[] heights) {
        if (heights == null) {
            return 0;
        }
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
}
