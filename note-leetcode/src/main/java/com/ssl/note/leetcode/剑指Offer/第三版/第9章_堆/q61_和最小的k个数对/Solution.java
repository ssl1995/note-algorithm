package com.ssl.note.leetcode.剑指Offer.第三版.第9章_堆.q61_和最小的k个数对;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author SongShengLin
 * @date 2021/10/17 3:05 下午
 * @description
 */
public class Solution {

    /**
     * 和最小的k个数对
     * 方法1：时间复杂度O(k^2 * logK)
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null || k < 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        // 最大堆
        Queue<int[]> maxHeap = new PriorityQueue<>((n1, n2) -> n2[0] + n2[1] - (n1[0] + n1[1]));
        // 优化:nums都是排序的，所以我们只用比对前k个即可
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            for (int j = 0; j < Math.min(k, nums2.length); j++) {
                int num1 = nums1[i];
                int num2 = nums2[j];
                if (maxHeap.size() < k) {
                    maxHeap.offer(new int[]{num1, num2});
                } else {
                    int[] peek = maxHeap.peek();
                    int peekSum = peek[0] + peek[1];
                    // 待添加的数对之和 < 最大值堆的堆顶
                    if (num1 + num2 < peekSum) {
                        maxHeap.poll();
                        maxHeap.offer(new int[]{num1, num2});
                    }
                }
            }
        }

        for (int[] nums : maxHeap) {
            List<Integer> sub = new ArrayList<>();
            sub.add(nums[0]);
            sub.add(nums[1]);
            res.add(sub);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 5, 13, 21};
        int[] nums2 = {2, 4, 9, 15};
        int k = 3;
        // [[5, 2], [1, 2], [1, 4]]
        System.out.println(solution.kSmallestPairs(nums1, nums2, k));
    }
}
