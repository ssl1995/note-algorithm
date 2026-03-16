package com.ssl.note.leetcode.编号刷题.LC215_数组中的第K大元素;

import java.util.Random;

public class Solution1 {

  /**
   * 数组中第k大的数
   * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
   * 输出: 4
   */
  public int findKthLargest(int[] nums, int k) {
    if (nums == null) {
      return -1;
    }
    int t = nums.length - k;
    return quickSelect(nums, 0, nums.length-1, t);
  }

  private int quickSelect(int[] nums, int left, int right, int target) {
    if (left == right) {
      return nums[left];
    }

    int random = left + new Random().nextInt(right - left + 1);
    swap(nums, random, right);

    int pivot = nums[right];
    int lt = left - 1;
    int gt = right;
    int i = left;

    while (i < gt) {
      if (nums[i] < pivot) {
        lt++;
        swap(nums, lt, i);
        i++;
      } else if (nums[i] > pivot) {
        gt--;
        swap(nums, gt, i);
      } else {
        i++;
      }
    }

    swap(nums, gt, right);

    if (target <= lt) {
      return quickSelect(nums, left, lt, target);
    }
    if (target >= gt) {
      return quickSelect(nums, gt, right, target);
    }

    return nums[target];
  }

  private void swap(int[] nums, int i, int j) {
    // 避免自我交换
    if (i == j) {
      return;
    }
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void main(String[] args) {
    int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
    int k = 4;
    Solution1 solution = new Solution1();
    // 4
    System.out.println(solution.findKthLargest(nums, k));
  }
}
