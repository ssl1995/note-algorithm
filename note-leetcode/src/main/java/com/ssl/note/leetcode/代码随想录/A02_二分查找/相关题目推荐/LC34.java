package com.ssl.note.leetcode.代码随想录.A02_二分查找.相关题目推荐;

import java.util.Arrays;

public class LC34 {

  /**
   * 力扣34：
   * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
   * 如果数组中不存在目标值 target，返回 [-1, -1]。
   * 输入：nums = [5,7,7,8,8,10], target = 8
   * 输出：[3,4]
   */
  public int[] searchRange(int[] nums, int target) {

    int leftBorder = searchLeftRange(nums, target);
    int rightBorder = searchRightRange(nums, target);
    // 情况一：{1,2,3}找-1，或者 {1,2,3}找4
    if (leftBorder == -2 || rightBorder == -2) {
      return new int[]{-1, -1};
    }
    // 情况三:{5, 7, 7, 8, 8, 10}找8
    if (rightBorder - leftBorder > 1) {
      return new int[]{leftBorder + 1, rightBorder - 1};
    }
    // 情况二：{5, 7, 7, 8, 8, 10}找6
    return new int[]{-1, -1};
  }

  /**
   * 左边界：找 < target的第一个数
   */
  public int searchLeftRange(int[] nums, int target) {

    int left = 0;
    int right = nums.length - 1;
    int res = -2;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] >= target) {
        right = mid - 1;
        res = right;
      } else {
        left = mid + 1;
      }
    }

    return res;
  }

  /**
   * 左边界：找 <= target的第一个数
   */
  public int searchLeftEqRange(int[] nums, int target) {

    int left = 0;
    int right = nums.length - 1;
    int res = -2;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] >= target) {
        res = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return res;
  }

  /**
   * 左边界：找 > target的第一个数
   */
  public int searchRightRange(int[] nums, int target) {

    int left = 0;
    int right = nums.length - 1;
    int res = -1;
    while (left <= right) {

      int mid = left + (right - left) / 2;
      if (nums[mid] <= target) {
        left = mid + 1;
        res = left;
      } else {
        right = mid - 1;
      }
    }

    return res;
  }

    /**
   * 左边界：找 >= target的第一个数
   */
  public int searchRightEqRange(int[] nums, int target) {

    int left = 0;
    int right = nums.length - 1;
    int res = -1;
    while (left <= right) {

      int mid = left + (right - left) / 2;
      if (nums[mid] <= target) {
        res = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    int[] nums = {5, 7, 7, 8, 8, 10};
    int t = 8;
    int[] res = new int[]{3, 4};
    // <t,左边界：2
    System.out.println(new LC34().searchLeftRange(nums, t));
    // <=t,左边界：3
    System.out.println(new LC34().searchLeftEqRange(nums, t));
    // >t,右边界：5
    System.out.println(new LC34().searchRightRange(nums, t));
    // >=t,右边界：4
    System.out.println(new LC34().searchRightEqRange(nums, t));
  }

}
