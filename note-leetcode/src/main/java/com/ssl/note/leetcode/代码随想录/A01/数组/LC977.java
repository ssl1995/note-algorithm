package com.ssl.note.leetcode.代码随想录.A01.数组;

import java.util.Arrays;

public class LC977 {

  /**
   * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
   * 输入：nums = [-4,-1,0,3,10]
   * 输出：[0,1,9,16,100]
   * 解释：平方后，数组变为 [16,1,0,9,100]
   * 排序后，数组变为 [0,1,9,16,100]
   */
  public int[] sortedSquares(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new int[]{};
    }
    // 原数组有序，平方方后的最大值一定在最左边或者最右边
    // 所以使用前后双指针
    int i = 0, j = nums.length - 1;

    int[] res = new int[nums.length];
    int k = nums.length - 1;
    while (i <= j) {
      int nums1 = nums[i] * nums[i];
      int nums2 = nums[j] * nums[j];

      if (nums1 > nums2) {
        res[k--] = nums1;
        i++;
      } else {
        res[k--] = nums2;
        j--;
      }
    }

    return res;
  }

  public static void main(String[] args) {
    LC977 lc977 = new LC977();
    int[] nums = new int[]{-4, -1, 0, 3, 10};
    int[] res = lc977.sortedSquares(nums);
    System.out.println(Arrays.toString(res));
  }
}
