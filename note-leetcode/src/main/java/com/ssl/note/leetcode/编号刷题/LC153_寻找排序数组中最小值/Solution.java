package com.ssl.note.leetcode.编号刷题.LC153_寻找排序数组中最小值;

public class Solution {

  /**
   * 寻找排序数组中最小值
   * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
   * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
   * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
   * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
   * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
   * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
   * 输入：nums = [3,4,5,1,2]
   * 输出：1
   * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
   */
  public int findMin(int[] nums) {
    if (nums == null) {
      return -1;
    }
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < nums[right]) {
        // <,最小值可能是nums[mid]
        right = mid;
      } else if (nums[mid] > nums[right]) {
        // >,最小值不可能是nums[mid]
        left = mid + 1;
      } else {
        // [1,0,1,1,1]
        // 如果选择left++，上述的情况就会把最小值0给遗漏掉
        // [1,1,1,0,1]
        // nums[mid]==nums[right]时，无法确定最小值，只能缩小范围
        right--;

      }
    }
    // nums = [3,4,5,1,2]
    // left=4, right=4, mid=4, nums[mid]=0 == nums[right]=0，right--
    // 循环结束，返回 nums[left]=0
    return nums[left];
  }
}
