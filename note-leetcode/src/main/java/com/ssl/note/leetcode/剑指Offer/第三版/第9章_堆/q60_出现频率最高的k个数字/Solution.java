package com.ssl.note.leetcode.剑指Offer.第三版.第9章_堆.q60_出现频率最高的k个数字;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2021/10/17 2:49 下午
 * @description
 */
public class Solution {
    /**
     * 出现频率最高的K个数字
     */
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        // 小根堆：存entry，按照频率value升序排序
        Queue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                // 每次都要先出一个元素
                minHeap.poll();
                minHeap.offer(entry);
            }
        }

        int[] res = new int[k];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : minHeap) {
            res[i++] = entry.getKey();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        int k = 2;
        Solution solution = new Solution();
        // 出现频率最高的2个数字：-1，2
        System.out.println(Arrays.toString(solution.topKFrequent(nums, k)));
    }
}
