package com.ssl.note.leetcode.编号刷题.LC53_最大子数组和;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:21 AM
 * @description
 */
public class Solution2 {

  /**
   * 最大子数组和
   * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
   * 输出：6
   * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
   */
  public int maxSubArray(int[] nums) {
    // 分治法
    return divide(nums, 0, nums.length - 1);
  }

  private int divide(int[] nums, int left, int right) {
    if (left == right) {
      return nums[left];
    }
    int mid = left + (right - left) / 2;
    // 左边找最大
    int leftMax = divide(nums, left, mid);
    // 右边找最大
    int rightMax = divide(nums, mid + 1, right);
    // 跨越中间
    int crossMax = crossMax(nums, left, mid, right);

    return Math.max(Math.max(leftMax, rightMax), crossMax);
  }

  private int crossMax(int[] nums, int left, int mid, int right) {
    // 从mid向左扩展
    int leftSum = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = mid; i >= left; i--) {
      sum += nums[i];
      leftSum = Math.max(leftSum, sum);
    }
    // 从mid+1向右扩展
    int rightSum = Integer.MIN_VALUE;
    sum = 0;
    for (int i = mid + 1; i <= right; i++) {
      sum += nums[i];
      rightSum = Math.max(rightSum, sum);
    }
    return leftSum + rightSum;
  }

  public static void main(String[] args) {
    Solution2 solution = new Solution2();
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(solution.maxSubArray(nums));
  }
}
