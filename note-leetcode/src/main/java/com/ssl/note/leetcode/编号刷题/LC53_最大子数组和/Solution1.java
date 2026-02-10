package com.ssl.note.leetcode.编号刷题.LC53_最大子数组和;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:21 AM
 * @description
 */
public class Solution1 {

  /**
   * 最大子数组和
   * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
   * 输出：6
   * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
   */
  public int maxSubArray(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    int pre = nums[0];// 前一个元素的最大累加和
    int max = nums[0];

    for (int i = 1; i < nums.length; i++) {
      // 如果前一个元素的最大累加和＜0 = 负收益，就丢弃
      if (pre < 0) {
        pre = 0;
      }
      pre += nums[i];

      max = Math.max(max, pre);
    }

    return max;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(solution.maxSubArray(nums));
  }
}
