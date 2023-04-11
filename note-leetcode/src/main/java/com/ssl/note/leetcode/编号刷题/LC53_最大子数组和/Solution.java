package com.ssl.note.leetcode.编号刷题.LC53_最大子数组和;

/**
 * @author SongShengLin
 * @date 2022/1/26 8:21 AM
 * @description
 */
public class Solution {

    /**
     * 最大子数组和
     * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
     * 输出：6
     * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // max与pre比较，pre可能为负数，所以max初值为最小的负数
        int max = Integer.MIN_VALUE;
        int pre = 0;
        int index = 0;
        while (index <= nums.length - 1) {
            if (pre < 0) {
                pre = 0;
            }
            pre += nums[index++];
            max = Math.max(max, pre);
        }
        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray(nums));
    }
}
