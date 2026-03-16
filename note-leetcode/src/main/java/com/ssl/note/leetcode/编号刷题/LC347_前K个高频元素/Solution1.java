package com.ssl.note.leetcode.编号刷题.LC347_前K个高频元素;

import java.util.*;


public class Solution1 {

  /**
   * 前k个高频元素
   * 输入: nums = [1,1,1,2,2,3], k = 2
   * 输出: [1,2]
   */
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }

//    PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
    PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      if (minHeap.size() < k) {
        minHeap.offer(entry);
      } else if (minHeap.peek() != null && minHeap.peek().getValue() < entry.getValue()) {
        minHeap.poll();
        minHeap.offer(entry);
      }
    }

    int[] res = new int[k];
    for (int i = 0; i < k; i++) {
      if (minHeap.peek() != null) {
        res[i] = minHeap.poll().getKey();
      }
    }

    return res;
  }

  public static void main(String[] args) {
    Solution1 solution1 = new Solution1();
    int[] nums = {1, 1, 1, 2, 2, 3};
    int t = 2;
    System.out.println(Arrays.toString(solution1.topKFrequent(nums, t)));
  }
}
