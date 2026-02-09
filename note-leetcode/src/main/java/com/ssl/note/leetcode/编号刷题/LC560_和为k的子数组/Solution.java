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

    Map<Integer, Integer> map = new HashMap<>();
    // 规定前缀和0，出现的次数=1
    map.put(0, 1);

    int sum = 0;
    int count = 0;
    for (int i = 0; i <= nums.length - 1; i++) {
      // sum[i]=prefixSum[i+1]=nums[0]+...+nums[i]
      sum += nums[i];
      // k = preFixSum[i+1] - 某个前缀和 = sum - 某个前缀和
      int x = sum - k;
      // 如果某个前缀和出现过，那就累加
      if (map.containsKey(x)) {
        count += map.get(x);
      }
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
