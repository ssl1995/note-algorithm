package com.ssl.note.leetcode.编号刷题.LC33_搜索旋转排序数组;

/**
 * @author SongShengLin
 * @date 2022/1/23 9:52 PM
 * @description
 */
public class Solution {

  /**
   * 搜索旋转排序数组
   * 输入：nums = [4,5,6,7,0,1,2], target = 0
   * 输出：4
   */
  public int search(int[] nums, int target) {
    if (nums.length == 0) {
      return -1;
    }
    if (nums.length == 1) {
      return nums[0] == target ? 0 : -1;
    }
    int left = 0;
    int right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return mid;
      }
      // 左侧有序
      if (nums[0] <= nums[mid]) {
        if (nums[0] <= target && target < nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else {
        //  右侧有序
        if (nums[mid] < target && target <= nums[nums.length - 1]) {
          left = mid + 1;
        } else {
          right = right - 1;
        }
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {4, 5, 6, 7, 0, 1, 2};
    int target = 0;
    System.out.println(solution.search(nums, target));
  }
}
