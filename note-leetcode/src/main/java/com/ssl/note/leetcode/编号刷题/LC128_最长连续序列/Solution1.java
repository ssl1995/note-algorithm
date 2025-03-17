package com.ssl.note.leetcode.编号刷题.LC128_最长连续序列;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Solution1 {
  /**
   * 最优解：
   * 1、只处理起点的元素，因为起点的元素可以确定连续序列的长度
   * 2、不走并查集合并操作，每次统计该连续序列的长度
   */
  public int longestConsecutive(int[] nums) {

    Set<Integer> set = new HashSet<>();  // 记录所有的数值
    for (int num : nums) {
      set.add(num);    // 将数组中的值加入哈希表中
    }

    int maxLen = 0;
    // 注意：这里是遍历set，不是遍历nums
    // 因为set会去重
    for (int num : set) {
      // 只处理数组元素中起点位置的元素：
      // 1、set中已经存在某个连续数的起点，跳过
      // 2、遍历到那个数的时候，会重新计算该连续数的长度，所以从当前数开始
      if (set.contains(num - 1)) {
        continue;
      }
      int curLen = 1;
      while (set.contains(++num)) {
        curLen++;
      }
      maxLen = Math.max(maxLen, curLen);
    }

    return maxLen;
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {100, 4, 200, 1, 3, 2};
    System.out.println(solution.longestConsecutive(nums));
  }
}
