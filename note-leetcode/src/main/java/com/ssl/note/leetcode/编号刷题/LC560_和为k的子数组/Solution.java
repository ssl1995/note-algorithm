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
        // <(当前元素和构成的)前缀和，该前缀和出现的次数>
        Map<Integer, Integer> map = new HashMap<>();
        // 难点:为什么前缀和为0的个数初始化为1，而不是0?
        // 当前缀和恰好等于k时（如nums = [3], k = 3），sum - k = 0
        // 此时需要统计初始的0出现次数，
        map.put(0, 1);

        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;
            // 当前前缀和是否有sum-k的前缀和
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            // 更新前缀和
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
