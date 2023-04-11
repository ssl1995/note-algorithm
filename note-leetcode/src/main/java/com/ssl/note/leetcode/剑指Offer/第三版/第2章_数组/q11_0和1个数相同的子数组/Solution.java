package com.ssl.note.leetcode.剑指Offer.第三版.第2章_数组.q11_0和1个数相同的子数组;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2021/9/15 9:23 上午
 */
public class Solution {

    /**
     * 连续数组：找到含有相同数量0和1的连续子数组
     * nums中只有0和1
     */
    public int findMaxLength(int[] nums) {
        // Map<当前下标累加和，当前下标>
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);// 初始化map:<累加和0，下标-1>
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            // 遇到原数组中的0，累加-1；遇到1，累加1
            sum += nums[i] == 0 ? -1 : 1;
            // 如果后面的累加和曾经出现过，说明中间出现了相同的0和1才能导致累加和重复出现
            if (map.containsKey(sum)) {
                // [j,j+1..i]记录j+1..i的长度 = i-j 不用加1
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0};
        Solution solution = new Solution();
        int res = solution.findMaxLength(nums);
        System.out.println(res);
    }
}
