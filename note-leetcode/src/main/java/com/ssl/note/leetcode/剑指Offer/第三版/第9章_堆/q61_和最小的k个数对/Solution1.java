package com.ssl.note.leetcode.剑指Offer.第三版.第9章_堆.q61_和最小的k个数对;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2021/10/17 3:05 下午
 * @description
 */
public class Solution1 {

    /**
     * 和最小的k个数对
     * 方法2：时间复杂度O(k * logK)，相比方法1降低了k次时间复杂度
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null || k < 0) {
            return new ArrayList<>();
        }
        // 最小值堆：存两个数组的下标
        Queue<int[]> minIndexHeap = new PriorityQueue<>(Comparator.comparing(index -> nums1[index[0]] + nums2[index[1]]));
        if (nums2.length > 0) {
            for (int i = 0; i < Math.min(k, nums1.length); i++) {
                minIndexHeap.offer(new int[]{i, 0});
            }
        }
        List<List<Integer>> res = new ArrayList<>();

        while (!minIndexHeap.isEmpty() && k-- > 0) {
            // 优化：由于数组是递增有序的，从小达大出栈的都是要入结果集的最小值对，这里就不需要再peek，直接poll就行
            int[] index = minIndexHeap.poll();
            res.add(Arrays.asList(nums1[index[0]], nums2[index[1]]));

            if (index[1] < nums2.length - 1) {
                minIndexHeap.offer(new int[]{index[0], index[1] + 1});
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        int[] nums1 = {1, 5, 13, 21};
        int[] nums2 = {2, 4, 9, 15};
        int k = 3;
        System.out.println(solution.kSmallestPairs(nums1, nums2, k));
    }
}
