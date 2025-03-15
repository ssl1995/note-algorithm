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
    int i = 0;
    int j = height.length - 1;

    int maxArea = 0;
    while (i < j) {
      int curArea;
      // 高度低的才移
      if (height[i] < height[j]) {
        curArea = (j - i) * height[i];
        i++;
      } else {
        curArea = (j - i) * height[j];
        j--;
      }
      maxArea = Math.max(maxArea, curArea);
    }

    return maxArea;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
    System.out.println(solution.maxArea(height));
  }
}
