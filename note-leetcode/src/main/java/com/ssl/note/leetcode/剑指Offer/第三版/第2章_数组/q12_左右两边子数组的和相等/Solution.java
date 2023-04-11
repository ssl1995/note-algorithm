package com.ssl.note.leetcode.剑指Offer.第三版.第2章_数组.q12_左右两边子数组的和相等;

public class Solution {

    /**
     * 寻找数组的中心下标
     * 输入：nums = [1, 7, 3, 6, 5, 6]
     * 输出：3
     * 解释：
     * 中心下标是 3 。
     * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
     * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
     */
    public int pivotIndex(int[] nums) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 左边的累加和 == sum - nums[i]
            // 右边的累加和 == total - sum
            if (sum - nums[i] == total - sum) {
                return i;
            }
        }
        return -1;
    }
}
