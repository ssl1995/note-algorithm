package com.ssl.note.common.utils;

import java.util.ArrayList;
import java.util.List;

public class RandomUtil {

  /**
   * 蓄水池抽样（Reservoir Sampling）
   * 从n个元素中等概率、不放回抽取k个，且只需要O(k)额外空间。
   */
  public static List<Integer> pickKByReservoir(List<Integer> nums, int k) {
    if (nums == null) {
      throw new IllegalArgumentException("nums is null");
    }
    int n = nums.size();
    if (k < 0 || k > n) {
      throw new IllegalArgumentException("k must be in [0, n], k=" + k + ", n=" + n);
    }

    List<Integer> reservoir = new ArrayList<>(k);

    // 1) 先放入前k个
    for (int i = 0; i < k; i++) {
      reservoir.add(nums.get(i));
    }

    // 2) i从k到n-1：以k/(i+1)概率选中当前元素，并随机替换蓄水池中的一个
    for (int i = k; i < n; i++) {

      int j = (int) (Math.random() * (i + 1)); // j in [0, i]

      if (j < k) {
        reservoir.set(j, nums.get(i));
      }
    }

    return reservoir;
  }
}
