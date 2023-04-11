package com.ssl.note.leetcode.剑指Offer.第三版.第2章_数组.q9_乘积小于k的子数组;

/**
 * @author SongShengLin
 * @date 2022/10/2 19:00
 * @description
 */
public class Solution1 {

    /**
     * 乘积小于k的子数组个数
     * 方法1：两次遍历
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        int res = 0;
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            // 外层：乘积重置1
            product = 1;
            product *= nums[i];
            if (product < k) {
                res++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                product *= nums[j];
                if (product < k) {
                    res++;
                } else {
                    break;
                }
            }
        }
        return res;
    }
}
