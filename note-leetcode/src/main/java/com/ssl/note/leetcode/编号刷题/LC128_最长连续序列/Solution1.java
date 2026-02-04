package com.ssl.note.leetcode.编号刷题.LC128_最长连续序列;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Solution1 {

  /**
   * 并查集的解法
   */
  public int longestConsecutive(int[] nums) {
    Map<Integer, Integer> fathers = new HashMap<>();  // 父节点
    Map<Integer, Integer> counts = new HashMap<>();   // 集合大小
    Set<Integer> set = new HashSet<>();
    // 初始化：每个数字是自己的父节点，集合大小为1
    for (int num : nums) {
      fathers.put(num, num);
      counts.put(num, 1);
      set.add(num);
    }
    // 合并相邻元素
    for (int num : nums) {
      if (set.contains(num + 1)) {
        union(fathers, counts, num, num + 1);
      }
      if (set.contains(num - 1)) {
        union(fathers, counts, num, num - 1);
      }
    }
    // 找最大集合
    int res = 0;
    for (int length : counts.values()) {
      res = Math.max(res, length);
    }
    return res;
  }

  private void union(Map<Integer, Integer> fathers, Map<Integer, Integer> counts, int a, int b) {
    int fa = findFather(fathers, a);
    int fb = findFather(fathers, b);
    if (fa != fb) {
      fathers.put(fa, fb);  // fa挂到fb下
      counts.put(fb, counts.get(fa) + counts.get(fb));  // 更新fb的集合大小
    }
  }

  private int findFather(Map<Integer, Integer> fathers, int num) {
    if (fathers.get(num) != num) {
      fathers.put(num, findFather(fathers, fathers.get(num)));  // 路径压缩
    }
    return fathers.get(num);
  }

  public static void main(String[] args) {
    Solution1 solution = new Solution1();
    int[] nums = {100, 4, 200, 1, 3, 2};
    System.out.println(solution.longestConsecutive(nums));
  }
}
