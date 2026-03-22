package com.ssl.note.leetcode.编号刷题.LC152_乘积最大子数组;

/**
 * @author SongShengLin
 * @date 2022/3/2 8:56 AM
 * @description
 */
public class Solution {

  /**
   * 乘积最大子数组
   * 注意：子数组指数组的连续子序列
   * 输入: nums = [2,3,-2,4]
   * 输出: 6
   */
  public int maxProduct(int[] nums) {

    // 所以每一位数组元素都需要存当前位置的最大乘积、最小乘积
    // iMax:表示0到i的最大乘积
    int iMax = 1;
    // iMin:表示0到i的最小乘积
    int iMin = 1;

    int max = Integer.MIN_VALUE;
    for (int num : nums) {
      // 由于存在负数,导致子数组乘积从最大变成最小 -> 遇到负数，交换iMax、iMin
      if (num < 0) {
        int temp = iMax;
        iMax = iMin;
        iMin = temp;
      }
      // 在解法中， iMax 和 iMin 的定义是 以当前元素结尾的最大/最小乘积 。这里的"以当前元素结尾"是保证连续性的核心：
      //- "结尾"意味着连续 ：如果子数组包含当前元素，并且是连续的，那么它要么是：
      //  1. 延续之前的子数组 ：即之前的子数组加上当前元素（ iMax * num 或 iMin * num ）
      //  2. 以当前元素开始新的子数组 ：即只包含当前元素（ num 本身）
      iMax = Math.max(iMax * num, num);
      iMin = Math.min(iMin * num, num);

      max = Math.max(max, iMax);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] nums = {2, 3, -2, 4};
    Solution solution = new Solution();
    System.out.println(solution.maxProduct(nums));
  }

}
