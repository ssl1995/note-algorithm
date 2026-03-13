package com.ssl.note.leetcode.编号刷题.LC35_搜素插入的位置;

public class Solution1 {

  /**
   * 搜索插入的位置
   * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
   * 请必须使用时间复杂度为 O(log n) 的算法。
   * 输入: nums = [1,3,5,6], target = 5
   * 输出: 2
   */
  public int searchInsert(int[] nums, int target) {
    // 找>=t的第一个下标，时间复杂度O(logN)
    int left = 0;
    // 陷阱：这里必须是n的长度=默认是[1,3,5,6,空]的数组
    int right = nums.length;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] >= target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {1, 3, 5, 6};
    int target = 7;
    System.out.println(solution.searchInsert(nums, target));
  }

}
