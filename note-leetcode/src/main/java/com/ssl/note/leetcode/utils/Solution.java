package com.ssl.note.leetcode.utils;

import java.util.*;

public class Solution {
  public int longestConsecutive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    Map<Integer, Integer> fathers = new LinkedHashMap<>();
    Map<Integer, Integer> counts = new LinkedHashMap<>();
    Set<Integer> set = new LinkedHashSet<>();

    for (int num : nums) {
      fathers.put(num, num);
      counts.put(num, 1);
      set.add(num);
    }

    for (int num : nums) {
      if (set.contains(num - 1)) {
        union(fathers, counts, num, num - 1);
      }
      if (set.contains(num + 1)) {
        union(fathers, counts, num, num + 1);
      }
    }

    int res = 0;
    for (int length : counts.values()) {
      res = Math.max(res, length);
    }

    return res;
  }

  private void union(Map<Integer, Integer> fathers, Map<Integer, Integer> counts, int n1, int n2) {
    int f1 = findFather(fathers, n1);
    int f2 = findFather(fathers, n2);
    if (f1 == f2) {
      return;
    }

    fathers.put(f1, f2);
    counts.put(f2, counts.get(f1) + counts.get(f2));
  }

//  private int findFather(Map<Integer, Integer> fathers, int n) {
//    Integer tempFather = fathers.get(n);
//    if (tempFather == n) {
//      return tempFather;
//    }
//    return findFather(fathers, tempFather);
//  }
  private int findFather(Map<Integer, Integer> fathers, int num) {
    if (fathers.get(num) != num) {
      // 路径压缩的作用是将当前节点直接挂载到根节点下，减少后续查找的深度
      fathers.put(num, findFather(fathers, fathers.get(num)));
    }
    return fathers.get(num);
  }

}
