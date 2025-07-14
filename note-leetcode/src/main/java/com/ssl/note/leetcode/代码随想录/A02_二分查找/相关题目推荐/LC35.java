package com.ssl.note.leetcode.代码随想录.A02_二分查找.相关题目推荐;

public class LC35 {
  /**
   * LC35:搜索插入位置
   * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
   * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
   */
  public int searchInsert(int[] nums, int target) {
    if (nums.length == 0) {
      return 0;
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
    // 二分返回-1，这里返回left，自动处理所有边界情况
    // >跳过循环 = left或right自动记录了上次的索引
    return left;
  }

  public static void main(String[] args) {
    int[] nums = {1, 3, 5, 6};
    int t = 7;
    int res = 4;
    System.out.println(new LC35().searchInsert(nums, t));
    System.out.println(new LC35().searchInsert(nums, t) == res);
  }
}
