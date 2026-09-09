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
    int n = nums.length;
    int l = 0;
    int r = n - 1;

    while (l <= r) {
      int m = l + (r - l) / 2;
      if (nums[m] == target) {
        return m;
      } else {
        // 哪侧有序就二分哪侧，先比较端点再比较值域
        // m是向下取整，两个元素时，l=m，左端点必须有等于
        if (nums[l] <= nums[m]) {
          if (nums[l] <= target && target < nums[m]) {
            r = m - 1;
          } else {
            l = m + 1;
          }
        } else {
          // 右侧有序，就二分
          if (nums[m] < target && target <= nums[n - 1]) {
            l = m + 1;
          } else {
            r = m - 1;
          }
        }

      }
    }
    return -1;
  }

  public int searchError(int[] nums, int target) {
    int n = nums.length;
    int l = 0;
    int r = n - 1;

    while (l <= r) {
      int m = l + (r - l) / 2;
      if (nums[m] == target) {
        return m;
      } else {
        // 两元素二分，m是想下取整，左端点必须是<=
        if (nums[l] < nums[m]) {
          if (nums[l] <= target && target < nums[m]) {
            r = m - 1;
          } else {
            l = m + 1;
          }
        } else {
          if (nums[m] < target && target <= nums[n - 1]) {
            l = m + 1;
          } else {
            r = m - 1;
          }
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
