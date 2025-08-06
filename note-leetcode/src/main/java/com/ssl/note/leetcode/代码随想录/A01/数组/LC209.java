package com.ssl.note.leetcode.代码随想录.A01.数组;

public class LC209 {

  /**
   * 给定一个含有 n 个正整数的数组和一个正整数 target 。
   * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，
   * 返回最短子序列长度。如果不存在符合条件的子数组，返回 0 。
   * <p>
   * 输入：target = 7, nums = [2,3,1,2,4,3]
   * 输出：2
   * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
   */
  public int minSubArrayLen(int target, int[] nums) {
    if (nums == null || nums.length == 0 || target < 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0] < target ? 0 : 1;
    }
    // left = 滑动窗口的左指针，每次移动右指针，动态调整左指针的位置，减轻时间复杂度
    int left = 0;
    int sum = 0;
    // res: 如果nums=[1,1,1]，target=4，res返回0
    int res = Integer.MAX_VALUE;
    // 如果滑动窗口的右指针是遍历的初始位置，终止位置就要二次遍历=暴力法
    // right = 滑动窗口的右指针：才是遍历的终止位置
    for (int right = 0; right < nums.length; right++) {
      sum += nums[right];
      // 滑动窗口的核心
      while (sum >= target) {
        res = Math.min(res, right - left + 1);
        sum -= nums[left++];
      }
    }
    // 如果nums=[1,1,1]，target=4，res返回0
    return res == Integer.MAX_VALUE ? 0 : res;
  }
}
