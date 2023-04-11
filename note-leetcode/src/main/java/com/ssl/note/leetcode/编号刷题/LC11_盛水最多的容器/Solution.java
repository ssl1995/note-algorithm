package com.ssl.note.leetcode.编号刷题.LC11_盛水最多的容器;

/**
 * @author SongShengLin
 * @date 2022/1/20 8:14 AM
 * @description
 */
public class Solution {
    /**
     * 盛水最多的容器
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        int tempArea = 0;
        while (left < right) {
            // 移动短板，才可能增大面积
            if (height[left] < height[right]) {
                tempArea = (right - left) * height[left];
                left++;
            } else {
                tempArea = (right - left) * height[right];
                right--;
            }
            maxArea = Math.max(tempArea, maxArea);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(solution.maxArea(height));
    }
}
