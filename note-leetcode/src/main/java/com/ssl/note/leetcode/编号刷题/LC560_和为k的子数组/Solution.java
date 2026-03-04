package com.ssl.note.leetcode.编号刷题.LC560_和为k的子数组;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/2/27 11:00 AM
 * @description
 */
public class Solution {
  /**
   * 和为k的子数组
   * 返回该数组中和为 k 的连续子数组的个数
   * 输入：nums = [1,2,3], k = 3
   * 输出：2
   */
  public int subarraySum(int[] nums, int k) {

    // 规定前缀和0，出现的次数=1
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    int sum = 0;
    int count = 0;

    for (int num : nums) {
      sum += num;

      // pre[L,R] = sum[R] - sum[L-1] = k
      // sum[L-1] = k - sum[R],sum[L-1]就是曾经的某个出现的前缀和
      count += map.getOrDefault(sum - k, 0);

      // map<前缀和，次数>
      map.put(sum, map.getOrDefault(sum, 0) + 1);
    }

    return count;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {1, 2, 3};
    int k = 3;
    System.out.println(solution.subarraySum(nums, k));
  }
}
