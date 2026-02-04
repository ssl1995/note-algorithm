package com.ssl.note.leetcode.编号刷题.LC128_最长连续序列;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/15 3:29 PM
 * @Describe:
 */
public class Solution {
  /**
   * 最长连续序列
   * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
   * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
   * 输入：nums = [100,4,200,1,3,2]
   * 输出：4
   * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
   */
  public int longestConsecutive(int[] nums) {
    // 哈希解法，时间复杂度O(n)，空间复杂度O(n)
    if (nums == null || nums.length == 0) {
      return 0;
    }
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      set.add(num);
    }
    int maxLen = 0;
    // 不能遍历nums，因为可能有很多重复元素
    // 直接遍历set
    for (int num : set) {
      // 当前数-1已经在set中，说明当前数不是起点，跳过
      if (set.contains(num - 1)) {
        continue;
      }

      int curLen = 1;
      int curNum = num;

      while (set.contains(curNum + 1)) {
        curLen++;
        curNum++;
      }
      maxLen = Math.max(maxLen, curLen);
    }

    return maxLen;
  }

  public static void main(String[] args) {
    Solution solution = new Solution();
    int[] nums = {100, 4, 200, 1, 3, 2};
    System.out.println(solution.longestConsecutive(nums));
  }
}
