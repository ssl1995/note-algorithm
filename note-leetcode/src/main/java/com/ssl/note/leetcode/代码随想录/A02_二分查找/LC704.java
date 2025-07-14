package com.ssl.note.leetcode.代码随想录.A02_二分查找;

public class LC704 {

  /**
   * LC704：二分查找
   * 1、左闭右闭
   * 2、左闭右开
   */
  public int search(int[] nums, int target) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return -1;
  }
}
